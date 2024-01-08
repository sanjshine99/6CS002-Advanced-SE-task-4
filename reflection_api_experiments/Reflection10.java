package reflection_api_experiments;

import java.lang.reflect.*;

/******************************************************************************
 * This demonstrates that all the rules of encapsulation can be broken.
 * 
 ******************************************************************************/

public class Reflection10 {
    public static void main(String[] args) throws Exception {
        Simple s = new Simple();
        Method m = s.getClass().getDeclaredMethod("setA", float.class);
        m.setAccessible(true);
        m.invoke(s, 76.5f);
        System.out.println(s);
    }
}
