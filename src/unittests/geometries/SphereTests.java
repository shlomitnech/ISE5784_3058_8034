package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.Sphere;
import primitives.Point;
import primitives.Vector;

/**
 * Testing Sphere
 * @author jessica and Shlomit
 *
 */
class SphereTests {

    /**
     * Test method for {@link geometries.Sphere#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormal() {
        Sphere sphere1 = new Sphere(new Point(1, 2, 3), 1);
        // ============ Equivalence Partitions Tests ==============
        Vector vec1 = sphere1.getNormal(new Point(2, 2, 3));
        // TC01: Test that the normal is correct
        assertEquals(new Vector(1, 0, 0), vec1, "getNormal() wrong result");

    }

}