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

    /***
     * Get the color from ambient light and intensity
     * calls helper function
     * @param gp intersection point
     * @param ray
     * @returns color
     */
    private Color calcColor(GeoPoint gp, Ray ray) {
        return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K )
                .add(scene.ambientLight.getIntensity());
    }

    /**
     * helper for calc color
     * @param gp
     * @param ray
     * @param level
     * @param k
     * @return
     */
    private Color calcColor(GeoPoint gp, Ray ray, int level, Double3 k) {
       Color color = gp.geometry.getEmission().add(calcLocalEffects(gp,ray,k));
        return 1 == level ? color : color.add(calcGlobalEffects(gp, ray, level, k));
    }

    /***

     * @param gp
     * @param ray
     * @param level
     * @param k attenuation coefficent
     * @return sum of the colors from the two secondary rays
     */
    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k){
        primitives.Color color = new primitives.Color(Color.BLACK.getColor()); //start withthe black color
        Vector norm = gp.geometry.getNormal(gp.point).normalize(); //find the norm of geometry and point
        Material mat = gp.geometry.getMaterial();

        Double3 kkr = k.product(mat.kR); //attenuation factor * reflection coefficient
        Double3 kkt = k.product(mat.kT); //attenuation factor * transparency coefficient

        if(!(kkr.lowerThan(MIN_CALC_COLOR_K))) //send the reflection if kkr > min_color
            color = color.add(calcGlobalEffects(constructReflectedRay(ray,gp,norm), level, mat.kR, kkr));

        if(!(kkt.lowerThan(MIN_CALC_COLOR_K))) //send the transparency if kkt > min_color
            color = color.add( calcGlobalEffects(constructRefractedRay(gp,ray,norm), level, mat.kT, kkt));
        return color;

    }

    /**
     * method calculates the color of the ray by calculating the intersection point closest
     *  to the beginning of the ray and calc its color by calling a recursive method calcColor
     *
     * @param ray
     * @param level
     * @param k attenuation coeficient
     * @param kx reflected coefficient or transparency coefficient
     * @return
     */
    private Color calcGlobalEffects(Ray ray, int level, Double3 k, Double3 kx){
        GeoPoint gp = findClosestIntersection(ray);
        return(gp == null ? scene.background : calcColor(gp, ray, level-1, kx).scale(k));
    }
    /**
     *
     * @param gp
     * @param ray
     * @return
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray, Double3 k){
        Color color = gp.geometry.getEmission();
        Vector v = ray.getDirection();
        Vector n = gp.geometry.getNormal(gp.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return color;
        Material material = gp.geometry.getMaterial();
        //if the light source is the same side as camera
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l)); //dot product of the vector's normal and vector's light source
            if (nl * nv > 0) {
                Double3 ktr = transparency(gp, lightSource, l,n); //find the transparency of the geometry being hit
                if(!(k.product(ktr).lowerThan(MIN_CALC_COLOR_K))){
                    Color iL = lightSource.getIntensity(gp.point).scale(ktr);
                    color = color.add(iL.scale(calcDiffusive(material,Math.abs(nl))),
                            iL.scale(calcSpecular(material,n,l,nl, v)));
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
    private Double3 calcDiffusive(Material mat, double nl) { return mat.Kd.scale(nl); }
    /**
     * @param mat
     * @param n
     * @param l
     * @param nl
     * @param v
     * @return
     */
    private Double3 calcSpecular(Material mat, Vector n, Vector l, double nl, Vector v) {
        return mat.Ks.scale(Math.pow((Math.max(0, -v.dotProduct(l.subtract(n.scale(nl * 2))))),mat.nShininess));
    }
    /**
     * Method that constructs a reflected ray
     * @param ray Ray
     * @param gp Point
     * @param normal Vector
     * @return constructs a reflection ray
     */
    private Ray constructReflectedRay(Ray ray, GeoPoint gp, Vector normal) {
        //direction ray = 𝒗−(𝟐 * (𝒗*𝒏) * n)
        return new Ray(gp.point, ray.direction.subtract((normal.scale(normal.dotProduct(ray.direction))).scale(2)),  normal);
    }

    /**
     * constructs a refraction ray
     * for our implementation refraction index is 1
     * @param gp Point
     * @param ray Ray
     * @param normal Vector
     * @return
     */
    private Ray constructRefractedRay(GeoPoint gp, Ray ray, Vector normal) {
        // ray = direction vector because they will have the same n1 and n2
        return new Ray(gp.point, ray.direction,  normal);
    }

    /**
     * check if a geometry is blocking this current geometry
     * @param gp GeoPoint
     * @param light LightSource
     * @param l Vector
     * @param n Vector
     * @return Double3
     */

    private Double3 transparency(GeoPoint gp, LightSource light, Vector l, Vector n){

        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(gp.point, lightDirection,n);

        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
        if (intersections == null) return new Double3(1.0);

        Double3 ktr = new Double3(1.0);

        double rayLightDistance = light.getDistance(lightRay.head);
        //loop over intersections and for each intersection which is closer to the point than the light source,
        //multiply ktr by kT of its geometry
        for (GeoPoint geopoint : intersections) {
            double rayIntersectionDistance = lightRay.head.distance(geopoint.point);
            if (rayIntersectionDistance < rayLightDistance) return geopoint.geometry.getMaterial().kT.product(ktr);
        }
        return ktr;
    }

}
