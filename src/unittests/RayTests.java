package unittests;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import primitives.*;

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

}

