package renderer;
//import geometries.*;
import lighting.AmbientLight;
import primitives.*;
import primitives.Color;
import primitives.Point;
import static primitives.Util.isZero;


import java.awt.*;
import java.util.LinkedList;
import java.util.List;
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

    /**
     * casts a ray through every pixel of image writer and colors that pixel
     */
    public void renderImage(int GRIDSIZE){ //changed from return void
        if(p0 == null || vTo == null || vUp == null|| vRight == null || imageWriter == null || rayTracer == null ) {
            throw new IllegalArgumentException("MissingResourcesException");
        }
        int nY = imageWriter.getNy();
        int nX = imageWriter.getNx();

        for (int i = 0; i < nY; ++i)
            for (int j = 0; j < nX; j++)
                imageWriter.writePixel(j, i, castRays(j, i, GRIDSIZE)); //check if intersection of geometries at each pixel
    }

    /**
     * prints the grid over the image at the interval of pixels
     * @param interval = space between pixels
     * @param color = color of grid
     */
    public void printGrid(int interval, Color color){ //changed from return void
        if(imageWriter == null) {
            throw new IllegalArgumentException("MissingResourcesException");
        }
        //vertical lines
        for (int i = 0; i < imageWriter.getNy(); i += interval)
            for (int j = 0; j < imageWriter.getNx(); j += 1) //go through all the pixels in that line
                imageWriter.writePixel(i, j, color);
        //horizontal lines
        for (int i = 0; i < imageWriter.getNy(); i += 1)
            for (int j = 0; j < imageWriter.getNx(); j += interval)
                imageWriter.writePixel(i, j, color);

    }

    /**
     * delegates to imagewriter to write the pixels to the final image
     */
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
    private Color castRay(int j, int i) {
        return rayTracer.traceRay(constructRay(imageWriter.getNx(), imageWriter.getNy(), j, i));

    }

    private Color castRays(int j, int i, int GRIDSIZE) {
        List<Ray> rays = constructRayBeamGrid(imageWriter.getNx(), imageWriter.getNy(), j, i, GRIDSIZE); // Construct multiple rays through each pixel
        Color totalColor = Color.BLACK; // Initialize total color to accumulate colors from multiple rays
        for (Ray ray : rays) {
            totalColor = totalColor.add(rayTracer.traceRay(ray)); // Trace each ray and accumulate colors
        }
        // Return the average color obtained from tracing multiple rays
        return totalColor.reduce((int) rays.size()); //this was double before
    }
    public List<Ray> constructRayBeamGrid(int nX, int nY, int j, int i, int GRIDSIZE) {
        List<Ray> rays = new LinkedList<>();
        double pixelWidth = width / nX; // Width of each pixel
        double pixelHeight = height / nY; // Height of each pixel

        // Calculate the starting point of the pixel
        double startX = -width / 2 + pixelWidth * j;
        double startY = height / 2 - pixelHeight * i;

        // Calculate the step size for each sub-pixel
        double stepX = pixelWidth / GRIDSIZE;
        double stepY = pixelHeight / GRIDSIZE;

        // Iterate over the sub-pixels in the current pixel
        for (int row = 0; row < GRIDSIZE; row++) {
            for (int col = 0; col < GRIDSIZE; col++) {
                // Calculate the coordinates of the sub-pixel
                double x = startX + stepX * col + stepX / 2;
                double y = startY - stepY * row - stepY / 2; // Negative because Y-axis is inverted in image space

                // Construct the ray passing through the sub-pixel
                Point pIJ = p0.add(vTo.scale(distance)); // Center of the view plane
                pIJ = pIJ.add(vRight.scale(-x)); // Apply offset along the X-axis (inverted direction)
                pIJ = pIJ.add(vUp.scale(y)); // Apply offset along the Y-axis
                rays.add(new Ray(p0, pIJ.subtract(p0))); // Construct and add the ray
            }
        }
        return rays;
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