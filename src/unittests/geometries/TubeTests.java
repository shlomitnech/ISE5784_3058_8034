package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import geometries.*;
import primitives.*;

/**
 * Testing Tube
 */
class TubeTests {


    @Test
    void testGetNormal() {
        Tube t1 = new Tube(1.0, new Ray(new Point(1, 1, 1), new Vector(0, 1, 0)));

        // ============ Equivalence Partitions Tests ==============
        Vector e1 = t1.getNormal(new Point(1, 3, 2));
        // Test 1 : Test that the normal is correct
        assertEquals(new Vector(0, 0, 1), e1, "ERROR: getNormal() incorrect result");

        // =============== Boundary Values Tests ==================
        Vector b1 = t1.getNormal(new Point(1, 1, 2));

        // Test 2: Test that getNormal works for normal that is perpendicular to the axis ray
        assertEquals(new Vector(0, 0, 1), b1,
                "ERROR: getNormal() with norm that is perpendicular to the axis ray");
    }

}
