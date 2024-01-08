package reflection_api_experiments;

/******************************************************************************
 * This experiment demonstrates the use of access modifiers in Java. They are
 * Java's way of implementing data hiding and restricting which methods are
 * accessible outside the class.
 * 
 * If you were to remove the comments this code would try to access the private
 * members squareB and b. The compiler will complain about this.
 * 
 ******************************************************************************/

public class Reflection02 {
    public static void main(String[] args) {
        Simple s = new Simple();
        s.squareA();
        // s.squareB(); // Compiler error if uncommented
        float a = s.a;
        // float b = s.b; // Compiler error if uncommented
        System.out.println("s=" + s);
    }
}
