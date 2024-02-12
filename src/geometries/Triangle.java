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
        var planeIntersections = this.plane.findGeoIntersections(ray);
        if (planeIntersections == null) {
            return null; // No intersection with the plane, so no intersection with the triangle
        }
        // Calculate barycentric coordinates
        Vector v1 = vertices.get(0).subtract(ray.getHead()).normalize();
        Vector v2 = vertices.get(1).subtract(ray.getHead()).normalize();
        Vector v3 = vertices.get(2).subtract(ray.getHead()).normalize();

        Vector n1 = v1.crossProduct(v2).normalize();
        Vector n2 = v2.crossProduct(v3).normalize();
        Vector n3 = v3.crossProduct(v1).normalize();

        double ans1 = ray.getDirection().dotProduct(n1);
        double ans2 = ray.getDirection().dotProduct(n2);
        double ans3 = ray.getDirection().dotProduct(n3);

        if((ans1>0 && ans2>0 && ans3>0) || (ans1<0 && ans2<0 && ans3<0)) {
            //return this.plane.findGeoIntersectionsHelper(ray);

            //reassigning geo of geoPoint to be the  triangle, because it calls the funcitom
            //of the plane, which does not have an emission color
            List<GeoPoint> result = this.plane.findGeoIntersectionsHelper(ray);
            for (GeoPoint gp : result) {
                gp.geometry = this;
            }
            return result;
        }
        else {
            return null;
        }
    }
}

