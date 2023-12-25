package primitives;

/**
 * Class Ray, contains a point head and direction vector
 * @author Shlomit and Jessica
 */
public class Ray {
    public Point head;
    public Vector direction;

    /**
     *
     * @param p
     * @param v
     */
    public Ray(Point p, Vector v) {
        head = p;
        direction = v.normalize();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return (obj instanceof Ray other)
                && this.head.equals(other.head)
                && this.direction.equals(other.direction);
    }
}