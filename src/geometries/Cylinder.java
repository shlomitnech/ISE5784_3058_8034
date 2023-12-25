package geometries;

import primitives.Point;
import primitives.Vector;

public class Cylinder extends Tube {

    double height;

    public Cylinder(double rad) {
        super(rad);
    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }

    /*
    public double getHeight() {
        return height;
    }

     */
}