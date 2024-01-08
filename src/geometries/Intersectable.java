package geometries;
import primitives.*;

import java.util.List;

/***
 * Interface intersectable
 */
public interface Intersectable {
    /***
     *
     * @param ray
     * @return List of points
     */
    List<Point> findIntersections(Ray ray);

}
