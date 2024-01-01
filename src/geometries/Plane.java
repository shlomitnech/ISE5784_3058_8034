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
        q0 = p1;
        normal = null; //change this to be normal at next stage!
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