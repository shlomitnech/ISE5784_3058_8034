package geometries;
import primitives.*;

import java.util.List;

/**
 * Interface that all the Geometries use
 * @author Shlomit and Jessica
 */
public abstract class Geometry extends Intersectable {

    /**
     *
     */
    protected Color emission = Color.BLACK;

    private Material material = new Material();

    /**
     * function to get the emission color
     * @return Emission
     */
    public Color getEmission() { return emission; }

    /**
     *
     * @return
     */
    public Material getMaterial() { return material;}

    /**
     * function to set the emission Color
     * @param emission
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
    return this;}

    /**
     *
     * @param material
     */
    public Geometry setMaterial(Material material) { this.material = material;
    return this;}
    /***
     *
     * @param p
     * @return the normal from the point
     */
    public abstract Vector getNormal(Point p);

}