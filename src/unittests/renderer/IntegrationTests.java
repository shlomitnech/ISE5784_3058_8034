/**
 *
 */
package unittests.renderer;
import renderer.*;
import geometries.*;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import scene.Scene;

/**
 * @author jessica and Shlomit
 * test integration of camera and geometries
 */
class IntegrationTests {
        /** Camera builder for the tests */
    private final Camera.Builder cameraBuilder = Camera.getBuilder()
            .setRayTracer(new SimpleRayTracer(new Scene("Test")))
            .setImageWriter(new ImageWriter("Test", 1, 1))
            .setLocation(Point.ZERO)
            .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0)) //vTo, vUp
            .setVpDistance(1);

    final Point ZERO = new Point(0,0,0);
    /**
     * check intersections between camera and a Geometries
     * (count intersections in the view plane)
     * @param c camera
     * @param g Geometry
     * @param intersections
     * @param assertMessage
     */
    private void checkGeoIntersections(Camera c, Geometry g, int intersections, String assertMessage) throws CloneNotSupportedException {
        int count = 0;
        for (int i = 0; i < 3; i++) { //go through the 3x3 view plane
            for (int j = 0; j < 3; j++) {
                Ray r = c.constructRay(3, 3, j, i);
                if (g.findIntersections(r) != null) {
                    count += g.findIntersections(r).size(); //add the intersection if exists
                }
            }
        }
        assertEquals(intersections, count, assertMessage);
    }

    /**
     * test the camera sphere intersections in the different cases
     * @throws CloneNotSupportedException
     */
    @Test
        //CameraSphereIntersections
    void testCameraSphere() throws CloneNotSupportedException {
        Camera c1 = cameraBuilder.setVpSize(3, 3).setLocation(new Point(0,0,0.5)).build();


        //TC:01 unit sphere in the center of view plane
        Sphere s1 = new Sphere(new Point(0, 0, -3),1d);
        checkGeoIntersections(c1, s1,2, "ERROR Sphere: Expected 2 points");

        //TC:02 Sphere larger than view plane - 18 intersection points
        Sphere s2 = new Sphere(new Point(0, 0, -2.5),2.5d);
        Point p2 = new Point (0,0,0.5);
        checkGeoIntersections(c1, s2,18, "ERROR Sphere: Expected 18 points");

        //TC03: Sphere closer to intersecting view plane - 10 points
        Sphere s3 = new Sphere(new Point(0, 0, -2),2d);
        checkGeoIntersections(c1, s3,10, "ERROR Sphere: Expected 10 points");

        //TC04: Sphere with view plane in middle of it - 9 points
        Sphere s4 = new Sphere(new Point(0, 0, -2),4d);
        Point p4 = new Point (0,0,-2);
        checkGeoIntersections(c1, s4,9, "ERROR Sphere: Expected 10 points");

        //TC05: Sphere with view plane in middle of it - 0 points
        Sphere s5 = new Sphere(new Point(0, 0, 1),0.5d);
        Point p5 = new Point (0,0,-1);
        checkGeoIntersections(c1, s5,0, "ERROR Sphere: Expected 0 points");

    }

    /**
     * test the different cases of camera plane intersections
     * @throws CloneNotSupportedException
     */
    @Test
        //CameraPlaneIntersections

    void testCameraPlane() throws CloneNotSupportedException {
        Camera c1 = cameraBuilder.setVpSize(3, 3).build();

        //TC01: Plane parallel to view plane - 9 points
        Plane p1 = new Plane(new Point(0,0,-3), new Vector(0,0,1));
        checkGeoIntersections(c1, p1, 9, "ERROR Plane: Expected 9 points");

        //TC02: Plane slightly slanted against view plane - 9 points
        Plane p2 = new Plane(new Point(0,0,-3), new Vector(0,1,2));
        checkGeoIntersections(c1, p2, 9, "ERROR Plane: Expected 9 points");

        //TC03: Plane very slanted against view plane - 6 points
        Plane p3 = new Plane(new Point(0,0,-3), new Vector(0,1,1));
        checkGeoIntersections(c1, p3, 6, "ERROR Plane: Expected 6 points");

        //TC04: Plane behind view plane - 0 points
        Plane p4 = new Plane(new Point(1,1,2), new Point(-1,1,2), new Point(0,-10,2));
        checkGeoIntersections(c1, p4, 0, "ERROR Plane: Expected 0 points");

    }

    /**
     * test different cases of camera triangle intersections
     * @throws CloneNotSupportedException
     */
    @Test
        //CameraTriangleIntersections
    void testCameraTriangle() throws CloneNotSupportedException {
        Camera c1 = cameraBuilder.setVpSize(3, 3).build();

        //TC01: small triangle in front of view plane - 1 point
        Triangle t1 = new Triangle(new Point(1,1,-2), new Point(-1,1,-2), new Point(0,-1,-2));
        checkGeoIntersections(c1, t1, 1, "ERROR Triangle TC01: Expected 1 point");

        //TC02: tall triangle - 2 points
        Triangle t2 = new Triangle(new Point(1,1,-2), new Point(-1,1,-2), new Point(0,-20,-2));
        checkGeoIntersections(c1, t2, 2, "ERROR Triangle TC02: Expected 2 points");

        //TC03: triangle behind view plane - 0 points
        Triangle t3 = new Triangle(new Point(1,1,2), new Point(-1,1,2), new Point(0,-20,2));
        checkGeoIntersections(c1, t3, 0, "ERROR Triangle TC03: Expected 0 points");

    }
}