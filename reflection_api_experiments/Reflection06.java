package reflection_api_experiments;

import java.lang.reflect.Field;

/******************************************************************************
 * This example prints the contents of its accessibility flag. This is
 * not to be confused with whether it is private or not, but is related to that.
 * 
 * The accessibility flags for both the public and private member are set to
 * false.
 * 
 ******************************************************************************/

public class Reflection06 {
    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws Exception {
        Simple s = new Simple();
        Field[] fields = s.getClass().getDeclaredFields();
        System.out.printf("There are %d fields\n", fields.length);

        for (Field f : fields) {
            System.out.printf("field name=%s type=%s accessible=%s\n", f.getName(),
                    f.getType(), f.isAccessible());
        }
    }
}
