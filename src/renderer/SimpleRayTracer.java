package renderer;

import primitives.*;
import geometries.*;
import lighting.*;
import scene.*;
import geometries.Intersectable.GeoPoint;

import java.util.List;

public class SimpleRayTracer extends RayTraceBase {

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
        return calcColor(closestPoint);
    }

    /***
     * Get the color from ambient light and intensity
     * @param p point
     * @returns color
     */
    private Color calcColor(GeoPoint p) {

        return this.scene.ambientLight.getIntensity().add(p.geometry.getEmission());


//add on to this from the instructions
    }
}