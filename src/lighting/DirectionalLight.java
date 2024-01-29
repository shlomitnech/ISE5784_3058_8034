package lighting;
import primitives.*;

public class DirectionalLight extends Light implements LightSource {
    Vector direction;
    /***
     * constructor for directional light
     * @param color of the light
     * @param dir = direction of the light
     */
    public DirectionalLight(Color color, Vector dir) {
        super(color);
        this.direction = dir;
    }
    /**
     *
     * @return the direction vector
     */
    public Vector getL(Point p){
        return direction;
    }

    /**
     * @return intensity/color of the light
     */
    public Color getIntensity(Point p) {
        return getIntensity();
    }



}
