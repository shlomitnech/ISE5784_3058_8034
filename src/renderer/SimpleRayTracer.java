package renderer;

import primitives.*;
import geometries.*;
import elements.*;
import java.util.List;

public class SimpleRayTracer extends RayTraceBase {
    /***
     *
     */
    public Color traceRay(Ray ray) {
        List<Point> intersections = this.scene.geometries.findIntersections(ray);
        Point closestPoint = ray.findClosestPoint(intersections);
        return calcColor(closestPoint);
    }

    /***
     *
     * @param p
     * @return
     */
    private Color calcColor(Point p) {
        return this.scene.ambientLight.getIntensity();
    }
}