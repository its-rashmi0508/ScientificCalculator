package calculator.ui;

import calculator.theme.AppTheme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DisplayPanel extends JPanel {

    private JLabel expressionLabel;
    private JLabel resultLabel;

    public DisplayPanel() {

        setLayout(new BorderLayout());

        setBackground(AppTheme.DISPLAY);

        setPreferredSize(new Dimension(420, 140));

        setBorder(new EmptyBorder(20,20,20,20));

        expressionLabel = new JLabel("");

        expressionLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        expressionLabel.setForeground(Color.LIGHT_GRAY);

        expressionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        resultLabel = new JLabel("0");

        resultLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        resultLabel.setForeground(AppTheme.TEXT);

        resultLabel.setFont(new Font("Segoe UI", Font.BOLD, 42));

        JPanel textPanel = new JPanel(new GridLayout(2,1));
        textPanel.setOpaque(false);
        textPanel.add(expressionLabel);
        textPanel.add(resultLabel);
        add(textPanel, BorderLayout.CENTER);

    }

    // Updates the small expression line
    public void setExpression(String text) {
        expressionLabel.setText(text);
    }

    // Updates the main result
    public void setResult(String text) {
        resultLabel.setText(text);
    }
}