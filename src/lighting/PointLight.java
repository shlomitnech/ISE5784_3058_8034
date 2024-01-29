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
    public PointLight setkC(double d) {
        this.kC = d;
        return this;
    }

    /**
     *set linear attenuation factor
     * @param d
     * @return
     */
    public PointLight setkL(double d) {
        this.kL = d;
        return this;
    }

    /***
     * set quadratic attenuation factor
     * @param d
     * @return
     */
    public PointLight setkQ(double d) {
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


}
