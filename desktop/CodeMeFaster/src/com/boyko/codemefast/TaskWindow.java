package com.boyko.codemefast;

import java.awt.Cursor;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TaskWindow extends JPanel {

    private JLabel nameLbl;
    private JLabel deleteTaskLbl;
    private ImageIcon deleteTaskIcon = new ImageIcon(getClass().getResource("deleteTask.png"));

    public TaskWindow() {

    }

    public TaskWindow(String projectName, String taskName) {
        setLayout(null);
        nameLbl = new JLabel(projectName + "." + taskName);
        nameLbl.setFont(new Font("Serif", Font.ITALIC, 20));
        nameLbl.setBounds(5, 7, 270, 50);
        deleteTaskLbl = new JLabel();
        deleteTaskLbl.setIcon(deleteTaskIcon);
        deleteTaskLbl.setBounds(200, 0, 40, 50);
        deleteTaskLbl.setToolTipText("Delete Task");
        deleteTaskLbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(deleteTaskLbl);
        add(nameLbl);
    }
}
