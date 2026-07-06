package calculator.ui;

import calculator.theme.AppTheme;

import javax.swing.*;
import java.awt.*;

public class CalculatorFrame extends JFrame {

    private DisplayPanel displayPanel;
    private ButtonPanel buttonPanel;
    private HistoryPanel historyPanel;

    public CalculatorFrame() {

        setTitle("Scientific Calculator Pro");

        setSize(700, 650);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setResizable(false);

        getContentPane().setBackground(AppTheme.BACKGROUND);

        setLayout(new BorderLayout());

        displayPanel = new DisplayPanel();

        buttonPanel = new ButtonPanel(displayPanel, this);

        historyPanel = new HistoryPanel();

        JPanel calculatorPanel = new JPanel(new BorderLayout());

        calculatorPanel.setBackground(AppTheme.BACKGROUND);

        calculatorPanel.add(displayPanel, BorderLayout.NORTH);

        calculatorPanel.add(buttonPanel, BorderLayout.CENTER);

        add(calculatorPanel, BorderLayout.CENTER);

        add(historyPanel, BorderLayout.EAST);

        setVisible(true);
    }
    public HistoryPanel getHistoryPanel() {
        return historyPanel;
    }
    public void addToHistory(String expression, String result) {
        historyPanel.addHistory(expression, result);
    }
}