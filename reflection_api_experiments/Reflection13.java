package reflection_api_experiments;

import java.lang.reflect.*;

/******************************************************************************
 * This code prints all method names as well as parameter names and types. Note
 * that the original identifier names are lost.
 * 
 ******************************************************************************/

public class Reflection13 {
    public static void main(String[] args) {
        Simple s = new Simple();
        Class<?> c = s.getClass();

        Method[] methods = c.getDeclaredMethods();

        for (Method m : methods) {
            System.out.printf("%s %s(", m.getReturnType(), m.getName());
            int count = 0;
            for (Parameter p : m.getParameters()) {
                if (count != 0) {
                    System.out.print(", ");
                }
                System.out.printf("%s %s", p.getType(), p.getName());
                count++;
            }
            System.out.println(")");
        }
    }
}
