package renderer;

import geometries.Intersectable;
import primitives.Color;
import primitives.Ray;
import scene.Scene;

public class MultiRayTracer extends RayTraceBase {
    /**
     * constructor for RayTraceBase
     *
     * @param scene
     */
    public MultiRayTracer(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        Intersectable.GeoPoint closestPoint = findClosestIntersection(ray); //return the closest GeoPoint that the ray hits
        return closestPoint == null ? scene.background : calcColor(closestPoint, ray);
    }


}
