package calculator.engine;

public class CalculatorEngine {

    private StringBuilder expression;

    public CalculatorEngine() {
        expression = new StringBuilder();
    }

    // Add text to the expression
    public void append(String value) {
        expression.append(value);
    }

    // Get current expression
    public String getExpression() {
        return expression.toString();
    }

    // Clear expression
    public void clear() {
        expression.setLength(0);
    }

    // Delete last character
    public void backspace() {
        if (expression.length() > 0) {
            expression.deleteCharAt(expression.length() - 1);
        }
    }

    // Evaluate expression
    public String evaluate() {

        if (expression.length() == 0) {
            return "0";
        }
        try {
            double result = ExpressionParser.evaluate(expression.toString());
            // Replace expression with result
            expression.setLength(0);
            double rounded = Math.round(result * 1_000_000_000d) / 1_000_000_000d;
            if (rounded == (long) rounded) {
                expression.append((long) rounded);
            } else {
                expression.append(rounded);
            }
            return expression.toString();

        } catch (Exception e) {
            expression.setLength(0);
            return "Error";
        }
    }
    public double getCurrentValue() {
        try {
            return Double.parseDouble(getExpression());
        } catch (Exception e) {
            return 0;
        }
    }
    public void setCurrentValue(double value) {
        expression.setLength(0);

        if(value == (long)value){
            expression.append((long)value);
        }else{
            expression.append(value);
        }
    }
}