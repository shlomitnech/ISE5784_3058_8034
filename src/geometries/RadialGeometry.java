package geometries;

import primitives.Point;
import primitives.Vector;

public class RadialGeometry implements Geometry{

    protected double radius = 0;

    public RadialGeometry(double rad) {
        radius = rad;
    }
    public Vector getNormal(Point p) {
        return null;
    }



}