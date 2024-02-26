package renderer;
import primitives .*;
import geometries .*;
import scene .*;

public abstract class RayTraceBase {

    protected Scene scene;


    /**
     * consturctor for RayTraceBase
     * @param scene
     */
    public RayTraceBase(Scene scene) {
        this.scene=scene;
    }

    public abstract Color traceRay(Ray ray);
}