package geometries;
import primitives.*;

/**
 * Interface that all the Geometries use
 * @author Shlomit and Jessica
 */
public interface Geometry {

    /***
     *
     * @param p
     * @return the normal from the point
     */
    public  Vector getNormal(Point p);

}