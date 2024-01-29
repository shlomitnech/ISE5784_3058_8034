package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

/***
 * Geometries class that implements intersectable interface
 */
public class Geometries extends Intersectable {
    List<Intersectable> geometries;
    /**
     * Constructs a new empty geometries object
     * composite of intersectable objects
     *
     */
    public Geometries() {
        geometries = new LinkedList<>();
    }
/**
 * Constructs new geometetries object as a copmosite of interstable
 * objects
 */
    public Geometries(Intersectable... geometries) {
        this.geometries = new LinkedList<>(List.of(geometries));
    }

    /***
     * add the addition of the geometries
     * @param geometries
     */
    public void add(Intersectable... geometries) {
        this.geometries.addAll(List.of(geometries));
    }
    /***
     *
     * @param ray
     * @return the list of intersections
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<GeoPoint> result = null;
        List<GeoPoint> toAdd = null;
        for (Intersectable geo : geometries) {
            toAdd = geo.findGeoIntersections(ray);
            if (toAdd != null) {
                if(result==null) { // checks if this is the first point on my list
                    result = new LinkedList<>();
                }
                result.addAll(toAdd);
            }
        }
        return result;
    }


}
