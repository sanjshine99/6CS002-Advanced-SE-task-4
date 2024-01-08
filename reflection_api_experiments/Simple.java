package reflection_api_experiments;

//This class is used to demonstrate various aspects of Java reflection. 

public class Simple {

    public float a = 10.0f;
    private float b = 20.0f;

    public Simple() {
    }

    public Simple(float a, float b) {
        this.a = a;
        this.b = b;
    }

    public void squareA() {
        this.a *= this.a;
    }

    private void squareB() {
        this.b *= this.b;
    }

    public float getA() {
        return a;
    }

    private void setA(float a) {
        this.a = a;
    }

    public float getB() {
        return b;
    }

    public void setB(float b) {
        this.b = b;
    }

    public String toString() {
        return String.format("(a:%.2f, b:%.2f)", a, b);
    }
}