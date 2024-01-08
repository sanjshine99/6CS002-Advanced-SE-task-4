package test;

import reflection_api_experiments.Simple;

/******************************************************************************
 * This code demonstrates the use of the improved unit testing framework.
 ******************************************************************************/

public class SimpleTests {

    public void checkConstructorAndAccess() {
        Simple s = new Simple(3.0f, 4.0f);
        SimpleUnitTestFramework.assertEquals(s.getA(), 3.0f);
        SimpleUnitTestFramework.assertEquals(s.getB(), 4.0f);
        SimpleUnitTestFramework.assertNotEquals(s.getB(), 3.0f);
        SimpleUnitTestFramework.assertNotEquals(s.getB(), 5.0f);
    }

    public void checkSquareA() {
        Simple s = new Simple(3.0f, 4.0f);
        s.squareA();
        SimpleUnitTestFramework.assertEquals(s.getA(), 9.0f);
    }

    public void assertPrivateSquareB() {
        Simple s = new Simple(3.0f, 4.0f);
        SimpleUnitTestFramework.invokePrivateMethod(s, "squareB");
        SimpleUnitTestFramework.assertEquals(s.getB(), 16.0f);
    }

    public void assertPrivateFieldExample() {
        Simple s = new Simple(3.0f, 4.0f);
        SimpleUnitTestFramework.assertPrivateField(s, "b", 5.0f);
    }

    public static void main(String[] args) {
        SimpleUnitTestFramework.runChecks(new SimpleTests());
        SimpleUnitTestFramework.report();
    }
}
