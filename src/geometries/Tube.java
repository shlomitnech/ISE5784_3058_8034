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

    /**
     *
     * @param p
     * @return the normal at point p
     */
    @Override
    public Vector getNormal(Point p) {
        // Finding the nearest point to the given point that is on the axis ray
        double distance = this.axisRay.getDirection().dotProduct(p.subtract(axisRay.getHead()));
        Point hit = this.axisRay.getHead().add(axisRay.getDirection().scale(distance));
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