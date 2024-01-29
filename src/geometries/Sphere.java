package geometries;
import java.util.ArrayList;
import java.util.List;

import primitives.*;

/**
 * Class Sphere, contains a center and extends Radial Geometry
 * @author Shlomit and Jessica
 */
public class Sphere extends RadialGeometry {

    Point center;

    public Sphere(Point c, double rad) {
        super(rad);
        center = c;
    }

    @Override
    public Vector getNormal(Point p) {

        return p.subtract(center).normalize();

    }

    @Override
    public String toString(){
        return super.toString() + String.format("Center: " + center);
    }
	/*
    public Point getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }
    */

    /**
     * Method to find the intersections of a sphere
     */
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {

        List<GeoPoint> intersections = new ArrayList<>();
        Vector u = this.center.subtract(ray.getHead());
        double tm = ray.getDirection().dotProduct(u);
        double d = Math.sqrt(u.dotProduct(u) - tm * tm);

        if (d > radius) {
            return null; // No intersections
        }
        else
        {
            double th = Math.sqrt(radius * radius - d * d);
            double t1 = tm - th;
            double t2 = tm + th;

            if (t1 > 0) {
                intersections.add(new GeoPoint(this, ray.getPoint(t1)));
            }
            if (t2 > 0 && t2 != t1) {
                intersections.add(new GeoPoint(this,ray.getPoint(t2)));
            }
            if (intersections.isEmpty()) {
                return null;
            }
            return intersections;
        }
    }


}