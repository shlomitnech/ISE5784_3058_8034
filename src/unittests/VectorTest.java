/**
 *
 */
package unittests;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

import org.junit.jupiter.api.Test;

import primitives.Double3;
import primitives.Vector;

/**
 * @author jessica and Shlomit
 *
 */
class VectorTests {
    Vector v1 = new Vector(1, 2, 3);

    Vector v1Opposite = new Vector(-1, -2, -3);
    Vector v2 = new Vector(-2, -4, -6);
    Vector v3 = new Vector(0, 3, -2);
    Vector v4 = new Vector(1, 2, 2);

    // test zero vector =====================================================
    @Test
    void testZeroVector() {
        // TC01: Tests that zero vector throws exception
        assertThrows(IllegalArgumentException.class, () -> new Vector(0, 0, 0));
    }

    /**
     * Test method for {@link primitives.Vector#add(primitives.Vector)}.
     */
    @Test
    void testAddVector() {
        assertThrows(IllegalArgumentException.class, () -> v1.add(v1Opposite), "ERROR: Vector + -itself does not throw an exception");
//
    }

    @Test
    void testSubtractVector() {
        assertEquals(v1.add(v2), new Vector(-1, -2, -3), "ERROR: Point - Point does not work correctly");
        assertEquals(v1.subtract(v2), new Vector(3, 6, 9), "ERROR: Point - Point does not work correctly");
        assertThrows(IllegalArgumentException.class, () -> v1.subtract(v1), "ERROR: Vector - itself does not throw an exception");

    }

    /**
     * Test method for {@link primitives.Vector#scale(double)}.
     */
    @Test
    void testScale() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Test that the new vector is scaling correctly
        assertEquals(v1.scale(2), new Vector(2, 4, 6), "ERROR: scale() not working");

        // =============== Boundary Values Tests ==================

        // TC01: Test scaling by zero throws exception
        assertThrows(IllegalArgumentException.class, () -> v1.scale(0),
                "ERROR: scale() does not throw exception for scaling by zero");
    }
    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */
    @Test
    void testCrossProduct() {
        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v2);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals(v1.length() * v2.length(), vr.length(), 0.00001, "crossProduct() wrong result length");

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)), "crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v3)), "crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-product of co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v3), "crossProduct() for parallel vectors does not throw an exception");
    }

    /**
     * Test method for {@link primitives.Vector#lengthSquared()}.
     */
    @Test
    void testLengthSquared() {
        assertEquals(v1.lengthSquared(), 14, "ERROR: lengthSquared() is the wrong value");
    }

    /**
     * Test method for {@link primitives.Vector#length()}.
     */
    @Test
    void testLength() {
        assertEquals(new Vector(0, 3, 4).length(), 5, "ERROR: length() is the wrong value");
    }

    /**
     * Test method for {@link primitives.Vector#normalize()}.
     */
    @Test
    void testNormalize() {
        Vector v = new Vector(0, 3, 4);
        Vector n = v.normalize();
        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        assertEquals(1d, n.lengthSquared(), 0.00001, "wrong normalized vector length");
        assertThrows(IllegalArgumentException.class, () -> v.crossProduct(n), //
                "normalized vector is not in the same direction");
        assertEquals(new Vector(0, 0.6, 0.8), n, "wrong normalized vector");

        Vector vec = new Vector(1, 2, 3);
        Vector u = vec.normalize();
        assertEquals(u.length() - 1, 0, "ERROR: the normalized vector is not a unit vector");
        assertTrue(vec.dotProduct(u) > 0, "ERROR: the normalized vector is opposite to the original one");
        //assertTrue(v.crossProduct(u),"ERROR: the normalized vector is not parallel to the original one";)
        assertThrows(IllegalArgumentException.class, () -> vec.crossProduct(u), "ERROR: the normalized vector is not parallel to the original one");
    }

    /**
     * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
     */
    @Test
    void testDotProduct() {
        assertEquals(v1.dotProduct(v3), 0, "ERROR: dotProduct() for orthogonal vectors is not zero");
        assertEquals(v1.dotProduct(v2) + 28, 0, "ERROR: dotProduct() wrong value");
    }
}