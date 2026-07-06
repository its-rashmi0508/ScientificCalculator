package calculator.ui;

import calculator.engine.CalculatorEngine;
import calculator.theme.AppTheme;
import calculator.ui.components.RoundedButton;
import calculator.engine.MemoryManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ButtonPanel extends JPanel {

    private final DisplayPanel displayPanel;
    private final CalculatorEngine engine;
    private final CalculatorFrame frame;
    private final MemoryManager memory;

    public ButtonPanel(DisplayPanel displayPanel, CalculatorFrame frame) {

        this.frame = frame;
        this.displayPanel = displayPanel;
        this.engine = new CalculatorEngine();
        this.memory = new MemoryManager();

        setLayout(new GridLayout(7, 5, 10, 10));
        setBackground(AppTheme.BACKGROUND);
        setBorder(new EmptyBorder(15, 15, 15, 15));

        String[] buttons = {
                "MC","MR","MS","M+","M-",

                "sin", "cos", "tan", "log", "√",

                "(", ")", "π", "^", "x²",

                "÷", "7", "8", "9", "n!",

                "×", "4", "5", "6", "⌫",

                "-", "1", "2", "3", "C",

                "+","0",".","ln","="
        };

        for (String text : buttons) {

            RoundedButton button = new RoundedButton(text);
            switch (text) {
                case "+":
                case "-":
                case "×":
                case "÷":
                    button.setBackground(AppTheme.OPERATOR);
                    break;

                case "=":
                    button.setBackground(AppTheme.EQUALS);
                    break;

                case "C":
                case "⌫":
                    button.setBackground(new Color(220, 70, 70));
                    break;
                default:
                    button.setBackground(AppTheme.BUTTON);
            }
            button.addActionListener(e -> handleButton(button.getText()));
            add(button);
        }
    }

    private void handleButton(String value) {
        switch (value) {
            case "C":
                engine.clear();
                displayPanel.setExpression("");
                displayPanel.setResult("0");
                break;

            case "⌫":
                engine.backspace();
                displayPanel.setResult(engine.getExpression());
                break;

            case "π":
                engine.append(String.valueOf(Math.PI));
                displayPanel.setResult(engine.getExpression());
                break;

            case "e":
                engine.append(String.valueOf(Math.E));
                displayPanel.setResult(engine.getExpression());
                break;

            case "√":
                engine.append("sqrt(");
                displayPanel.setResult(engine.getExpression());
                break;

            case "sin":
                engine.append("sin(");
                displayPanel.setResult(engine.getExpression());
                break;

            case "cos":
                engine.append("cos(");
                displayPanel.setResult(engine.getExpression());
                break;

            case "tan":
                engine.append("tan(");
                displayPanel.setResult(engine.getExpression());
                break;

            case "log":
                engine.append("log(");
                displayPanel.setResult(engine.getExpression());
                break;

            case "ln":
                engine.append("ln(");
                displayPanel.setResult(engine.getExpression());
                break;

            case "MC":
                memory.clearMemory();
                break;
            case "MR":
                engine.setCurrentValue(memory.recall());
                displayPanel.setResult(engine.getExpression());
                break;
            case "MS":
                memory.store(engine.getCurrentValue());
                break;
            case "M+":
                memory.add(engine.getCurrentValue());
                break;
            case "M-":
                memory.subtract(engine.getCurrentValue());
                break;

            case "x²":
                engine.append("^2");
                displayPanel.setResult(engine.getExpression());
                break;

            case "n!":
                engine.append("!");
                displayPanel.setResult(engine.getExpression());
                break;

            case "=":
                String expression = engine.getExpression();
                String result = engine.evaluate();
                displayPanel.setExpression(expression);
                displayPanel.setResult(result);

                if (!result.equals("Error")) {
                    frame.addToHistory(expression, result);
                }
                break;

            default:

                engine.append(value);
                displayPanel.setResult(engine.getExpression());
        }
    }
}