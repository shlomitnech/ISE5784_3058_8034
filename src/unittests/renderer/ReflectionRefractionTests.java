/**
 *
 */
package unittests.renderer;

import static java.awt.Color.*;

import lighting.DirectionalLight;
import lighting.PointLight;
import org.junit.jupiter.api.Test;

import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.SpotLight;
import primitives.*;
import renderer.*;
import scene.Scene;

/** Tests for reflection and transparency functionality, test for partial
 * shadows
 * (with transparency)
 * @author dzilb */
public class ReflectionRefractionTests {
    /** Scene for the tests */
    private final Scene          scene         = new Scene("Test scene");
    /** Camera builder for the tests with triangles */
    private final Camera.Builder cameraBuilder = Camera.getBuilder()
            .setDirection(new Vector(0,0,-1), new Vector(0, 1, 0))
            .setRayTracer(new SimpleRayTracer(scene));

    /** Produce a picture of a sphere lighted by a spot light */
    @Test
    public void twoSpheres() throws CloneNotSupportedException {
        scene.geometries.add(
                new Sphere(new Point(0, 0, -50), 50d).setEmission(new Color(BLUE))
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setkT(0.3)),
                new Sphere(new Point(0, 0, -50), 25d).setEmission(new Color(RED))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)));
        scene.lights.add(
                new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2))
                        .setKl(0.0004).setKq(0.0000006));

        cameraBuilder.setLocation(new Point(0, 0, 1000)).setVpDistance(1000)
                .setVpSize(150, 150)
                .setImageWriter(new ImageWriter("refractionTwoSpheres", 500, 500))
                .build();
        cameraBuilder.build().renderImage();
        cameraBuilder.build().writeToImage();
    }

    /** Produce a picture of a sphere lighted by a spot light */
    @Test
    public void twoSpheresOnMirrors() throws CloneNotSupportedException {
        scene.geometries.add(
                new Sphere(new Point(-950, -900, -1000), 400d).setEmission(new Color(0, 50, 100))
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)
                                .setkT(new Double3(0.5, 0, 0))),
                new Sphere(new Point(-950, -900, -1000), 200d).setEmission(new Color(100, 50, 20))
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),
                new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                        new Point(670, 670, 3000))
                        .setEmission(new Color(20, 20, 20))
                        .setMaterial(new Material().setkR(1)),
                new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                        new Point(-1500, -1500, -2000))
                        .setEmission(new Color(20, 20, 20))
                        .setMaterial(new Material().setkR((new Double3(0.5, 0, 0.4)))));
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));
        scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(-750, -750, -150), new Vector(-1, -1, -4))
                .setKl(0.00001).setKq(0.000005));

        cameraBuilder.setLocation(new Point(0, 0, 10000)).setVpDistance(10000)
                .setVpSize(2500, 2500)
                .setImageWriter(new ImageWriter("reflectionTwoSpheresMirrored", 500, 500))
                .build();
        cameraBuilder.build().renderImage();
        cameraBuilder.build().writeToImage();
    }

    /** Produce a picture of a two triangles lighted by a spot light with a
     * partially
     * transparent Sphere producing partial shadow */
    @Test
    public void trianglesTransparentSphere() throws CloneNotSupportedException {
        scene.geometries.add(
                new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135),
                        new Point(75, 75, -150))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),
                new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),
                new Sphere(new Point(60, 50, -50), 30d).setEmission(new Color(BLUE))
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setkT(0.6)));
        scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));
        scene.lights.add(
                new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1))
                        .setKl(4E-5).setKq(2E-7));

        cameraBuilder.setLocation(new Point(0, 0, 1000)).setVpDistance(1000)
                .setVpSize(200, 200)
                .setImageWriter(new ImageWriter("refractionShadow", 600, 600))
                .build();
        cameraBuilder.build().renderImage();
        cameraBuilder.build().writeToImage();
    }

    @Test
    public void test1() throws CloneNotSupportedException {
        scene.geometries.add(
                //top sphere
                new Sphere(new Point(0, 0, -10), 15d).setEmission(new Color(102,97,255))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
                //middle sphere
                new Sphere(new Point(0, -20, -20), 20d).setEmission(new Color(78,73,222))
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setkT(0.3)),
                //bottom sphere
                new Sphere(new Point(0, -50, -30), 30d).setEmission(new Color(60,57,178))
                .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setkT(0.3)),
                //left eye
                new Sphere(new Point(-8, 5, 5), 2d).setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setkT(0.3)),
                //right eye
                new Sphere(new Point(5, 5,5), 2d).setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setkT(0.3)),
                //sun
                new Sphere(new Point(-250, 250, -3000), 100d).setEmission(new Color(YELLOW))
                .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setkT(1)));
        scene.geometries.add(
                //top nose
                new Triangle(new Point(-5, 0, 5), new Point(1, 0,5), new Point(-4, -5, 30))
                        .setMaterial(new Material().setKd(0.6).setKs(0.6).setShininess(60)).setEmission(new Color(RED)),
                //bottom nose
                new Triangle(new Point(-5, -2, 5), new Point(1, -2,5), new Point(-4, -5, 30))
                        .setMaterial(new Material().setKd(0.6).setKs(0.6).setShininess(60)).setEmission(new Color(RED)),
                //right side nose
                new Triangle(new Point(1, 0,5), new Point(1, -2,5), new Point(-4, -5, 30))
                        .setMaterial(new Material().setKd(0.6).setKs(0.6).setShininess(60)).setEmission(new Color(RED)),
                //left side nose
                new Triangle(new Point(-5, 0, 5), new Point(-5,-2,5), new Point(-4, -5, 30))
                        .setMaterial(new Material().setKd(0.6).setKs(0.6).setShininess(60)).setEmission(new Color(RED)),
                //left arm
                new Triangle(new Point(-20,-18,-20), new Point(-20,-22,-20), new Point(-50,-15, 30))
                        .setMaterial(new Material().setKd(0.6).setKs(0.6).setShininess(60)).setEmission(new Color(64, 52, 34)),
                //right arm
                new Triangle(new Point(20,-18,-20), new Point(20,-22,-20), new Point(50,-15, 30))
                        .setMaterial(new Material().setKd(0.6).setKs(0.6).setShininess(60)).setEmission(new Color(64, 52, 34)),
                //mirror
                new Triangle(new Point(-75,25,500), new Point(-20,30,-200), new Point(-50,-100, 100))
                        .setEmission(new Color(20, 20, 20))
                        .setMaterial(new Material().setkR(.8)),
                //snow
                new Triangle(new Point(-80, -220, -115), new Point(-120, 0, -140), new Point(200, 10, -150))
                        .setMaterial(new Material().setKd(0.6).setKs(0.6).setShininess(60)).setEmission(new Color(240,240,240)));
       // scene.lights.add(
        //         new DirectionalLight(new Color(YELLOW), new Vector(2,-6,2)));
        scene.lights.add(
                new PointLight(new Color(230, 145, 60), new Point(100, 100, 2000)));
        scene.lights.add(
                new SpotLight(new Color(700, 400, 400), new Point(-100, -100, 2000), new Vector(3, 1, -4))
                        .setKl(0.0004).setKq(0.0000006));
        //light for the sun
        scene.lights.add(
                new PointLight(new Color(YELLOW), new Point(-250, 250, -3000)));

        scene.setBackground(new Color(128, 255 , 243)); //sky blue
        cameraBuilder.setLocation(new Point(0, 0, 1000)).setVpDistance(1000)
                .setVpSize(150, 150)
                .setImageWriter(new ImageWriter("snowMan", 500, 500))
                .build();
        cameraBuilder.build().renderImage();
        cameraBuilder.build().writeToImage();
    }
}
