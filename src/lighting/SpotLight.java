package lighting;

import primitives.*;

public class SpotLight extends PointLight {

    private final Vector direction;

    public SpotLight(Color intensity, Point position, double Kc, double Kl, double Kq, Vector direction) {
        super(intensity, position, Kc, Kl, Kq);
        this.direction = direction.normalize();
    }

    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }


    public Color getIntensity(Point p) {
        return super.getIntensity(p).scale(Math.max(0, direction.dotProduct(getL(p))));

    }


}

