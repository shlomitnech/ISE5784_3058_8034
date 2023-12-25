package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * This class represents a cylinder which inherits from Tube and implements Radial Geometry
 * @author Shlomit and Jessica
 */
public class Cylinder extends Tube {

    double height;

    public Cylinder(double rad) {
        super(rad);
    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }

    public String toString(){
        return String.format("Height: " + height);
    }
    /*
    public double getHeight() {
        return height;
    }

     */
}