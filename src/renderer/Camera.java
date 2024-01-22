package renderer;
//import geometries.*;
import primitives.*;

import java.util.MissingResourceException;
import java.lang.Cloneable;


public class Camera implements java.lang.Cloneable  {

    Point p0;
    Vector vUp;
    Vector vTo;
    Vector vRight;
    double width;
    double height;
    double distance;

    private ImageWriter imageWriter;
    private RayTraceBase rayTracer; //should this be changed


    public Camera() {
        width = 0;
        height = 0;
        distance = 0;
    }

    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }

    /**
     * @return position of camera
     */
    public Point getP0() {
        return p0;
    }
    /**
     * @return the vTO vector of the camera
     */
    public Vector getVto() {
        return vTo;
    }

    /**
     * @return the vUp of the camera
     */
    public Vector getVup() {
        return vUp;
    }

    /**
     * @return the vRight of the camera
     */
    public Vector getVright() {
        return vRight;
    }


    public Ray constructRay(int nX, int nY, int j, int i) {
        //image center
        Point Pc = this.p0.add(vTo.scale(distance));

        //ratio
        double Ry = height/nY;
        double Rx = width/nX;

        //if xj and yi equal zero we will get in error
        //therefore we want to work around this as it is not incorrect
        double yI = -(i-((double) (nY - 1) /2))*Ry;
        double xJ = (j-((double) (nX - 1) /2))*Rx;

        Point Pij = Pc;
        if (xJ != 0) Pij = Pij.add(vRight.scale(xJ));
        if (yI != 0) Pij = Pij.add(vUp.scale(yI));

        Vector Vij = Pij.subtract(p0);
        Ray ray = new Ray(p0,Vij);

        return ray;

    }
    public static Builder getBuilder(){
        return new Builder();
    }


    /**
     * Nested Builder class - design pattern in Camera
     */
    public static class Builder{
        private final Camera camera;

        /***
         * Constructor for Camera in Builder
         */
        public Builder() {
            this.camera = new Camera();
        }

        /***
         * set the location of camera
         * @param p
         * @return builder
         */
        public Builder setLocation(Point p){
            this.camera.p0 = p;
            return this;
        }

        /***
         * Set Direction of the camera
         * @param vUp
         * @param vTo
         * @return builder
         */
        public Builder setDirection(Vector vUp, Vector vTo){
            if(vUp.dotProduct(vTo) != 0) { //check that VTo and vUp are perpendicular
                throw new IllegalArgumentException("Error: Vto and Vup are not perpedicular");
            }
            this.camera.vTo = vTo.normalize();
            this.camera.vUp = vUp.normalize();
            this.camera.vRight = (vTo.crossProduct(vUp)).normalize();
            return this;
        }

        /***
         * set the View Plane Size
         * @param w
         * @param h
         * @return camera
         */
        public Builder setVpSize(double w, double h)
        {
            this.camera.width= w;
            this.camera.height=h;
            return this;
        }

        /***
         * set the Distance
         * @param d
         * @return camera
         */
        public Builder setVpDistance(double d) {
            this.camera.distance=d;
            return this;
        }

        /**
         *
         * @param test
         * @return
         */
        public Builder setRayTracer(SimpleRayTracer test) {
            this.camera.rayTracer=test;
            return this;
        }

        public Builder setImageWriter(){
            
        }

        /***
         * check for all relevant camera field that have a non-zero value
         * @return
         */
        public Camera build() throws CloneNotSupportedException {
            if(camera.width == 0)
                throw new MissingResourceException("Value is zero","Camera","width");
            if(camera.height == 0)
                throw new MissingResourceException("Value is zero","Camera","height");
            if(camera.distance == 0)
                throw new MissingResourceException("Value is zero","Camera","distance");

            if(camera.width < 0 || camera.height < 0 || camera.distance < 0)
                throw new IllegalArgumentException("Negative number");

            //add the rest of the value checks
            return (Camera) camera.clone();
        }


    }

}