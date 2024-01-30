package lighting;
import primitives.*;

public class DirectionalLight extends Light implements LightSource {
    private final Vector direction;
    /***
     * constructor for directional light
     * @param color intensity of light
     * @param dir = direction of the light
     */
    public DirectionalLight(Color color, Vector dir) {
        super(color);
        this.direction = dir.normalize();
    }
    /**
     * @return the direction vector
     */
    @Override
    public Vector getL(Point p){
        return direction.normalize();
    }
    /**
     * @return intensity/color of the light
     */
    @Override
    public Color getIntensity(Point p) {
        return getIntensity();
    }

}
