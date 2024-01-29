package primitives;

/**
 * @author Yahel and Ashi
 *
 */
public class Material {
    public Double3 Kd = Double3.ZERO, Ks = Double3.ZERO;
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
     * Sets the level of shininess.
     * @param nShininess int
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

}
