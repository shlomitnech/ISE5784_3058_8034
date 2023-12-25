package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane {
    private final Point q0;
    private final Vector normal;


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
     *
     * @return the normal of the vector
     */
    public Vector getNormal() {
        return normal;
    }

    public String toString(){
     //   return print("Head: " + p0 + " normal");
        return null;
    }
}