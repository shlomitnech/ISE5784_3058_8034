package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/***
 * Class Tube, inherits from Radial Geometry and contains an axisRay and radius
 */
public class Tube extends RadialGeometry {

    Ray axisRay;

    public Tube(double rad, Ray a) {

        super(rad);
        axisRay = a;
    }

    /**
     *
     * @param p
     * @return the normal at point p
     */
    @Override
    public Vector getNormal(Point p) {
        double distance = axisRay.getDirection().dotProduct(p.subtract(axisRay.getHead()));
        Point hit;
        if (distance != 0)
            hit = axisRay.getHead().add(axisRay.getDirection().scale(distance));
        else
            hit = axisRay.getHead();
        return p.subtract(hit).normalize();

    }

    /**
     *
     * @return info of the tube in a string
     */
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