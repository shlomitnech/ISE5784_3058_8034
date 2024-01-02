package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Plane Class implements the Geometry interface, represents a plane
 * @author Shlomit and Jessica
 */
public class Plane {
    private final Point q0;
    private final Vector normal;

    /***
     *
     * @param p1
     * @param p2
     * @param p3
     */
    public Plane( Point p1, Point p2, Point p3) {

        if(p1 == p2 || p1 == p3 || p2 == p3){
            throw new IllegalArgumentException("Error: two points are the same");
        }

//        public static boolean areCollinear(Point3D p1, Point3D p2, Point3D p3) {
//            // Calculate the slopes between pairs of points
//            double slope1 = (p2.d2 - p1.d2) / (p2.d1 - p1.d1);
//            double slope2 = (p3.d2 - p2.d2) / (p3.d1 - p2.d2);
//
//            // If the slopes are equal, the points are collinear
//            return Double.compare(slope1, slope2) == 0;
//        }

        Vector v1 = p2.subtract(p1);
        Vector v2 = p3.subtract(p1);
        normal = v1.crossProduct(v2).normalize();
        q0 = p2;

    }

    public Plane(Point p1, Vector normalV) {
        q0 = p1;
        normal = normalV.normalize();
    }

    //getters:

    /***
     *
     * @return the head
     */
    public Point getq0() {
        return q0;
    }

    /**
     *No paramter
     * @return the normal of the vector
     */
    public Vector getNormal() {
        return normal;
    }

    /**
     * with paramteter
     * @param p
     * @returns the normal
     */
    public Vector getNormal(Point p){
        return normal;
    }

    /***
     *
     * @return the info of the plane in a string
     */
    public String toString(){
        return String.format("Point: " + q0 + ", Normal: " + normal);
    }
}