package geometries;
import primitives.Point;
import primitives.Vector;

public class Sphere extends RadialGeometry {

    Point center;

    public Sphere(double rad) {
        super(rad);
    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }

	/*
    public Point getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }
    */

}