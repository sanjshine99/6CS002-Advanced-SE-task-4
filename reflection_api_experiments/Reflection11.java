package reflection_api_experiments;

import java.lang.reflect.*;

/******************************************************************************
 * This code:
 * 1) Constructs an instance of the AnnotatedSimple class.
 * 2) Prints the class using the implicit toString method.
 * 3) Prints the class name.
 * 4) Prints the names of all methods declared in the class.
 * 
 ******************************************************************************/

public class Reflection11 {
    public static void main(String[] args) {
        Simple s = new Simple();
        Class<?> c = s.getClass();

        System.out.println("class = " + c);
        System.out.println("class name = " + c.getName());

        Method[] methods = c.getDeclaredMethods();

        for (Method m : methods) {
            System.out.printf("%s", m.getName());
            System.out.println();
        }
    }
}
