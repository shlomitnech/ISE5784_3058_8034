package geometries;
/*
 * Plane (defined by a point and the orthogonal vector).
o Two Constructors:
▪ The first constructor will receive 3 points as parameters.
The constructor will calculate a normal vector from these points. (Recall what we
learned in class about the triangle.) For this stage of the project you do not need to
calculate the normal. You may assign the vector attribute the value null. (In the next
stage you will calculate the normal and refractor the code.) The constructor should
choose one of the points that it received as a parameter and save it in the point attribute.
▪ The second constructor receives a point and a vector. The vector that was received as a
parameter may not be normalized, therefore its value must first be normalized before it
can be stored in the vector attribute.
 * */
import primitives.Point;
import primitives.Vector;

public class Plane {

    //variables:

    Point q0;
    Vector normal;

    //constructors:

    public Plane( Point p1, Point p2, Point p3) {
//		Vector r1 = p1.subtract(p2);
//		Vector r2 = p1.subtract(p2);
//		Vector normal = r1.crossProduct(r2);
        q0 = p1;
        normal = null;
    }

    public Plane(Point p1, Vector v) {
        q0 = p1;
        normal = v.normalize();
    }

    //getters:

    public Point getq0() {
        return q0;
    }

    public Vector getNormal() {
        return normal;
    }

}