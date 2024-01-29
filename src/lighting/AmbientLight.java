package lighting;

import primitives.*;

public class AmbientLight extends Light{
    public static final AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);


    /**
     * Constructor for Ambient light
     * @param iA
     * @param kA of type double
     */
    public AmbientLight(Color iA, double kA) {
        super(iA.scale(kA));
    }
    /***
     * Constructs ambient light - Multiples color and scale to get intensity
     * @param iA
     * @param kA of type Double3
     */
    public AmbientLight(Color iA, Double3 kA) {

        super(iA.scale(kA));
    }
    /***
     *
     * @return intensity
     */
}