package lighting;

import primitives.*;

public class PointLight extends Light implements LightSource{

    private Point position;
    private double kC = 1;
    private double kL = 0;
    private double kQ = 0;

    /**
     *
     * @param intensity
     * @param position
     * @param kC
     * @param kL
     * @param kQ
     */
    public PointLight(Color intensity, Point position, double kC, double kL, double kQ) {
        super(intensity);
        this.position = position;
        this.kC = kC;
        this.kL = kL;
        this.kQ = kQ;
    }

    public PointLight(Color intensity, Point position){
        super(intensity);
        this.position = position;

    }
    /**
     * Set constant attenuation
     * @param d
     * @return
     */
    public PointLight setKc(double d) {
        this.kC = d;
        return this;
    }

    /**
     *set linear attenuation factor
     * @param d
     * @return
     */
    public PointLight setKl(double d) {
        this.kL = d;
        return this;
    }

    /***
     * set quadratic attenuation factor
     * @param d
     * @return
     */
    public PointLight setKq(double d) {
        this.kQ = d;
        return this;
    }

    /**
     * get the intensity of point light
     */
    public Color getIntensity(Point p){
        double dist = position.distance(p);
        return getIntensity().scale(1/(kC+kL*dist+kQ*dist*dist));
    }

    /**
     *
     * @return the direction of light
     */
    public Vector getL(Point p){
        return p.subtract(position).normalize();
    }


    public LightSource setNarrowBeam(int i) {
        return this;
    }
    protected double getReduction(Point p) {
        double dSquare = position.distanceSquared(p);
        return (kC + kL * Math.sqrt(dSquare) + kQ * dSquare);
    }

}
