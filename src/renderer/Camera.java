package renderer;
//import geometries.*;
import lighting.AmbientLight;
import primitives.*;
import primitives.Color;
import primitives.Point;
import static primitives.Util.isZero;


import java.awt.*;
import java.util.MissingResourceException;
import java.lang.Cloneable;

/**
 * class camera
 */
public class Camera implements java.lang.Cloneable  {

    Point p0;
    Vector vUp;
    Vector vTo;
    Vector vRight;
    double width;
    double height;
    double distance;

    private static ImageWriter imageWriter;
    private RayTraceBase rayTracer; //should this be changed

    /**
     * empty constructor for camera
     */
    public Camera() {
        width = 0;
        height = 0;
        distance = 0;
    }

    /**
     * Clone method
     * @return cloned Object
     * @throws CloneNotSupportedException
     */
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

    /**
     * method to construct ray through pixel in a view plane
     * @param nX
     * @param nY
     * @param j
     * @param i
     * @return the constructed ray
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
       //find the center of the view plane
        Point pIJ = p0.add(vTo.scale(distance));

        //Find the offset on the view plane
        //offset is the size of the pixel
        // height/nY = ratio on Y axis
        // width/nX = ratio on X axis
        double offsetY = (-(i-(nY - 1) / 2.0)) * (height / nY);
        double offsetX = (((nX - 1) / 2.0)-j) * (width / nX);

        // Apply the offsets to the view plane to get the final point
        // find distance to move for each pixel
        if (!isZero(offsetX))
            pIJ = pIJ.add(vRight.scale(offsetX));
        if (!isZero(offsetY))
            pIJ = pIJ.add(vUp.scale(offsetY));
        return new Ray(p0, pIJ.subtract(p0));


    }

    public Camera renderImage(){ //changed from return void
        if(p0 == null || vTo == null || vUp == null|| vRight == null || imageWriter == null || rayTracer == null ) {
            throw new IllegalArgumentException("MissingResourcesException");
        }
        for (int i = 0; i < imageWriter.getNy(); i++) {
            for (int j = 0; j < imageWriter.getNx(); j++) {

                Ray r = this.constructRay(imageWriter.getNx(), imageWriter.getNx(), j, i); //construct ray through pixel
                primitives.Color c = this.rayTracer.traceRay(r); //color at point intersected by ray
                this.imageWriter.writePixel(j, i, c); //coloring that pixel
            }
        }
        return this;
    }

    public Camera printGrid(int interval, Color color){ //changed from return void
        if(imageWriter == null) {
            throw new IllegalArgumentException("MissingResourcesException");
        }
        for (int i = 0; i < imageWriter.getNy(); i++) {
            for (int j = 0; j < imageWriter.getNx(); j++) {
                if (((i % interval) == 0) || ((j % interval) == 0)) { //grid lines - need to add if so no grid on shape
                    //need another check here to not overwrite picture
                    imageWriter.writePixel(j, i, color);
                }
            }
        }
        return this;
    }

    public void writeToImage() {
        if(imageWriter == null) {
            throw new IllegalArgumentException("MissingResourcesException");
        }
        imageWriter.writeToImage();
    }

    /**
     * receives the resolution and the pixel number
     *
     */
    private void castRay(int j, int i) {
        rayTracer.traceRay(constructRay(imageWriter.getNx(), imageWriter.getNy(), j, i));
    }

    /**
     * builder method
     * @return a new builder
     */
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
        public Builder setDirection(Vector vTo, Vector vUp){
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
         * set the Ray tracer from simpleRayTracer
         * @param test
         * @return
         */
        public Builder setRayTracer(SimpleRayTracer test) {
            this.camera.rayTracer=test;
            return this;
        }

        /**
         * set image writer
         * @param w
         * @return this (Builder)
         */
        public Builder setImageWriter(ImageWriter w){
            imageWriter = w;
            return this;
        }

        /***
         * check for all relevant camera field that have a non-zero value
         * @return the camera
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