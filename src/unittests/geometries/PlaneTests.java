package unittests.geometries;
/**
 *
 */
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

import org.junit.jupiter.api.Test;

import geometries.Plane;
import primitives.Point;
import primitives.Vector;

class PlaneTests {

    /**
     * Test method for {@link geometries.Plane#getNormal()}.
     */
    @Test
    void testGetNormal() {
        Point p1 = new Point(1,0,0);
        Point p2 = new Point(0,1,0);
        Point p3 = new Point(0,0,1);
        //-----------Equivalence partitions-------------
        Plane p = new Plane(p1,p2,p3);
        assertDoesNotThrow(() -> p.getNormal(), "");
        // generate the test result
        Vector result = p.getNormal();
        // ensure |result| = 1
        assertEquals(1, result.length(), 0.00000001, "Plane's normal is not a unit vector");
    }
}