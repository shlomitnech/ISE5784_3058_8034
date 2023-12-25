package geometries;
import primitives.*;

public interface Geometry {

    /***
     *
     * @param p
     * @return the normal from the point
     */
    public  Vector getNormal(Point p);

}