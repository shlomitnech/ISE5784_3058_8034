package primitives;

import geometries.Intersectable.GeoPoint;
import java.util.List;
import renderer.*;

import static java.lang.Double.MAX_VALUE;
import static primitives.Util.isZero;

/**
 * Class Ray, contains a point head and direction vector
 * @author Shlomit and Jessica
 */
public class Ray {
    private static final double DELTA = 0.1;
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

    /**
     * constructs rays that hit objects (reconstructed rays)
     * @param p
     * @param dir
     * @param normal
     */
    public Ray(Point p, Vector dir, Vector normal){
        direction = dir.normalize();
        double nv = normal.dotProduct(direction);
        Vector delta  =normal.scale(DELTA); //moved here as to not violate DRY (we use this delta for shadow, reflected and refracted)
        if (nv < 0)
            delta = delta.scale(-1);
        this.head = p.add(delta);
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
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }
    /**
     * returns the closest geo point from list of GeoPoints
     *
     * @param points GeoPoints to check
     * @return closest GeoPoint
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> points) {
        GeoPoint closest = null;
        double closestDistance = Integer.MAX_VALUE;
        if (points == null || points.isEmpty())
            return null;
        for (GeoPoint point : points) {
            if (point.point.distanceSquared(head) < closestDistance) {
                closest = point;
                closestDistance = point.point.distanceSquared(head);
            }
        }
        return closest;
    }

}

