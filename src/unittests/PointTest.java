/**
 *
 */
package unittests;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

import org.junit.jupiter.api.Test;

import primitives.Point;
import primitives.Vector;


/*
  Point p1 = new Point(1, 2, 3);
      if (!(p1.add(new Vector(-1, -2, -3)).equals(new Point(0, 0, 0))))
         out.println("ERROR: Point + Vector does not work correctly");
      if (!new Vector(1, 1, 1).equals(new Point(2, 3, 4).subtract(p1)))
         out.println("ERROR: Point - Point does not work correctly");

      out.println("If there were no any other outputs - all tests succeeded!");
 * */
class PointTests {

    /**
     * Test method for {@link primitives.Point#add(primitives.Vector)}.
     */
    @Test
    void testAdd() {
        Point p1 = new Point(1, 2, 3);
        assertEquals(p1.add(new Vector(-1, -2, -3)),new Point(0, 0, 0),"ERROR: Point + Vector does not work correctly");
    }

    /**
     * Test method for {@link primitives.Point#subtract(primitives.Point)}.
     */
    @Test
    void testSubtract() {
        Point p1 = new Point(1, 2, 3);
        assertEquals(new Vector(1, 1, 1),new Point(2, 3, 4).subtract(p1),"ERROR: Point - Point does not work correctly");
    }

    /**
     * Test method for {@link primitives.Point distanceSquared()}.
     */
    @Test
    void testDistanceSquared() {
        Point  p1 = new Point(1, 2, 3);
        Point  p2 = new Point(2, 4, 6);
        Point  p3 = new Point(2, 4, 5);
        //check if the distance squared from a point to itself is 0
        assertEquals(p1.distanceSquared(p1), 0.0, "ERROR: point squared distance to itself is not zero");
        //check distance squared between points is correct
        assertEquals(p1.distanceSquared(p3) - 9, 0.0, "ERROR: squared distance between points is wrong");
        assertEquals(p3.distanceSquared(p1) - 9, 0.0, "ERROR: squared distance between points is wrong");
    }

    /**
     * Test method for {@link primitives.Point distance()}.
     */
    @Test
    void testDistance() {
        Point  p1 = new Point(1, 2, 3);
        Point  p2 = new Point(2, 4, 6);
        Point  p3 = new Point(2, 4, 5);
        //check if the distance from a point to itself is 0
        assertEquals(p1.distance(p1), 0.0, "ERROR: point distance to itself is not zero");
        //check distance between points
        assertEquals(p1.distance(p3) - 3, 0.0, "ERROR: distance between points to itself is wrong");
        assertEquals(p3.distance(p1) - 3, 0.0, "ERROR: distance between points to itself is wrong");
    }

}