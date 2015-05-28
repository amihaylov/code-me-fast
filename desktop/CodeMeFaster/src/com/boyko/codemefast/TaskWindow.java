package com.boyko.codemefast;

import java.awt.Cursor;
import java.awt.Dimension;
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

        setPreferredSize(new Dimension(100, 50));
        nameLbl = new JLabel(projectName + "." + taskName);
        nameLbl.setFont(new Font("Serif", Font.ITALIC, 20));
        deleteTaskLbl = new JLabel();
        deleteTaskLbl.setIcon(deleteTaskIcon);
        // deleteTaskLbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(nameLbl);
    }
}
