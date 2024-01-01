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
        // TC01: There is a simple single test here - using 3 points
        Point p1 = new Point(0, 0, 1);
        Point p2 = new Point(1, 0, 0);
        Point p3 = new Point(0, 1, 0);
        Triangle tri = new Triangle(p1,p2,p3);
        // ensure there are no exceptions
        assertDoesNotThrow(() -> tri.getNormal(new Point(1, 1, 1)), "");
        // generate the test result
        Vector result = tri.getNormal(new Point(1, 1, 1));
        // ensure |result| = 1
        assertEquals(1, result.length(), 0.00000001, "Triangle's normal is not a unit vector");
        // ensure the result is orthogonal to all the edges
        //for (int i = 0; i < 3; ++i)
        // assertTrue(isZero(result.dotProduct(pts[i].subtract(pts[i == 0 ? 3 : i - 1]))),
        //          "Triangle's normal is not orthogonal to one of the edges");
    }

}