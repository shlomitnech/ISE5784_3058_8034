package geometries;
import primitives.*;

import java.util.List;

/***
 * Interface intersectable
 */
public abstract class Intersectable {
    /***
     *
     * @param ray
     * @return List of points
     */
    public final List<Point> findIntersections(Ray ray)
    {
        List<GeoPoint> geoList = findGeoIntersections(ray);
        return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
    }

    /**
     *
     * @param ray
     * @return
     */
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersectionsHelper(ray);
    }

    /**
     *
     * @param ray
     * @return
     */
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);

    /**
     * PDS
     * Geometry Point class
     */
    public static class GeoPoint {
        public Geometry geometry; //the geometry which the point is on
        public Point point; //the point on the geometry

        /**
         * Constructor for GeoPoint
         * @param geometry the geometry
         * @param point    the point itself
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            return obj instanceof GeoPoint other && (this.point == other.point) && (this.geometry == other.geometry);
        }

        @Override
        public String toString() {
            return this.point + " " + this.geometry;
        }
    }


}
