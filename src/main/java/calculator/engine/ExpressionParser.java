package calculator.engine;
public class ExpressionParser {

    private String expression;
    private int position = -1;
    private int currentChar;
    public static double evaluate(String expression) {
        return new ExpressionParser().parse(expression);
    }

    private double parse(String expression) {
        this.expression = expression
                .replace("×", "*")
                .replace("÷", "/")
                .replace("π", String.valueOf(Math.PI))
                .replace("e", String.valueOf(Math.E));
        nextChar();

        double value = parseExpression();
        if (position < this.expression.length()) {
            throw new RuntimeException("Unexpected character: " + (char) currentChar);
        }
        return value;
    }
    private void nextChar() {
        position++;
        if (position < expression.length()) {
            currentChar = expression.charAt(position);
        } else {
            currentChar = -1;
        }
    }
    private boolean eat(int charToEat) {
        while (currentChar == ' ') {
            nextChar();
        }
        if (currentChar == charToEat) {
            nextChar();
            return true;
        }
        return false;
    }
    // expression = term + term
    private double parseExpression() {
        double value = parseTerm();
        while (true) {
            if (eat('+')) {
                value += parseTerm();
            } else if (eat('-')) {
                value -= parseTerm();
            } else {
                return value;
            }
        }
    }
    // term = factor * factor
    private double parseTerm() {
        double value = parseFactor();
        while (true) {
            if (eat('*')) {
                value *= parseFactor();
            } else if (eat('/')) {
                double divisor = parseFactor();
                if (divisor == 0)
                    throw new ArithmeticException("Division by zero");
                value /= divisor;
            } else {
                return value;
            }
        }
    }
    // factor = number | function | (expression) | unary +/- | power
    private double parseFactor() {

        if (eat('+')) {
            return parseFactor();
        }
        if (eat('-')) {
            return -parseFactor();
        }
        double value;
        int startPos = this.position;
        // Parentheses
        if (eat('(')) {
            value = parseExpression();
            if (!eat(')')) {
                throw new RuntimeException("Missing ')'");
            }
        }
        // Numbers
        else if ((currentChar >= '0' && currentChar <= '9') || currentChar == '.') {
            while ((currentChar >= '0' && currentChar <= '9') || currentChar == '.') {
                nextChar();
            }
            value = Double.parseDouble(expression.substring(startPos, this.position));
        }
        // Functions
        else if (Character.isLetter(currentChar)) {
            while (Character.isLetter(currentChar)) {
                nextChar();
            }
            String function = expression.substring(startPos, this.position);
            value = parseFactor();
            switch (function) {
                case "sqrt":
                    value = ScientificFunctions.sqrt(value);
                    break;
                case "sin":
                    value = ScientificFunctions.sin(value);
                    break;
                case "cos":
                    value = ScientificFunctions.cos(value);
                    break;
                case "tan":
                    value = ScientificFunctions.tan(value);
                    break;
                case "log":
                    value = ScientificFunctions.log(value);
                    break;
                case "ln":
                    value = ScientificFunctions.ln(value);
                    break;
                default:
                    throw new RuntimeException("Unknown function: " + function);
            }
        }
        else {
            throw new RuntimeException("Unexpected character: " + (char) currentChar);
        }
        // Power (^)
        if (eat('^')) {
            value = ScientificFunctions.power(value, parseFactor());
        }
// Factorial (!)
        if (eat('!')) {
            if (value < 0 || value != Math.floor(value)) {
                throw new RuntimeException("Invalid factorial");
            }
            long fact = 1;
            for (int i = 1; i <= (int) value; i++) {
                fact *= i;
            }
            value = fact;
        }
// Percentage (%)
        if (eat('%')) {
            value = value / 100.0;
        }
        return value;
    }
}