package lighting;

import primitives.*;

public class AmbientLight {
    public static final AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);
    private final Color intensity; //intensity

    /**
     * default constructor that sets the intensity to black
     */
    public AmbientLight(){
        this.intensity = Color.BLACK;
    }

    /**
     * Constructor for Ambient light
     * @param iA
     * @param kA of type double
     */
    public AmbientLight(Color iA, double kA) {
        this.intensity =  iA.scale(kA);
    }
    /***
     * Constructs ambient light - Multiples color and scale to get intensity
     * @param iA
     * @param kA of type Double3
     */
    public AmbientLight(Color iA, Double3 kA) {
        this.intensity =  iA.scale(kA);
    }
    /***
     *
     * @return intensity
     */
    public Color getIntensity() { return intensity; }
}