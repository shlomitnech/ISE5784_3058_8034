/**
 *
 */
package unittests;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

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
        //fail("Not yet implemented");
    }

    /**
     * Test method for {@link primitives.Point distance()}.
     */
    @Test
    void testDistance() {
        //fail("Not yet implemented");
    }

}