package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Tube extends RadialGeometry {

    Ray axisRay;
    double radius;

    public Tube(double rad) {
        super(rad);
    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }

    /*
    public Ray getAxisRay() {
        return axisRay;
    }
    public double getRadius() {
        return radius;
    }
    */

}