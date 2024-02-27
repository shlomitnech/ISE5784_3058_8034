package renderer;

import primitives.*;
import lighting.*;
import scene.*;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class SimpleRayTracer extends RayTraceBase {

    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final Double3 INITIAL_K = new Double3(1.0);

    private static final double DELTA = 0.1;

    public SimpleRayTracer(Scene s) {
        super(s);
    }


    /**
     * calc the closest intersection point to the ray's head
     * if no intersections, return null
     *
     * @param ray
     * @return closest point to ray's head
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        return ray.findClosestGeoPoint(scene.geometries.findGeoIntersections(ray));
    }

    /***
     *return the color of the closest point
     */
    @Override
    public Color traceRay(Ray ray) {
        GeoPoint closestPoint = findClosestIntersection(ray); //return the closest GeoPoint that the ray hits
        return closestPoint == null ? scene.background : calcColor(closestPoint, ray);
    }

    public Color traceRays(List<Ray> rays) {
        if (rays.size() == 1) { //if we are not doing antialiasing
            Ray r = rays.getFirst();
            GeoPoint closestPoint = findClosestIntersection(r); //return the closest GeoPoint that the ray hits
            return closestPoint == null ? scene.background : calcColor(closestPoint, r);
        }
        Color c = Color.BLACK;
        if (rays.isEmpty())
            return c;
        //we are doing antialiasing
        for (Ray r : rays)
        {
            GeoPoint closestPoint = findClosestIntersection(r); //return the closest GeoPoint that the ray hi
            if (closestPoint== null)
                c = c.add(scene.background);
            else
                c = c.add(calcColor(closestPoint, r));
        }
        return c.reduce(rays.size()); //this was double before
    }


    /***
     * Get the color from ambient light and intensity
     * calls helper function to get global and local colors
     * @param gp intersection point
     * @param ray
     * @returns color
     */
    private Color calcColor(GeoPoint gp, Ray ray) {
        return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(scene.ambientLight.getIntensity());

        //returns color of the point where the ray hits combined with ambient light
    }

    /**
     * helper for calc color
     * will add the global and local effects
     *
     * @param gp intersection point with geometry
     * @param ray from that intersects with geometry
     * @param level current recursion level
     * @param k transparency coefficient
     * @return
     */
    private Color calcColor(GeoPoint gp, Ray ray, int level, Double3 k) {
        //  Color color = gp.geometry.getEmission().add(calcLocalEffects(gp,ray,k));
        Color color = calcLocalEffects(gp, ray, k);
        return 1 == level ? color : color.add(calcGlobalEffects(gp, ray, level, k));
    }

    /***
     * calculates gobal effects
     * recursively traces the rays to determine the overall color
     *
     * @param gp intersection pt with geometry
     * @param ray that hits point
     * @param level current recursion level
     * @param k attenuation coefficent
     * @return sum of the colors from the two secondary rays
     */
    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
        //initialize color to black
        Color color = Color.BLACK;

        Vector norm = gp.geometry.getNormal(gp.point).normalize(); //find the norm of geometry and point
        Material mat = gp.geometry.getMaterial();


        Double3 kkr = k.product(mat.kR); //attenuation factor * reflection coefficient
        Double3 kkt = k.product(mat.kT); //attenuation factor * transparency coefficient

        if (!(kkr.lowerThan(MIN_CALC_COLOR_K))) //send the reflection if kkr > min_color
            color = color.add(calcGlobalEffects(constructReflectedRay(ray, gp, norm), level, mat.kR, kkr));

        if (!(kkt.lowerThan(MIN_CALC_COLOR_K))) //send the transparency if kkt > min_color
            color = color.add(calcGlobalEffects(constructRefractedRay(gp, ray, norm), level, mat.kT, kkt));

        return color;

    }

    /**
     * Calculates the global effect of reflection or refraction for a ray
     * @param ray in which to calculate global effect
     * @param level current recurison level
     * @param k     attenuation coeficient (global effect)
     * @param kx    global effect * materials reflection or refraction coefficient
     * @return color of the global effect
     */
    private Color calcGlobalEffects(Ray ray, int level, Double3 k, Double3 kx) {
        GeoPoint gp = findClosestIntersection(ray);
        return (gp == null ? scene.background : calcColor(gp, ray, level - 1, kx).scale(k));
    }

    /**
     * @param gp
     * @param ray
     * @return
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray, Double3 k) {
        Color color = gp.geometry.getEmission();
        Vector v = ray.getDirection();
        Vector n = gp.geometry.getNormal(gp.point);
        double nv = alignZero(n.dotProduct(v));
        //if direction is perpendicular to the surface, return emission color
        if (nv == 0) return color;

        Material material = gp.geometry.getMaterial();
        //iterate through all the light sources in the scene
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l)); //dot product of the vector's normal and vector's light source
            //check light source and view are on the same side of surface
            if (nl * nv > 0) {
                Double3 ktr = transparency(gp, lightSource, l, n, nv); //find the transparency of the geometry being hit
                if (!(k.product(ktr).lowerThan(MIN_CALC_COLOR_K))) {
                    Color iL = lightSource.getIntensity(gp.point).scale(ktr);
                    color = color.add(iL.scale(calcDiffusive(material, Math.abs(nl))),
                            iL.scale(calcSpecular(material, n, l, nl, v)));
                }
            }
        }
        return color;
    }

    /**
     * @param mat
     * @param nl
     * @return
     */
    private Double3 calcDiffusive(Material mat, double nl) {
        return mat.Kd.scale(nl);
    }

    /**
     * @param mat to get the specular coefficient (Ks) and nshininess
     * @param n
     * @param l
     * @param nl
     * @param v
     * @return
     */
    private Double3 calcSpecular(Material mat, Vector n, Vector l, double nl, Vector v) {
        // Calculate the specular coefficient raised to the power of the shininess factor.
        return mat.Ks.scale(Math.pow(-alignZero(v.dotProduct(l.subtract(n.scale(nl * 2)))), mat.nShininess));
    }

    /**
     * Method that constructs a reflected ray
     * direction vector = ð’— âˆ’(ðŸ * (ð’— dot ð’) * n)
     * @param ray    Ray
     * @param gp     Point
     * @param normal Vector
     * @return constructs a reflection ray
     */
    private Ray constructReflectedRay(Ray ray, GeoPoint gp, Vector normal) {
        Vector v = ray.getDirection();
        double vn = alignZero(v.dotProduct(normal));
        // If the dot product of v and n is zero, the incident vector is parallel to the surface normal
        if (vn == 0) { //there is no reflection
            return null;
        }
        return new Ray(gp.point, v.subtract(normal.scale(2 * vn)), normal);
    }

    /**
     * constructs a refraction ray
     * for our implementation refraction index is 1
     *
     * @param gp     Point
     * @param ray    Ray
     * @param normal Vector
     * @return
     */
    private Ray constructRefractedRay(GeoPoint gp, Ray ray, Vector normal) {
        // ray = direction vector because they will have the same n1 and n2
        return new Ray(gp.point, ray.direction, normal);
    }

    /**
     * calculate the transparency coefficient with light source
     *
     * @param gp    GeoPoint
     * @param light LightSource
     * @param l     light direction vector
     * @param n     normal vector
     * @return transparency coefficient
     */

    private Double3 transparency(GeoPoint gp, LightSource light, Vector l, Vector n, double nv) {

        //make a new light ray in the opposite direction
        Vector lightDir = l.scale(-1);
        Ray lightRay = new Ray(gp.point, lightDir, n);

        //find the intersections with this new light ray
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
        Double3 ktr = new Double3(1.0); //full transparency

        if (intersections == null) return ktr;

        //loop over intersections and for each intersection which is closer to the point than the light source,
        //multiply ktr by kT of its geometry

        // Compute the maximum distance to the light source
        double maxDistance = light.getDistance(gp.point);

        for (GeoPoint geopoint : intersections) {
            if (gp.point.distance(geopoint.point) <= maxDistance) {
                // Multiply the transparency coefficient by the transparency factor of the intersected geometry's material
                ktr = ktr.product(geopoint.geometry.getMaterial().kT);

                // If the transparency coefficient falls below the minimum calculation threshold, return a fully opaque value (0 transparency)
                if (ktr.lowerThan(MIN_CALC_COLOR_K)) {
                    return Double3.ZERO;
                }
            }

        }
        return ktr;

    }
}
