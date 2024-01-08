package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.Sphere;
import primitives.*;

import java.util.List;

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

    /***
     *
     */
    @Test
    public void testFindIntersections() {
        Sphere sphere = new Sphere(new Point(1, 0, 0),1d);
        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(1, 1, 0))),"Ray's line out of sphere");


        // TC02: Ray starts before and crosses the sphere (2 points)
        Point p1 = new Point(0.0651530771650466, 0.355051025721682, 0);
        Point p2 = new Point(1.53484692283495, 0.844948974278318, 0);
        List<Point> result2 = sphere.findIntersections(new Ray(new Point(-1, 0, 0),new Vector(3, 1, 0)));
        assertEquals( 2, result2.size(),"Wrong number of points");
        if (result2.get(0).getX() > result2.get(1).getX())
            result2 = List.of(result2.get(1), result2.get(0));
        assertEquals(List.of(p1, p2), result2, "Wrong number of points");

        // TC03: Ray starts inside the sphere (1 point)
        assertEquals(new Point(2,0,0), sphere.findIntersections(new Ray(new Point(0.5, 0, 0),new Vector(1, 0, 0))).get(0),"Ray starts inside the sphere");
        // TC04: Ray starts after the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray (new Point(3,4,5), new Vector(7,7,7))), "Ray starts after sphere");

        // =============== Boundary Values Tests ==================
        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points
        assertEquals(new Point(1,0.5,0.8660254037844386), sphere.findIntersections(new Ray(new Point(1, 0.5, -0.5), new Vector(0, 0, 1))).get(0), "Ray starts at sphere and goes inside");
        // TC12: Ray starts at sphere and goes outside (0 points)
        //assertNull(sphere.findIntersections(new Ray (new Point(1,0,1), new Vector(-3,0,-3))), "Ray starts at the sphere and goes outside");

        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)

        Point p3 = new Point(0, 0, 0);
        Point p4 = new Point(2,0,0);
        List<Point> result3 = sphere.findIntersections(new Ray(new Point(2.5, 0, 0),new Vector(-1, 0, 0)));
        assertEquals(2, result3.size(),"Wrong number of points");
        if (result3.get(0).getX() > result3.get(1).getX())
            result3 = List.of(result3.get(1), result3.get(0));
        assertEquals(List.of(p3, p4), result3, "Wrong number of points");

        // TC14: Ray starts at sphere and goes inside (1 point)
        assertEquals(new Point(0,0,0),sphere.findIntersections(new Ray(new Point(2, 0, 0),new Vector(-1, 0, 0))).get(0),"Ray starts at spheres and go inside");

        // TC15: Ray starts inside (1 point)
        assertEquals(new Point(0,0,0),sphere.findIntersections(new Ray(new Point(.5, 0, 0),new Vector(-1, 0, 0))).get(0),"Ray starts inside and goes out");

        // TC16: Ray starts at the center (1 point)
        assertEquals(new Point(1.5773502691896257,0.5773502691896258,0.5773502691896258),sphere.findIntersections(new Ray(new Point(1, 0, 0),new Vector(1, 1, 1))).get(0),"Ray starts at center ");

        // TC17: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntersections(new Ray (new Point(0,0,0), new Vector(-1,0,0))), "Ray starts on sphere and goes other direction");
        // TC18: Ray starts after sphere (0 points)
        assertNull(sphere.findIntersections(new Ray (new Point(3,0,0), new Vector(1,0,0))), "Ray starts after sphere and doesn't enter");

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        assertNull(sphere.findIntersections(new Ray (new Point(0,0,2), new Vector(1,0,0))), "Ray starts before the tangent point");
        // TC20: Ray starts at the tangent point
        assertNull(sphere.findIntersections(new Ray (new Point(1,1,0), new Vector(1,0,0))), "Ray starts at the tangent point");

        // TC21: Ray starts after the tangent point
        assertNull(sphere.findIntersections(new Ray (new Point(2,0,2), new Vector(1,0,0))), "Ray starts after the tangent point");

        // **** Group: Special cases
        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        assertNull(sphere.findIntersections(new Ray (new Point(2,0,0), new Vector(0,0,1))), "Ray starts after the tangent point");

    }

}