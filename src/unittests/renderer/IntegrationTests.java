///**
// *
// */
//package unittests.renderer;
//import renderer.*;
//import geometries.*;
//import primitives.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import org.junit.jupiter.api.Test;
//
///**
// * @author jessi
// *
// */
//class IntegrationTests {
//    //CHANGE TO MAKE 1 Check intersections class with GEOMETRIES!!!!!!!!!!!
//    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!?????????????
//    //????????????????????????????????????????????????????????????????????????
//    final Point ZERO = new Point(0,0,0);
//    private void checkSphereIntersections(Camera c, Sphere g, int intersections, String assertMessage) {
//        c.Builder.setVpSize(3,3).setDistance(1);
//        int count = 0;
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                Ray r = c.constructRay(3, 3, j, i);
//                if (g.findIntersections(r) != null) {
//                    count += g.findIntersections(r).size();
//                }
//            }
//        }
//        assertEquals(intersections, count, assertMessage);
//    }
//    private void checkPlaneIntersections(Camera c, Plane g, int intersections, String assertMessage) {
//        c.Builder.setVpSize(3,3).setDistance(1);
//        int count = 0;
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                Ray r = c.constructRay(3, 3, j, i);
//                if (g.findIntersections(r) != null) {
//                    count += g.findIntersections(r).size();
//                }
//            }
//        }
//        assertEquals(intersections, count, assertMessage);
//    }
//    private void checkTriIntersections(Camera c, Triangle g, int intersections, String assertMessage) {
//        c.Builder.setVpSize(3,3).setDistance(1);
//        int count = 0;
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                Ray r = c.constructRay(3, 3, j, i);
//                if (g.findIntersections(r) != null) {
//                    count += g.findIntersections(r).size();
//                }
//            }
//        }
//        assertEquals(intersections, count, assertMessage);
//    }
//    @Test
//        //CameraSphereIntersections
//    void testCameraSphere() {
//        Vector vTo = new Vector(0,0,-1);
//        Vector vUp = new Vector(0,1,0);
//        //TC:01 unit sphere in the center of view plane
//        Sphere s1 = new Sphere(new Point(0, 0, -3),1d);
//        Camera c1 = cameraBuilder.setVpSize(3, 3).build();
//        //Camera c1 = new Camera(ZERO,vTo,vUp).setViewPlaneSize(3, 3).setDistance(1);
//        checkSphereIntersections(c1, s1,2, "ERROR Sphere: Expected 2 points");
//
//        //TC:02 Sphere larger than view plane - 18 intersection points
//        Sphere s2 = new Sphere(new Point(0, 0, -2.5),2.5d);
//        Point p2 = new Point (0,0,0.5);
//        Camera c2 = new Camera(p2,vTo,vUp).setViewPlaneSize(3, 3).setDistance(1);
//        checkSphereIntersections(c2, s2,18, "ERROR Sphere: Expected 18 points");
//
//        //TC03: Sphere closer to intersecting view plane - 10 points
//        Sphere s3 = new Sphere(new Point(0, 0, -2),2d);
//        Camera c3 = new Camera(p2,vTo,vUp).setViewPlaneSize(3, 3).setDistance(1);
//        checkSphereIntersections(c3, s3,10, "ERROR Sphere: Expected 10 points");
//
//        //TC04: Sphere with view plane in middle of it - 9 points
//        Sphere s4 = new Sphere(new Point(0, 0, -2),4d);
//        Point p4 = new Point (0,0,-2);
//        Camera c4 = new Camera(p4,vTo,vUp).setViewPlaneSize(3, 3).setDistance(1);
//        checkSphereIntersections(c4, s4,9, "ERROR Sphere: Expected 10 points");
//
//        //TC05: Sphere with view plane in middle of it - 0 points
//        Sphere s5 = new Sphere(new Point(0, 0, 1),0.5d);
//        Point p5 = new Point (0,0,-1);
//        Camera c5 = new Camera(p5,vTo,vUp).setViewPlaneSize(3, 3).setDistance(1);
//        checkSphereIntersections(c5, s5,0, "ERROR Sphere: Expected 0 points");
//
//    }
//    @Test
//        //CameraPlaneIntersections
//
//    void testCameraPlane() {
//        Camera cam = new Camera(ZERO, new Vector(0,0,-1), new Vector(0,-1,0));
//
//        //TC01: Plane parallel to view plane - 9 points
//        Plane p1 = new Plane(new Point(0,0,-3), new Vector(0,0,1));
//        checkPlaneIntersections(cam, p1, 9, "ERROR Plane: Expected 9 points");
//
//        //TC02: Plane slightly slanted against view plane - 9 points
//        Plane p2 = new Plane(new Point(0,0,-3), new Vector(0,1,2));
//        checkPlaneIntersections(cam, p2, 9, "ERROR Plane: Expected 9 points");
//
//        //TC03: Plane very slanted against view plane - 6 points
//        Plane p3 = new Plane(new Point(0,0,-3), new Vector(0,1,1));
//        checkPlaneIntersections(cam, p3, 6, "ERROR Plane: Expected 6 points");
//
//        //TC04: Plane behind view plane - 0 points
//        Plane p4 = new Plane(new Point(1,1,2), new Point(-1,1,2), new Point(0,-10,2));
//        checkPlaneIntersections(cam, p4, 0, "ERROR Plane: Expected 0 points");
//
//    }
//
//    @Test
//        //CameraTriangleIntersections
//    void testCameraTriangle() {
//        Camera c = new Camera(ZERO, new Vector(0,0,-1), new Vector(0,-1,0));
//
//        //TC01: small triangle in front of view plane - 1 point
//        Triangle t1 = new Triangle(new Point(1,1,-2), new Point(-1,1,-2), new Point(0,-1,-2));
//        checkTriIntersections(c, t1, 1, "ERROR Triangle TC01: Expected 1 point");
//
//        //TC02: tall triangle - 2 points
//        Triangle t2 = new Triangle(new Point(1,1,-2), new Point(-1,1,-2), new Point(0,-20,-2));
//        checkTriIntersections(c, t2, 2, "ERROR Triangle TC02: Expected 2 points");
//
//        //TC03: triangle behind view plane - 0 points
//        Triangle t3 = new Triangle(new Point(1,1,2), new Point(-1,1,2), new Point(0,-20,2));
//        checkTriIntersections(c, t3, 0, "ERROR Triangle TC03: Expected 0 points");
//
//    }
//}