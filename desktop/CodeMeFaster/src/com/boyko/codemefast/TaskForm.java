package com.boyko.codemefast;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TaskForm extends JPanel {
    // private ImageIcon markedIcon = new
    // ImageIcon(getClass().getResource("markedIcon.jpg"));
    private JComboBox<String> startDays;
    private JComboBox<String> startMonth;
    private JComboBox<String> startYear;
    private JComboBox<String> endDays;
    private JComboBox<String> endMonths;
    private JComboBox<String> endYears;
    private JComboBox<String> comboLevel;
    private JComboBox<String> comboType;
    private JTextArea descriptionArea;
    private JTextField nameArea;
    String str;

    public TaskForm() {
        setLayout(null);
        JLabel TaskName = Utility.createLable("Task Name", 20, 5, 10, 100, 50);
        nameArea = new JTextField();
        nameArea.setFont(new Font("Serif", Font.ITALIC, 16));
        nameArea.setBounds(170, 20, 160, 30);
        JLabel projectEnd = Utility.createLable("End Date", 20, 5, 48, 100, 50);
        endDays = ComboCalendar.daysCombo();
        endDays.setBounds(170, 61, 50, 25);
        endMonths = ComboCalendar.monthsCombo();
        endMonths.setBounds(230, 61, 100, 25);
        endYears = ComboCalendar.yearsCombo();
        endYears.setBounds(340, 61, 80, 25);
        add(endYears);
        add(endMonths);
        add(endDays);
        add(projectEnd);
        JLabel chooseType = Utility.createLable("Choose Type", 20, 5, 110, 200, 50);
        comboType = new JComboBox<String>();
        comboType.addItem("Ui");
        comboType.addItem("Date Base");
        comboType.addItem("Server");
        comboType.addItem("Code behind");
        comboType.setBounds(170, 120, 160, 30);

        JLabel chooseDifficulty = Utility.createLable("Choose Difficulty", 20, 5, 80, 200, 50);
        comboLevel = new JComboBox<>();
        comboLevel.addItem("No difficulty");
        comboLevel.addItem("soft");
        comboLevel.addItem("Medium");
        comboLevel.addItem("Hard");
        comboLevel.setBounds(170, 90, 160, 30);

        add(chooseDifficulty);
        add(chooseType);
        add(comboLevel);
        add(comboType);
        add(TaskName);
        add(nameArea);
        JLabel description = Utility.createLable("Description", 20, 5, 160, 100, 50);
        descriptionArea = new JTextArea();
        descriptionArea.setFont(new Font("Serif", Font.ITALIC, 20));
        JScrollPane scrollpane = new JScrollPane(descriptionArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollpane.setBounds(6, 210, 460, 180);
        descriptionArea.setLineWrap(true);
        add(description);
        add(scrollpane);

    }

    public void resetTaskForm() {
        descriptionArea.setText("");
        nameArea.setText("");
        comboType.setSelectedIndex(0);
        comboLevel.setSelectedIndex(0);
        endDays.setSelectedIndex(0);
        endMonths.setSelectedIndex(0);
        endYears.setSelectedIndex(0);
    }

}
