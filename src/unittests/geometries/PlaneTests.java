package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

import org.junit.jupiter.api.Test;

import geometries.Plane;
import primitives.*;

import java.util.List;

/***
 * Unit test for Plane
 */
class PlaneTests {

    /**
     * Test method for {@link geometries.Plane#getNormal()}.
     */
    @Test
    void testGetNormal() {
        Point p1 = new Point(1, 0, 0);
        Point p2 = new Point(0, 1, 0);
        Point p3 = new Point(0, 0, 1);
        //-----------Equivalence partitions-------------
        Plane p = new Plane(p1, p2, p3);
        assertDoesNotThrow(() -> p.getNormal(), "");
        // generate the test result
        Vector result = p.getNormal();
        // ensure |result| = 1
        assertEquals(1, result.length(), 0.00000001, "Plane's normal is not a unit vector");
    }

    /**
     * Testing the constructor to throw errors if two points are equal or if three points are all on the same line
     */
    @Test
    void testConstructor() {
        // testing when two points are equal
        assertThrows(IllegalArgumentException.class, () -> new Plane(new Point(1, 0, 0), new Point(1, 0, 0), new Point(0, 0, 1)),
                "Error: two points are the same");
        // testing when points are all on the same line
        assertThrows(IllegalArgumentException.class, () -> new Plane(new Point(1, 1, 1), new Point(2, 2, 2), new Point(3, 3, 3)),
                "Error: points are all on the same line");
    }

    /**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    void testFindIntersections() {
        Plane plane1 = new Plane(new Point(1, 1, 0), new Vector(1, 1, 1));

        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray intersects the plane
        Ray ray1 = new Ray(new Point(0, 0, 1), new Vector(1, 2, 0));
        Point p1 = new Point(0.33333333333333337, 0.6666666666666667, 1.0);
        assertEquals(List.of(p1), plane1.findIntersections(ray1), "Ray does not intersect the plane");

        // TC02: Ray does not intersect the plane
        Ray ray2 = new Ray(new Point(2, 0, 1), new Vector(0, 0, 3));
        assertNull(plane1.findIntersections(ray2), "Ray's line is out of the sphere");

        // =============== Boundary Values Tests ==================
        // TC11: Ray is parallel to the plane including the plane
        Ray ray3 = new Ray(new Point(1, 0, 0), new Vector(2, 2, 0));
        Point p3 = new Point(1.5, 0.5, 0);
        assertEquals(List.of(p3), plane1.findIntersections(ray3), "Ray does not intersect the plane");

        // TC12: Ray parallel to the plane excluding the plane
        Ray ray4 = new Ray(new Point(1, 0, 4), new Vector(0, 4, 4));
        assertNull(plane1.findIntersections(ray4), "Ray's parallel to the plane excluding the plane ");

        // TC13: orthogonal to the plane before the plane
        Ray ray5 = new Ray(new Point(1, 1, -1), new Vector(1, 1, 0));
        Point p5 = new Point(1.5, 1.5, -1);
        assertEquals(List.of(p5), plane1.findIntersections(ray5), "Ray orthogonal to the plane before the plane");

        // TC14: orthogonal to the plane in the plane
        // ray starts on the plane
        assertEquals(List.of(p5), plane1.findIntersections(ray5), "Ray orthogonal to the plane in the plane");

        // TC15: orthogonal to the plane after the plan
        // ray starts below after the plane and therefore should not intersect
        Ray ray6 = new Ray(new Point(1, 1, 4), new Vector(1, 1, 6));
        assertNull(plane1.findIntersections(ray6), "Ray orthogonal to the plane after the plan");

        // TC16: Ray begins at the plane
        Ray ray8 = new Ray(new Point(0.5, 0.5, 0), new Vector(0.5, 0.5, 0.5));
        Point p8 = new Point(0.8333333333333334, 0.8333333333333334, 0.33333333333333337);
        assertEquals(List.of(p8), plane1.findIntersections(ray8), "Ray start on the plane");

        // TC17: Ray begins at the same point with a reference point in the plane
        Ray ray9 = new Ray(new Point(1, 1, 0), new Vector(6, 6, 1));
        assertThrows(IllegalArgumentException.class, () -> plane1.findIntersections(ray9), "Ray begins at the same point with is a reference point in the plane ");
    }
}
