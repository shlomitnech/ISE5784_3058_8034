package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * This class represents a cylinder which inherits from Tube and implements Radial Geometry
 * @author Shlomit and Jessica
 */
public class Cylinder extends Tube {

    double height;

    public Cylinder(double rad, Ray r) {
       super(rad, r);

    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }

    public String toString(){
        return String.format("Height: " + height);
    }

}