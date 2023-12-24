package primitives;
public class Ray {
    public Point p0;
    public Vector dir;

    //	Constructor: receives a point and a vector. The vector that is passed as a parameter may not have been
//	normalized. Therefore, it is important to normalize the parameter before its value is saved in the class’
//	attribute.
    public Ray(Point p, Vector v) {
        p0 = p;
        dir = v.normalize();
    }

    public boolean equals(Object obj)
    {
        if (this==obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Point)) //if the object is not a point
            return false;
        Point other = (Point)obj;
        return super.equals(other);

    }

}