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
        List<GeoPoint> intersections = this.scene.geometries.findGeoIntersections(ray);
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
     *
     * @param mat
     * @param nl
     * @return
     */
    private Double3 calcDiffusive(Material mat, double nl) {
        return mat.Kd.scale(nl);
    }

    /**
     *
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
}
