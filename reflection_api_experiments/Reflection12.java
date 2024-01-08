package reflection_api_experiments;

import java.lang.reflect.*;

/******************************************************************************
 * This code finds the return types of all methods in the class and prints them.
 * 
 ******************************************************************************/

public class Reflection12 {
    public static void main(String[] args) {
        Simple s = new Simple();
        Class<?> c = s.getClass();

        Method[] methods = c.getDeclaredMethods();

        for (Method m : methods) {
            System.out.printf("%s %s", m.getReturnType(), m.getName());
            System.out.println();
        }
    }
}
