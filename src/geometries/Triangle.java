package geometries;
import primitives.*;

import java.util.ArrayList;
import java.util.List;

/*
  Class Triangle, inherits from Polygon and has three vertices
  @author Shlomit and Jessica
 */
public class Triangle extends Polygon {

    public Triangle(Point p1, Point p2, Point p3) {
        super(p1,p2,p3);
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        // Find the intersection point with the triangle's plane
        List<Point> planeIntersections = this.plane.findIntersections(ray);
        if (planeIntersections == null) {
            return null; // No intersection with the plane, so no intersection with the triangle
        }
        List<GeoPoint> intersections = new ArrayList<>();

        // Extract the intersection point from the plane
        Point p = planeIntersections.getFirst();

        // Calculate barycentric coordinates
        Vector v0 = vertices.get(1).subtract(vertices.get(0));
        Vector v1 = vertices.get(2).subtract(vertices.get(0));
        Vector v2 = p.subtract(vertices.get(0));

        double dot00 = v0.dotProduct(v0);
        double dot01 = v0.dotProduct(v1);
        double dot02 = v0.dotProduct(v2);
        double dot11 = v1.dotProduct(v1);
        double dot12 = v1.dotProduct(v2);

        double denominator = dot00 * dot11 - dot01 * dot01;

        if (Util.isZero(denominator)) {
            return null; // The triangle is degenerate
        }

        double u = (dot11 * dot02 - dot01 * dot12) / denominator;
        double v = (dot00 * dot12 - dot01 * dot02) / denominator;

        // The intersection point is inside the triangle
        if (u >= 0 && v >= 0 && (u + v) <= 1) {
            intersections.add(new GeoPoint(this, p));
        }
        return intersections.isEmpty() ? null : intersections;
    }
}
