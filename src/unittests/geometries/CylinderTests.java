package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import geometries.Cylinder;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Unit test for Cylinder
 */
class CylinderTests {

    /**
     * Test method for {@link geometries.Cylinder#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormal() {
        Cylinder cylinder = new Cylinder(1.0, new Ray(new Point(1, 1, 1), new Vector(0, 1, 0)), 5);

    // ============ Equivalence Partitions Tests ==============
    // EP Test 1 that the normal is the right one
        assertEquals(new Vector(0, 0, 1), cylinder.getNormal(new Point(1, 3, 2)), "Error: getNormal() incorrect result");

    // EP Test 2 that getNormal works for the bottom base
        assertEquals(new Vector(0, -1, 0), cylinder.getNormal(new Point(1, 1, 1.5)), "Error: getNormal() for the bottom base");

    // EP Test 3 that getNormal works for the top base
        assertEquals(new Vector(0, 1, 0), cylinder.getNormal(new Point(1, 6, 1.5)), "Error: getNormal() for the top base");

        // =============== Boundary Values Tests ==================
        Vector b1 = cylinder.getNormal(new Point(1, 1, 1));
        Vector b2 = cylinder.getNormal(new Point(1, 6, 1));

    // BVA Test 1 that getNormal works for the center point of the bottom base
        assertEquals(new Vector(0, -1, 0), b1, "Error: getNormal() center point of the bottom base");

        //BVA Test 2 that getNormal works for the center point of the top base
        assertEquals(new Vector(0, 1, 0), b2, "Error: getNormal() center point of the top base");

    }



}