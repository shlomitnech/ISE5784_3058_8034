package primitives;

public class Vector extends Point{

    /***
     *
     * @param d3
     */
    public Vector(Double3 d3){
        super(d3);
        if(d3.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("Vector is null");
        }
    }

    /***
     *
     * @param normalizedD1
     * @param normalizedD2
     * @param normalizedD3
     */
    public Vector(double normalizedD1, double normalizedD2, double normalizedD3) {
        super(normalizedD1, normalizedD2, normalizedD3);
        // TODO Auto-generated constructor stub
        if(this.xyz.equals(Double3.ZERO)) {
            //throw illegal argument exception
            throw new IllegalArgumentException("Vector is null");
        }
    }


    @Override
    public boolean equals(Object obj) {
        if (this==obj) return true;
        return (obj instanceof Vector other) && this.xyz.equals(other.xyz);
       /* if (obj == null) return false;
        if (!(obj instanceof Point)) //if the object is not a point
            return false;
        Point other = (Point)obj;
        return super.equals(other);*/
    }

        @Override
    public String toString() {return "->" + super.toString();
    }

    /**
     * Add two vectors
     * @param v - Vector
     * @return
     */
    public Vector add(Vector v) {
        return new Vector(v.xyz.d1 + xyz.d1, v.xyz.d2 + xyz.d2, v.xyz.d3 + xyz.d3);
    }

    /**
     * Multiplies a vector by a number
     * @param d
     * @return multiplied vector
     */
    public Vector scale(double d) {
        return new Vector(d*xyz.d1,d*xyz.d2,d*xyz.d3);
    }

    /**
     *
     * @param v
     * @return cross product of two vectors
     */
    public Vector crossProduct(Vector v) {
        return new Vector(v.xyz.d2 * xyz.d3 - v.xyz.d3 * xyz.d2, -(v.xyz.d1 * xyz.d3 - v.xyz.d3 * xyz.d1), v.xyz.d1 * xyz.d2 - v.xyz.d2 * xyz.d1);
    }

    /***
     *
     * @return length squared
     */
    public double lengthSquared() {
        return this.dotProduct(this);
    }

    /**
     *length - calls lengthsquared
     * @return the length
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /***
     *
     * @return the vector normalized
     */
    public Vector normalize() {
        return new Vector(xyz.d1 / length(), xyz.d2 / length(), xyz.d3 / length());
    }

    /***
     *
     * @param v
     * @return the dot product
     */
    public double dotProduct(Vector v) {
        return (v.xyz.d1* xyz.d1 + v.xyz.d2* xyz.d2 + v.xyz.d3*xyz.d3);
    }

}