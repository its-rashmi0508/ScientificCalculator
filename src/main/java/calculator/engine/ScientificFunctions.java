package calculator.engine;

public class ScientificFunctions {

    // Square Root
    public static double sqrt(double value) {
        if (value < 0) {
            throw new ArithmeticException("Invalid Input");
        }
        return Math.sqrt(value);
    }

    // Square
    public static double square(double value) {
        return value * value;
    }

    // Power
    public static double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    // Sine (Degrees)
    public static double sin(double degrees) {
        return Math.sin(Math.toRadians(degrees));
    }

    // Cosine (Degrees)
    public static double cos(double degrees) {
        return Math.cos(Math.toRadians(degrees));
    }

    // Tangent (Degrees)
    public static double tan(double degrees) {
        return Math.tan(Math.toRadians(degrees));
    }

    // Log Base 10
    public static double log(double value) {
        if (value <= 0) {
            throw new ArithmeticException("Invalid Input");
        }
        return Math.log10(value);
    }

    // Natural Log
    public static double ln(double value) {
        if (value <= 0) {
            throw new ArithmeticException("Invalid Input");
        }
        return Math.log(value);
    }

    // Factorial
    public static long factorial(int n) {

        if (n < 0) {
            throw new ArithmeticException("Invalid Input");
        }

        long result = 1;

        for (int i = 2; i <= n; i++) {
            result *= i;
        }

        return result;
    }
}