package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import geometries.*;
import primitives.*;

/**
 * Testing Tube
 */
class TubeTests {

    /***
     *
     */
    @Test
    void testGetAxisRay() {
        assertDoesNotThrow(() -> {
            // Test code here
            Point p0 = new Point(0, 0, 0);
            Vector dir = new Vector(1, 0, 0);
            Ray r = new Ray(p0, dir);
            Tube t = new Tube(1.0, r);
            Ray axisRay = t.getAxisRay();
            assertEquals(r, axisRay, "Error: Tube getAxisRay");
        });
    }

    /**
     *
     */
    @Test
    void testGetRadius() {
        assertDoesNotThrow(() -> {
            // Test code here
            Point p0 = new Point(0, 0, 0);
            Vector dir = new Vector(1, 0, 0);
            Ray r = new Ray(p0, dir);
            double radius = 1.0;
            Tube t = new Tube(radius, r);
            assertEquals(radius, t.getRadius(), "Error: Tube getRadius");
        });
    }

    @Test
    void testGetNormal() {
        // Test 1: Point on the body of the tube
        assertDoesNotThrow(() -> {
            Point p0 = new Point(3, 0, 0);
            Point p1 = new Point(1, 1, 0);
            Vector v = new Vector(2, 0, 0);
            Ray r1 = new Ray(p0, v);
            Tube t = new Tube(1.0, r1);
            Vector ans1 = new Vector(0, 1, 0);
            assertEquals(ans1, t.getNormal(p1), "Error: Tube Get Normal");
        });

        // Test 2: Point on the body of the tube with a 90-degree angle to the ray
        assertDoesNotThrow(() -> {
            Point p2 = new Point(2, 0, 0);
            Point p3 = new Point(1, 0, 1);
            Vector v1 = new Vector(1, 0, 0);
            Ray r2 = new Ray(p2, v1);
            Tube t1 = new Tube(1, r2);
            Vector ans2 = new Vector(0, 0, 1);
            assertEquals(ans2, t1.getNormal(p3), "Error: Tube Get Normal");
        });
    }
}