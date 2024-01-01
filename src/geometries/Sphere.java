package geometries;
import primitives.Point;
import primitives.Vector;

/**
 * Class Sphere, contains a center and extends Radial Geometry
 * @author Shlomit and Jessica
 */
public class Sphere extends RadialGeometry {

    Point center;

    /**
     *
     * @param rad
     */
    public Sphere(double rad) {
        super(rad);
    }

    /**
     *
     * @param p
     * @return the normal of the sphere with point p
     */
    @Override
    public Vector getNormal(Point p) {
        return null;
    }

    /**
     *
     * @return the info of the sphere as a string
     */
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