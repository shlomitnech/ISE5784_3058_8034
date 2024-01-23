package unittests;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import primitives.*;
import java.util.List;

class RayTests {
    /*
    *  Two equivalent partitions – positive and negative distance
    *
▪ One boundary – zero distance
    * */
    @Test
    void testGetPoint() {
        Ray testRay = new Ray(new Point(1,1,0), new Vector(1,2,3));
        //EP 01: positive distance
        assertEquals(new Point(1.5345224838248488,2.0690449676496976,1.6035674514745464), testRay.getPoint(2), "Error with positive distance");
        //EP 02: negative distance
        assertEquals(new Point(0.4654775161751512,-0.06904496764969759,-1.6035674514745464), testRay.getPoint(-2), "Error with negative distance");
        //Boundary case: 0 distance
        //assertNull(testRay.getPoint(0), "Error with positive distance");

    }

    /**
     * Tester to make sure that the function findClosestPoint in fact returns the closest point
      */
    @Test
    void testFindClosestPoint(){
        Ray ray = new Ray(new Point(1,1,1), new Vector(1,2,3));
        Point p1 = new Point(1,2,3);
        Point p2 = new Point(1,3,5);
        Point p3 = new Point(1,4,7);
        //EP the closest point to the ray's head is found somewhere in the middle of the list
        List<Point> points = List.of(p2, p1, p3);
        assertEquals(p1, ray.findClosestPoint(points), "Error: Wrong point returned");

        //BVA Test 1: an empty list return null
        points = List.of();
        assertNull(ray.findClosestPoint(points), "Error: list is empty should return null");

        //BVA Test 2: a list where the closest point is the first point on the list
        points = List.of(p1,p2,p3);
        assertEquals(p1, ray.findClosestPoint(points), "Error: Wrong point returned");

        //BVA Test 3: a list where the closest point is the last point on the list
        points = List.of(p3,p2,p1);
        assertEquals(p1, ray.findClosestPoint(points), "Error: Wrong point returned");
    }

}

