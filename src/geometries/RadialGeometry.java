package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public abstract class RadialGeometry extends Geometry{

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
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        return null;
    }
}