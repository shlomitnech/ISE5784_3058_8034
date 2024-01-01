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
        Point p = new Point(1,0,0);
        Sphere sphere = new Sphere(p, 1);
        // ensure there are no exceptions
        assertDoesNotThrow(() -> sphere.getNormal(new Point(0, 0, 1)), "");
        // generate the test result
        Vector result = sphere.getNormal(new Point(0, 0, 1));
        // ensure |result| = 1
        assertEquals(1, result.length(), 0.00000001, "Sphere's normal is not a unit vector");
    }

}