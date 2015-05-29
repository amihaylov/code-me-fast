package com.boyko.codemefast;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ProjectForm extends JPanel {
    private JLabel projectName;
    private JTextField nameArea;
    private JComboBox<String> combo;
    private JLabel projectType;
    private JTextArea descriptionArea;

    public ProjectForm() {
        setLayout(null);
        setBackground(Color.WHITE);
        projectName = Utility.createLable("Project Name", 20.3f, 22, 25, 150, 30);
        nameArea = new JTextField();
        getNameArea().setFont(new Font("Serif", Font.ITALIC, 16));
        getNameArea().setBounds(160, 25, 150, 30);

        JLabel projectType = Utility.createLable("Project Type", 20.3f, 22, 70, 150, 30);
        combo = new JComboBox<>();
        combo.addItem("Regular project");
        combo.addItem("HackaThlon");
        combo.setBounds(160, 70, 150, 30);

        JLabel description = Utility.createLable("Description", 20.3f, 22, 120, 150, 30);
        descriptionArea = new JTextArea();
        descriptionArea.setFont(new Font("Serif", Font.ITALIC, 20));
        JScrollPane jsp = new JScrollPane(descriptionArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jsp.setBounds(22, 160, 500, 300);
        descriptionArea.setLineWrap(true);

        add(projectName);
        add(getNameArea());
        add(projectType);
        add(combo);
        add(description);
        add(jsp);
    }

    public void resetProjectForm() {
        getNameArea().setText("");
        descriptionArea.setText("");
        combo.setSelectedIndex(0);
    }

    public String getSelectedType(){
        return  String.valueOf(combo.getSelectedIndex() + 1);
    }

    public JTextField getNameArea() {
        return nameArea;
    }

    public JTextArea getDescriptionArea() {
        return descriptionArea;
    }
}
