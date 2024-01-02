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

/***
 * Unit test for Plane
 */
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

    /**
     * Testing the constructor to throw errors if two points are equal or if three points are all on the same line
     */
    @Test
    void testConstructor(){
        //testing when two points are equal
        assertThrows(IllegalArgumentException.class, () -> new Plane(new Point(1,0,0),new Point(1,0,0),new Point(0,0,1)),
                "Error: two points are the same");
        //testing when points are all on the same line
        assertThrows(IllegalArgumentException.class, () -> new Plane(new Point(1,1,1),new Point(2,2,2),new Point(3,3,3)),
                "Error: points are all on the same line");


    }
}