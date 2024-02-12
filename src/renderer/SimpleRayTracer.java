package renderer;

import primitives.*;
import lighting.*;
import scene.*;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Util.alignZero;

public class SimpleRayTracer extends RayTraceBase {

    private static final double DELTA = 0.1;

    public SimpleRayTracer(Scene s) {
        super(s);
    }

    /***
     *return the color of the closest point
     */
    public Color traceRay(Ray ray) {
        List<GeoPoint> intersections = this.scene.geometries.findGeoIntersectionsHelper(ray);
        if (intersections == null)
            return scene.background;
        GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
        return calcColor(closestPoint, ray);
    }

    /***
     * Get the color from ambient light and intensity
     * @param p intersection point
     * @returns color
     */
    private Color calcColor(GeoPoint p, Ray ray) {

        return this.scene.ambientLight.getIntensity().add(calcLocalEffects(p,ray));
    }

    private Color calcLocalEffects(GeoPoint gp, Ray ray){
        Color color = gp.geometry.getEmission();
        Vector v = ray.getDirection();
        Vector n = gp.geometry.getNormal(gp.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return color;
        Material material = gp.geometry.getMaterial();
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) {
                Color iL = lightSource.getIntensity(gp.point);
                color = color.add(iL.scale(calcDiffusive(material, Math.abs(nl))),
                        iL.scale(calcSpecular(material, n, l, nl, v)));
            }
        }
        return color;
    }
    private Double3 calcDiffusive(Material mat, double nl) {
        return mat.Kd.scale(nl);
    }

    private Double3 calcSpecular(Material mat, Vector n, Vector l, double nl, Vector v) {

        //if (mat.nShininess > 0)
        return mat.Ks.scale(Math.pow((Math.max(0, -v.dotProduct(l.subtract(n.scale(nl * 2))))),mat.nShininess));
       // handle the case if the shininess is a negative number
//        double m = Math.max(0, -v.dotProduct(l.subtract(n.scale(nl * 2))));
//        double result = 1;
//        for (int i = 0; i > mat.nShininess; --i)
//            result /= m;
//        return mat.Ks.scale(result);

    }
    private boolean unshaded(GeoPoint gp, LightSource light, Vector l, Vector n, double nl){
        Vector lightDirection = l.scale(-1); //from point to light source
        Vector deltaVector = n.scale(nl < 0 ? DELTA : -DELTA); //this will flip the normal's direction
        Point point = gp.point.add(deltaVector);
        Ray lightRay = new Ray(point, lightDirection);

        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);

        return scene.geometries.findGeoIntersectionsHelper(lightRay, light.getDistance(gp.point)) == null;

//        in the unshaded method, if the list of cuts received
//        is not empty - we will go through the list and if we
//        encountered a cut that is Closer to the top of the beam than the
//        distance between the point and the light source
//        - we will return false
    }

}