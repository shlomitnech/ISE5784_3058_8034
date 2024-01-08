package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public class RadialGeometry implements Geometry{

    protected double radius = 0;

    public RadialGeometry(double rad) {
        if (rad <= 0)
            throw new IllegalArgumentException("Radius must be greater than zero");
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


    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}