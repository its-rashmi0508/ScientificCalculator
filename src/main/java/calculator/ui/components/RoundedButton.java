package calculator.ui.components;

import calculator.theme.AppTheme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RoundedButton extends JButton {

    public RoundedButton(String text) {

        super(text);

        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);

        setOpaque(false);

        setForeground(AppTheme.TEXT);
        setBackground(AppTheme.BUTTON);

        setFont(new Font("Segoe UI Variable", Font.BOLD, 22));

        setBorder(new EmptyBorder(15,15,15,15));

        setCursor(new Cursor(Cursor.HAND_CURSOR));

    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground());

        g2.fillRoundRect(
                0,
                0,
                getWidth(),
                getHeight(),
                25,
                25);

        super.paintComponent(g);

        g2.dispose();
    }

}