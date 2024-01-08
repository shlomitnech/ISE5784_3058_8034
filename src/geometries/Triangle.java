package geometries;
import java.util.ArrayList;
import java.util.List;
import primitives.*;
import geometries.*;


/*
  Class Triangle, inherits from Polygon and has three vertices
  @author Shlomit and Jessica
 */
public class Triangle extends Polygon {

    public Triangle(Point p1, Point p2, Point p3) {
        super(p1,p2,p3);
    }
}
public Vector getNormal(Point point) { return plane.getNormal(); }

public List<Point> findIntersections(Ray ray) {
    // Find the intersection point with the triangle's plane
    List<Point> planeIntersections = this.plane.findIntersections(ray);
    if (planeIntersections == null) {
        return null; // No intersection with the plane, so no intersection with the triangle
    }

    List<Point> intersections = new ArrayList<>();

    // Extract the intersection point from the plane
    Point p = planeIntersections.get(0);

    // Calculate barycentric coordinates
    Vector v0 = vertices.get(1).subtract(vertices.get(0));
    Vector v1 = vertices.get(2).subtract(vertices.get(0));
    Vector v2 = p.subtract(vertices.get(0));

    double dot00 = v0.dotProduct(v0);
    double dot01 = v0.dotProduct(v1);
    double dot02 = v0.dotProduct(v2);
    double dot11 = v1.dotProduct(v1);
    double dot12 = v1.dotProduct(v2);

    double denominator = dot00 * dot11 - dot01 * dot01;

    if (denominator == 0) {
        return null; // The triangle is degenerate
    }

    double u = (dot11 * dot02 - dot01 * dot12) / denominator;
    double v = (dot00 * dot12 - dot01 * dot02) / denominator;

    if (u >= 0 && v >= 0 && (u + v) <= 1) {
        // The intersection point is inside the triangle
        intersections.add(p);
    }

    return intersections.isEmpty() ? null : intersections;
}

/*
	public List<Point> findIntersections(Ray ray)
	{

		Vector v1 = vertices.get(0).subtract(ray.p0);
		Vector v2 = vertices.get(1).subtract(ray.p0);
		Vector v3 = vertices.get(2).subtract(ray.p0);

		Vector n1 = v1.crossProduct(v2).normalize();
		Vector n2 = v2.crossProduct(v3).normalize();
		Vector n3 = v3.crossProduct(v1).normalize();

		double dot1 = v1.dotProduct(n1);
		double dot2 = v2.dotProduct(n2);
		double dot3 = v3.dotProduct(n3);
//the point is inside if all dot products have the same sign
		 if(dot1>0 && dot2>0 && dot3>0 || dot1<0 && dot2<0 && dot3<0) {
			 return plane.findIntersections(ray);
		 }
		 else
			 return null;

	}
 */