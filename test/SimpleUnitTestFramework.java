package test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/******************************************************************************
 * This is an improved version of a simple unit testing framework. It includes
 * features similar to JUnit assertions, a launcher, resilience to exceptions,
 * complex assertions, and logging.
 * 
 * @author Dr Kevan Buckley, University of Wolverhampton, 2019
 ******************************************************************************/

public class SimpleUnitTestFramework {
    private static List<String> checks;
    private static int checksMade = 0;
    private static int passedChecks = 0;
    private static int failedChecks = 0;

    private static final Logger LOGGER = Logger.getLogger(SimpleUnitTestFramework.class.getName());

    static {
        try {
            FileHandler fileHandler = new FileHandler("test-framework.log");
            LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void logInfo(String message) {
        LOGGER.log(Level.INFO, message);
    }

    public static void logWarning(String message) {
        LOGGER.log(Level.WARNING, message);
    }

    public static void logError(String message, Throwable throwable) {
        LOGGER.log(Level.SEVERE, message, throwable);
    }

    /******************************************************************************
     * A report is formed from a list of strings. This method adds a message to the
     * report.
     ******************************************************************************/

    private static void addToReport(String txt) {
        LocalDateTime timestamp = LocalDateTime.now();
        String logMessage = String.format("[%s] %s", timestamp, txt);
        logInfo(logMessage);
        logInfo(txt);

        if (checks == null) {
            checks = new ArrayList<>();
        }
        checks.add(String.format("%04d: %s", checksMade++, logMessage, txt));
    }

    /******************************************************************************
     * This method checks if two values are equal and adds an appropriate message to
     * the report.
     ******************************************************************************/

    public static void assertEquals(Object expected, Object actual) {
        if (expected.equals(actual)) {
            addToReport(String.format("  %s == %s", expected, actual));
            passedChecks++;
        } else {
            addToReport(String.format("* %s == %s", expected, actual));
            failedChecks++;
        }
    }

    /******************************************************************************
     * This method checks if two values are not equal and adds an appropriate
     * message to the report.
     ******************************************************************************/

    public static void assertNotEquals(Object expected, Object actual) {
        if (!expected.equals(actual)) {
            addToReport(String.format("  %s != %s", expected, actual));
            passedChecks++;
        } else {
            addToReport(String.format("* %s != %s", expected, actual));
            failedChecks++;
        }
    }

    /******************************************************************************
     * Runs all methods in the given object whose name starts with "check".
     ******************************************************************************/

    public static void runChecks(Object testObject) {
        runPublicChecks(testObject);
        runPrivateChecks(testObject);
    }

    private static void runPublicChecks(Object testObject) {
        Method[] methods = testObject.getClass().getDeclaredMethods();

        for (Method method : methods) {
            if (method.getName().startsWith("check") && Modifier.isPublic(method.getModifiers())) {
                try {
                    method.invoke(testObject);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    handleException(testObject, method, e);
                }
            }
        }
    }

    private static void runPrivateChecks(Object testObject) {
        Method[] methods = testObject.getClass().getDeclaredMethods();

        for (Method assertMethod : methods) {
            if (assertMethod.getName().startsWith("assertPrivate") && Modifier.isPublic(assertMethod.getModifiers())) {
                try {
                    assertMethod.invoke(testObject);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    handleException(testObject, assertMethod, e);
                }
            }
        }
    }

    private static void handleException(Object testObject, Method method, Exception e) {
        String testName = testObject.getClass().getSimpleName();
        String methodName = method.getName();
        LOGGER.log(Level.SEVERE,
                String.format("Exception occurred during %s.%s: %s", testName, methodName, e.getMessage()), e);
        addToReport(String.format("* Exception occurred during %s.%s: %s", testName, methodName, e.getMessage()));
        failedChecks++;
    }

    public static void invokePrivateMethod(Object obj, String methodName) {
        try {
            Method privateMethod = obj.getClass().getDeclaredMethod(methodName);
            privateMethod.setAccessible(true);
            privateMethod.invoke(obj);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void assertPrivateField(Object obj, String fieldName, float expectedValue) {
        try {
            Field privateField = obj.getClass().getDeclaredField(fieldName);
            privateField.setAccessible(true);
            float actualValue = (float) privateField.get(obj);
            SimpleUnitTestFramework.assertEquals(expectedValue, actualValue);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /******************************************************************************
     * Outputs the messages that form the report.
     ******************************************************************************/

    public static void report() {
        System.out.printf("%d checks passed\n", passedChecks);
        System.out.printf("%d checks failed\n", failedChecks);
        System.out.println();

        for (String check : checks) {
            System.out.println(check);
        }
    }

    /******************************************************************************
     * Main method to demonstrate the usage of the testing framework.
     ******************************************************************************/

    public static void main(String[] args) {
        SimpleUnitTestFramework.runChecks(new SimpleTests());
        SimpleUnitTestFramework.report();
    }
}
