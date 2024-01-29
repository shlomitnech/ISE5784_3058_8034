package lighting;
import primitives.*;


public abstract class Light {
    protected Color intensity;

    /***
     * Constructor for the light
     * @param c
     */
    protected Light(Color c) {
        intensity = c;
    }

    /***
     *
     * @return intensity
     */
    public Color getIntensity() {
        return intensity;
    }

}
