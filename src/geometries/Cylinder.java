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

    public Cylinder(double rad, Ray r, double h) {
       super(rad, r);
       if (h <= 0 )
           throw new IllegalArgumentException("Height must be greater than 0");
       height = h;
    }
  /*
    @Override
    public Vector getNormal(Point p) {

        Vector direction = axisRay.getDirection().normalize();
        Point head = axisRay.getHead();
        // Check that surface point is diff than head of axisRay to avoid creating a zero vector
        if (p.equals(head))
            return direction.scale(-1);
        double t = direction.dotProduct(p.subtract(head));
        // Finds out if surface point is on a base
        if (t == 0 || t == height)
            return direction.scale(t == 0 ? -1 : 1);
        // If surface point is on the side of the cylinder
        return super.getNormal(p);
    }
*/

    public String toString(){
        return String.format("Height: " + height);
    }

}