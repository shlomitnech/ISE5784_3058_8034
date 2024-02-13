package renderer;

import primitives.*;
import lighting.*;
import scene.*;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Util.alignZero;

public class SimpleRayTracer extends RayTraceBase {

    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final Double3 INITIAL_K = new Double3(1.0);

    private static final double DELTA = 0.1;

    public SimpleRayTracer(Scene s) {
        super(s);
    }

    /***
     *return the color of the closest point
     */
    public Color traceRay(Ray ray) {
        List<GeoPoint> intersections = this.scene.geometries.findGeoIntersections(ray);
        if (intersections == null)
            return scene.background;
        GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
        return calcColor(closestPoint, ray);
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
        primitives.Color color = gp.geometry.getEmission().add(calcLocalEffects(gp,ray));
        //if level == 1 return the color
        // else calculate the global effects
        return level == 1 ? color : color.add(calcGlobalEffects(gp,ray,level,k));
    }


    /**
     *
     * @param gp
     * @param ray
     * @return
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray){
        Color color = gp.geometry.getEmission();
        Vector v = ray.getDirection();
        Vector n = gp.geometry.getNormal(gp.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return color;
        Material material = gp.geometry.getMaterial();
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l)); //dot product of the vector's normal and vector's light source
            if (nl * nv > 0) {
                if (unshaded(gp, l, n, nl, lightSource)) {
                    Color iL = lightSource.getIntensity(gp.point);
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
    private boolean unshaded(GeoPoint gp, Vector l, Vector n, double nl, LightSource light){
        Vector lightDirection = l.scale(-1); // from point to light source
        Vector deltaV = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : - DELTA);
        Point p = gp.point.add(deltaV);

        Ray lightRay = new Ray(p, lightDirection);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);

        if (intersections == null) return true;

        double rayLightDistance = light.getDistance(lightRay.head);
        for (GeoPoint geopoint : intersections) {
            double rayIntersectionDistance = lightRay.head.distance(geopoint.point);
            if (rayIntersectionDistance < rayLightDistance) return false;
        }
        return true;  //nothing in between geo and lightsource

    }
    /**
     * Method that constructs a reflected ray
     * @param ray Ray
     * @param point Point
     * @param normal Vector
     * @return constructs a reflection ray
     */
    private Ray constructReflectedRay(Ray ray, Point point, Vector normal) {
        //direction ray = 𝒗−(𝟐 * (𝒗*𝒏) * n)
        return new Ray(point, ray.direction.subtract((normal.scale(normal.dotProduct(ray.direction))).scale(2)),  normal);
    }

    /**
     * constructs a refraction ray
     * for our implementation refraction index is 1
     * @param point Point
     * @param ray Ray
     * @param normal Vector
     * @return
     */
    private Ray constructRefractedRay(Point point, Ray ray, Vector normal) {
        // ray = direction vector because they will have the same n1 and n2
        return new Ray(point, ray.direction,  normal);
    }


}
