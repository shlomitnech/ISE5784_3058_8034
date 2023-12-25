package geometries;
import primitives.Point;
import primitives.Vector;

/**
 * Class Sphere, contains a center and extends Radial Geometry
 * @author Shlomit and Jessica
 */
public class Sphere extends RadialGeometry {

    Point center;

    public Sphere(double rad) {
        super(rad);
    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }

    @Override
    public String toString(){
        return super.toString() + String.format("Center: " + center);
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