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

}