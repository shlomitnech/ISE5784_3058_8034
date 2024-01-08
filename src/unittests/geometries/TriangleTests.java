package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

import org.junit.jupiter.api.Test;

import geometries.Polygon;
import geometries.Triangle;
import primitives.Point;
import primitives.Vector;

/**
 * Testing Triangles
 * @author jessi
 * @author shlomit
 *
 */
class TriangleTests {

    /**
     * Test method for {@link geometries.Triangle#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        Point p1 = new Point(0, 0, 1);
        Point p2 = new Point(1, 0, 0);
        Point p3 = new Point(0, 1, 0);
        Triangle tri = new Triangle(p1,p2,p3);
        // generate the test result
        Vector result = tri.getNormal(new Point(1, 1, 1));
        assertEquals(1, result.length(), 0.00000001, "Triangle's normal is not the unit vector");

        // check  that the normal in orthogonal to all the edges
        assertTrue(isZero(tri.getNormal(new Point(0, 0, 1)).dotProduct(new Vector(0, 1, -1))),
                "ERROR: normal is not orthogonal to the first edge");
        assertTrue(isZero(tri.getNormal(new Point(0, 0, 1)).dotProduct(new Vector(1, -1, 0))),
                "ERROR: normal is not orthogonal to the second edge");
        assertTrue(isZero(tri.getNormal(new Point(0, 0, 1)).dotProduct(new Vector(-1, 0, 1))),
                "ERROR:  normal is not orthogonal to the third edge");
    }

    /**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    void testFindIntersections() {
        Point p1 = new Point (1,0,0);
        Point p2 = new Point (4,0,0);
        Point p3 = new Point (3,3,0);

        Triangle tri = new Triangle(p1,p2,p3);
        Point p0 = new Point(2,2,2);
        // ============ Equivalence Partitions Tests ==============
        //Test 01: Intersection point contained on plane is in triangle
        assertEquals(new Point(3,1,0),tri.findIntersections(new Ray(p0,new Vector(1, -1, -2))).get(0),"Intersection of is in the triangle");

        //Test 02: Point contained on plane is outside triangle next to an edge
        assertNull(tri.findIntersections(new Ray(p0,new Vector(-2, -0.5, -2))),"No intersection (outside edge of triangle)");

        //Test 03: Point contained on plane is outside triangle next to a vertex
        assertNull(tri.findIntersections(new Ray(p0,new Vector(-1.5, -2, -2))),"No intersection (outside vertex of triangle");


        // ============ Boundary Value Analysis ==============

        //Test 11: Point on plane is on one of the sides
        assertEquals(new Point(2,1,0),tri.findIntersections(new Ray(p0,new Vector(0, -1, -2))).get(0),"No intersection (on the side of triangle");

        //Test 12: Point on plane is on a vertex
        assertEquals(new Point(3,2,0),tri.findIntersections(new Ray(p0,new Vector(1, 0, -2))).get(0),"No intersection (on the edge of triangle");


        //Test 13: Point on plane is on continuation of side
        assertNull(tri.findIntersections(new Ray(p0,new Vector(3, -2, -2))),"No intersection (on the continuation of side of triangle");


    }
}