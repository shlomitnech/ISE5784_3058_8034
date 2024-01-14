package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

/***
 * Geometries class that implements intersectable interface
 */
public class Geometries implements Intersectable {
    List<Intersectable> geometries;
    public Geometries() {
        geometries = new LinkedList<>();
    }

    public Geometries(Intersectable... geometries) {
        this.geometries = new LinkedList<>(List.of(geometries));
    }

    /***
     * return the addition of the geometries
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
    public List<Point> findIntersections(Ray ray) {
        List<Point> result = null;
        List<Point> toAdd = null;
        for (Intersectable geo : geometries) {
            toAdd = geo.findIntersections(ray);
            if (toAdd != null) {
                if(result==null) {
                    result = new LinkedList<>();
                }
                result.addAll(toAdd);
            }
        }
        return result;
    }


}
