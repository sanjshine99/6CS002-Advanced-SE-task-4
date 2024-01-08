package reflection_api_experiments;

import java.lang.reflect.Method;

/******************************************************************************
 * Not only can reflection be used to access data, but it also exposes methods.
 * This experiment demonstrates that the names and parameter types for methods
 * can be found. This version does not return a reference for the private method
 * setA, but if the documentation for the Class class is explored a way to
 * easily get access to private methods can easily be found.
 * 
 ******************************************************************************/

public class Reflection09 {
    public static void main(String[] args) throws Exception {
        Simple s = new Simple();
        Method[] methods = s.getClass().getMethods();
        System.out.printf("There are %d methods\n", methods.length);

        for (Method m : methods) {
            System.out.printf("method name=%s type=%s parameters = ", m.getName(),
                    m.getReturnType());
            Class<?>[] types = m.getParameterTypes();
            for (Class<?> c : types) {
                System.out.print(c.getName() + " ");
            }
            System.out.println();
        }
    }
}
