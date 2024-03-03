package renderer;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;
import static primitives.Util.isZero;


/**
 * @author Shlomit and Jessica
 * 
 *
 */
public class Pixel extends Camera {


    private static final int GRIDSIZE = 3;

    public Pixel(Ray refractedRay) {
        super();
        this.p0 = refractedRay.getHead();
    }

    //same method from Camera class
    public Ray constructRay(int nX, int nY, int j, int i) {
        //find the center of the view plane
        Point pIJ = p0.add(vTo.scale(distance));

        //Find the offset on the view plane
        //offset is the size of the pixel
        // height/nY = ratio on Y axis
        // width/nX = ratio on X axis
        double offsetY = (-(i - (nY - 1) / 2.0)) * (height / nY);
        double offsetX = (((nX - 1) / 2.0) - j) * (width / nX);

        // Apply the offsets to the view plane to get the final point
        // find distance to move for each pixel
        if (!isZero(offsetX))
            pIJ = pIJ.add(vRight.scale(offsetX));
        if (!isZero(offsetY))
            pIJ = pIJ.add(vUp.scale(offsetY));
        return new Ray(p0, pIJ.subtract(p0));

    }

    /**
     * alternative to constructRay; instead of shooting just one beam,
     * we are shooting a grid of beams of dimension DENSITY X DENSITY
     *
     * @return the list of rays
     */
    public List<Ray> constructRayBeamGrid() {
        List<Ray> rays = new LinkedList<>();
        for (int i = 0; i < GRIDSIZE; ++i)
            for (int j = 0; j < GRIDSIZE; j++)
                rays.add(constructRay(GRIDSIZE, GRIDSIZE, j, i));
        return rays;
    }
}
