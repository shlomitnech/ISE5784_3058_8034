package geometries;

import primitives.Point;
import primitives.Vector;

public class RadialGeometry implements Geometry{

    protected double radius = 0;

    public RadialGeometry(double rad) {
        radius = rad;
    }

    /**
     *
     * @param p
     * @return the normal
     */
    public Vector getNormal(Point p) {
        return null;
    }

    /**
     *
     * @return the info of the radial geometry in a string
     */
    public String toString(){
        return String.format("Radius: " + radius);
    }


}