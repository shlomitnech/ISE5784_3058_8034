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


    public Plane( Point p1, Point p2, Point p3) {
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
    public String toString(){
        return String.format("Point: " + q0 + ", Normal: " + normal);
    }
}