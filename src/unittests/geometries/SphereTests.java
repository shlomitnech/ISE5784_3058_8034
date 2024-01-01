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
        Sphere s1 = new Sphere(new Point(1, 2, 3), 1);
        // ============ Equivalence Partitions Tests ==============
        Vector e1 = s1.getNormal(new Point(3, 3, 3));
        // TC01: Test that the normal is correct
        assertEquals(new Vector(2, 1, 0), e1, "getNormal() wrong result");

    }

}