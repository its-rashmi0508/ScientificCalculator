package calculator.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HistoryPanel extends JPanel {

    private final DefaultListModel<String> historyModel;
    private final JList<String> historyList;

    public HistoryPanel() {

        setLayout(new BorderLayout());

        setPreferredSize(new Dimension(220, 0));

        setBorder(new EmptyBorder(10,10,10,10));

        JLabel title = new JLabel("History");

        title.setFont(new Font("Segoe UI", Font.BOLD, 18));

        add(title, BorderLayout.NORTH);

        historyModel = new DefaultListModel<>();

        historyList = new JList<>(historyModel);

        historyList.setFont(new Font("Consolas", Font.PLAIN, 16));

        JScrollPane scrollPane = new JScrollPane(historyList);

        add(scrollPane, BorderLayout.CENTER);

    }
    public void addHistory(String expression, String result){
        historyModel.addElement(expression + " = " + result);
    }
    public void clearHistory(){
        historyModel.clear();
    }

}