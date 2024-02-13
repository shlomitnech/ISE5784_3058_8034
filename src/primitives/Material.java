package primitives;

/**
 * @author Yahel and Ashi
 *
 */
public class Material {
    /**
     * kD = diffusive attenuation coefficient
     * kS = specular attenuation coefficient
     * kT = transparency coefficient
     * kR = reflection coefficient
     */
    public Double3 Kd = Double3.ZERO;
    public Double3 Ks = Double3.ZERO;
    public Double3 kT = Double3.ZERO;
    public Double3 kR = Double3.ZERO;
    public int nShininess = 0;

    /**
     * Sets diffusive attenuation factor.
     * @param Kd double
     */
    public Material setKd(double Kd) {
        this.Kd = new Double3(Kd);
        return this;
    }
    /**
     * Sets diffusive attenuation factor.
     * @param Kd Double3
     */
    public Material setKd(Double3 Kd) {
        this.Kd = Kd;
        return this;
    }

    /**
     * Sets specular attenuation factor.
     * @param Ks double.
     */
    public Material setKs(double Ks) {
        this.Ks = new Double3(Ks);
        return this;
    }
    /**
     * Sets specular attenuation factor.
     * @param Ks Double3
     */
    public Material setKs(Double3 Ks) {
        this.Ks = Ks;
        return this;
    }

    /**
     * sets transparency coefficient
     * @param kT
     * @return this (builder pattern)
     */
    public Material setkT(double kT) {
        this.kT = new Double3(kT);
        return this;
    }

    /**
     * sets transparency coeff
     * @param kT
     * @return this (builder pattern)
     */
    public Material setkT(Double3 kT) {
        this.kT = kT;
        return this;
    }

    /**
     * sets refelection coeff
     * @param kR
     * @return this (builder pattern)
     */
    public Material setkR(double kR) {
        this.kR = new Double3(kR);
        return this;
    }

    /**
     * sets reflection coeff
     * @param kR
     * @return this (builder pattern)
     */
    public Material setkR(Double3 kR) {
        this.kR = kR;
        return this;
    }
    /**
     * Sets the level of shininess.
     * @param nShininess int
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

}
