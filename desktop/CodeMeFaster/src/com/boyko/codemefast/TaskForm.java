package com.boyko.codemefast;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
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
    private JComboBox<String> endDay;
    private JComboBox<String> endMonth;
    private JComboBox<String> endYear;
    private JComboBox<String> comboLevel;
    private JComboBox<String> comboType;
    private JComboBox<String> userCombo;
    private JTextArea descriptionArea;
    private JTextField nameArea;
    private JLabel chooseUser;

    public TaskForm() {
        setLayout(null);
        JLabel taskName = Utility.createLable("Task Name", 20, 5, 10, 100, 50);
        nameArea = new JTextField();
        nameArea.setFont(new Font("Serif", Font.ITALIC, 16));
        nameArea.setBounds(170, 20, 160, 30);
        JLabel projectEnd = Utility.createLable("End Date", 20, 5, 48, 100, 50);
        endDay = ComboCalendar.daysCombo();
        endDay.setCursor(new Cursor(Cursor.HAND_CURSOR));
        endDay.setBounds(170, 61, 50, 25);
        endMonth = ComboCalendar.monthsCombo();
        endMonth.setCursor(new Cursor(Cursor.HAND_CURSOR));
        endMonth.setBounds(230, 61, 100, 25);
        endYear = ComboCalendar.yearsCombo();
        endYear.setCursor(new Cursor(Cursor.HAND_CURSOR));
        endYear.setBounds(340, 61, 80, 25);
        add(endYear);
        add(endMonth);
        add(endDay);
        add(projectEnd);
        JLabel chooseType = Utility.createLable("Choose Type", 20, 5, 110, 200, 50);
        comboType = new JComboBox<String>();
        comboType.setCursor(new Cursor(Cursor.HAND_CURSOR));
        comboType.addItem("Ui");
        comboType.addItem("Date Base");
        comboType.addItem("Server");
        comboType.addItem("Code behind");
        comboType.setBounds(170, 120, 160, 30);

        JLabel chooseDifficulty = Utility.createLable("Choose Difficulty", 20, 5, 80, 200, 50);
        comboLevel = new JComboBox<>();
        comboLevel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        comboLevel.addItem("No difficulty");
        comboLevel.addItem("soft");
        comboLevel.addItem("Medium");
        comboLevel.addItem("Hard");
        comboLevel.setBounds(170, 90, 160, 30);

        JLabel chooseUser = Utility.createLable("Choose User", 20, 5, 137, 200, 50);
        userCombo = new JComboBox<String>();
        userCombo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        getUserCombo().setBounds(170, 150, 160, 30);
        add(chooseUser);
        add(getUserCombo());

        add(chooseDifficulty);
        add(chooseType);
        add(comboLevel);
        add(comboType);
        add(taskName);
        add(nameArea);
        JLabel description = Utility.createLable("Description", 20, 5, 165, 100, 50);
        descriptionArea = new JTextArea();
        getDescriptionArea().setFont(new Font("Serif", Font.ITALIC, 20));
        JScrollPane scrollpane = new JScrollPane(getDescriptionArea(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollpane.setBounds(6, 210, 460, 180);
        getDescriptionArea().setLineWrap(true);
        add(description);
        add(scrollpane);

    }

    public void resetTaskForm() {
        getDescriptionArea().setText("");
        nameArea.setText("");
        comboType.setSelectedIndex(0);
        comboLevel.setSelectedIndex(0);
        endDay.setSelectedIndex(0);
        endMonth.setSelectedIndex(0);
        endYear.setSelectedIndex(0);
    }

    public JComboBox<String> getUserCombo() {
        return userCombo;
    }

    public JTextArea getDescriptionArea() {
        return descriptionArea;
    }
}
