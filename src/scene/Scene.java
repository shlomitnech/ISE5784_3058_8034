package scene;

import lighting.*;
import primitives.*;
import geometries.*;
import primitives.Color;


import java.awt.*;

/**
 * Scene class
 * Plain Data Structure - access to all its attributes are public
 */
public class Scene {

    public final String name;
    public Color background = Color.BLACK;
    public AmbientLight ambientLight = AmbientLight.NONE;
    public Geometries geometries = new Geometries();

    /***
     * Constructor for Scene's name
     * @param s
     */
    public Scene(String s) {
        this.name = s;
    }
    /***
     * Sets the background color
     * @param c
     * @return
     */
    public Scene setBackground( Color c) {
        this.background = c;
        return this;
    }

    /**
     * sets the Ambient light to the color and scale given
     * @param color
     * @param ka
     * @return
     */
    public Scene setAmbientLight(Color color, double ka) {
        this.ambientLight = new AmbientLight(color, ka);
        return this;
    }

    public Scene setAmbientLight(AmbientLight amb) {
        this.ambientLight = amb;
        return this;
    }
    /**
     * adds a geometry to the list
     *
     * @param geometries the Geometry you want to add to the scene
     * @return the scene
     */
    public Scene setGeometries(Geometries... geometries) {
        for (Intersectable g : geometries) {
            this.geometries.add(g);
        }
        return this;
    }

}