package lighting;

import primitives.*;

public class AmbientLight {
    public static final AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);
    private final Color intensity;

    Double3 Ka;

    /**
     * default constructor that sets the intensity to black
     */
    public AmbientLight(){
        this.intensity = Color.BLACK;
    }

    public AmbientLight(Color iA, double kA) {
        this.intensity =  iA.scale(kA);
    }
    /***
     * Constructs ambient light - Multiples color and scale to get intensity
     * @param iA
     * @param kA
     */
    public AmbientLight(Color iA, Double3 kA) {
        this.intensity =  iA.scale(kA);
    }
    /***
     *
     * @return intensity
     */
    public Color getIntensity() {
        return intensity;
    }
}