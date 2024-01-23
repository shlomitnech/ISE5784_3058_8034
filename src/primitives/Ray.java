package primitives;

import java.util.List;

import static java.lang.Double.MAX_VALUE;

/**
 * Class Ray, contains a point head and direction vector
 * @author Shlomit and Jessica
 */
public class Ray {
    public Point head;
    public Vector direction;

    /**
     * @param p
     * @param v
     */
    public Ray(Point p, Vector v) {
        head = p;
        direction = v.normalize();
    }

    /***
     *
     * @param obj - an Object
     * @return true if the object equals this ray, else return false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return (obj instanceof Ray other)
                && this.head.equals(other.head)
                && this.direction.equals(other.direction);
    }

    //Return the P0 point of ray
    public Point getHead() {
        return head;
    }

    /**
     * @param t
     * @return  //point of how and add how far away t is from starting point
     * (The method calculates a point on the beam line, at a given
     * distance from the beam’s head)
     */
    public Point getPoint(double t) {
        return (head.add(direction.scale(t)));
    }
    public Vector getDirection() {
        return direction;
    }

    /**
     * Finds the closest point
     * @param points //collection of points
     * @return closest point to ray's head
     */
    public Point findClosestPoint(List<Point> points ) {
        double closestDistance = MAX_VALUE;
        if (points.isEmpty())
                return null;

        //If there are values in the points list
        Point closestPoint = points.getFirst(); //set the first point
        for (Point p : points){
            if (p.distance(this.head) < closestDistance) { //closer than previous points
                closestDistance = p.distance((this.head));
                closestPoint = p;
            }
        }
        return closestPoint;

    }
}

