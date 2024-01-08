package primitives;
import java.lang.*;

/***
 * Point class contains a double3, a point in space
 */
public class Point {
    public static final Point ZERO = new Point(0, 0, 0);
    protected Double3 xyz = null;

    //constructors:
    public Point(Double3 d3){xyz = d3;}
    public Point( double d1, double d2, double d3){
        xyz = new Double3(d1,d2,d3);
    }
    
    /****
     *
     * @param obj - an object
     * @return true if obj equals this point, else return false
     */
    @Override
    public boolean equals(Object obj) {
        if (this==obj) return true;
        return (obj instanceof Point other)
                && this.xyz.equals(other.xyz);
    }

    /***
     * no params
     * @return a string depicting the point
     */
    @Override
    public String toString() {return xyz.toString();}

    /***
     * adds a vector to the point. Returns a new point.
     * @param v - Vector
     * @return new point
     */
    public Point add(Vector v)
    {
        return new Point(xyz.add(v.xyz));
    }

    /***
     *
     * @param p - Point
     * @return receives a Point as a parameter, returns a vector from second point to the point which
     */

    public Vector subtract(Point p) {
        return new Vector(xyz.subtract(p.xyz));
    }

    /***
     *
     * @return the distance squared
     */
    public double distanceSquared(Point other) {
        return (xyz.d1-other.xyz.d1)*(xyz.d1-other.xyz.d1) + (xyz.d2 -other.xyz.d2)*(xyz.d2-other.xyz.d2)
                + (xyz.d3 - other.xyz.d3)*(xyz.d3-other.xyz.d3);
    }
    /***
     *
     * @return the distance
     */
    public double distance(Point other) {
        return Math.sqrt(distanceSquared(other));
    }
}