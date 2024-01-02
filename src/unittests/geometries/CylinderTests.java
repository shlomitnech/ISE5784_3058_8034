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

    @Test
    void testGetNormal() {
        /*
        // Test 1 Normal is on the side
        Point p1 = new Point(2, 0, 0);
        Point p2 = new Point(1, 0, 1);
        Vector v = new Vector(1, 0, 0);
        Ray r = new Ray(p1, v);
        Cylinder c1 = new Cylinder(1.0, r, 1.0);
        Vector correctNorm = new Vector(0, 0, 1);
        assertEquals(correctNorm, c1.getNormal(p2), "Error: Cylinder normal is on the side");

        // Test 2 Normal is on the top base
        //  p1 = new Point(2, 0, 0);
        // Point p3 = new Point(1, 0, 0); // Point on top base
        //v = new Vector(1, 0, 0);
        //r = new Ray(p1, v);
        // Cylinder c2 = new Cylinder(1.0, r, 1.0);
        // correctNorm = new Vector(0, 0, -1); // Correct the comment for the top base
        // assertEquals(correctNorm, c2.getNormal(p3), "Error: Cylinder normal is on the bottom base");

        // Test 3 Normal is on the bottom base
        p1 = new Point(2, 0, 0);
        Point p4 = new Point(1, 0, 1); // Point on the bottom base
        v = new Vector(1, 0, 0);
        r = new Ray(p1, v);
        Cylinder c3 = new Cylinder(1.0, r, 1.0);
        correctNorm = new Vector(0, 0, 1);
        assertEquals(correctNorm, c3.getNormal(p4), "Error: Cylinder normal is on the bottom base");

        // Test 4 Center of Top base
       /* p1 = new Point(2, 0, 0);
        Point p5 = new Point(2, 0, 0); // Center of the top base
        v = new Vector(1, 0, 0);
        r = new Ray(p1, v);
        Cylinder c4 = new Cylinder(1.0, r, 1.0);
        correctNorm = new Vector(0, 0, -1); // Correct the comment for the top base
        assertDoesNotThrow(() -> c4.getNormal(p5), "Error: Cylinder normal at the center of the top base");

        // Test 5 Center of Bottom base
        p1 = new Point(2, 0, 0);
        Point p6 = new Point(2, 0, 1); // Center of the bottom base
        v = new Vector(1, 0, 0);
        r = new Ray(p1, v);
        Cylinder c5 = new Cylinder(1.0, r, 1.0);
        correctNorm = new Vector(0, 0, 1);
        assertEquals(correctNorm, c5.getNormal(p6), "Error: Cylinder normal is on the center of bottom base");
    */
    }
}