package reflection_api_experiments;

import java.lang.reflect.Field;

/******************************************************************************
 * In this experiment a reference to a Simple object is obtained then used to
 * retrieve a collection of Fields that belong to its class. A field is another
 * name for instance variable.
 * 
 * When this code is run it prints details of a single field, a float called
 * a with value 10. The private access modifier has stopped details of the b
 * field from being revealed. This shows data hiding is working an Java is good
 * at maintaining data integrity.
 * 
 ******************************************************************************/

public class Reflection04 {
    public static void main(String[] args) throws Exception {
        Simple s = new Simple();
        Field[] fields = s.getClass().getFields();
        System.out.printf("There are %d fields\n", fields.length);
        for (Field f : fields) {
            if (f.getType() == float.class) {
                System.out.printf("field name=%s type=%s value=%.2f\n", f.getName(),
                        f.getType(), f.getFloat(s));
            } else {
                System.out.printf("field name=%s type=%s value=%d\n", f.getName(),
                        f.getType(), f.getInt(s));
            }
        }
    }
}
