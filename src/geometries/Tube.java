package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/***
 * Class Tube, inherits from Radial Geometry and contains an axisRay and radius
 */
public class Tube extends RadialGeometry {

    Ray axisRay;
    double radius;

    public Tube(double rad, Ray a) {

        super(rad);
        axisRay = a;
    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }

    public String toString(){
        return String.format("Radius: " + radius);
    }

    public Ray getAxisRay() {
        return axisRay;
    }
    public double getRadius() {
        return radius;
    }

}