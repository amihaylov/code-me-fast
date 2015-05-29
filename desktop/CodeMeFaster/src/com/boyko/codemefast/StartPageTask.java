package com.boyko.codemefast;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StartPageTask extends JPanel{
    private String taskName;
    private String taskDescription;
    private String taskType;

    public StartPageTask(String taskName, String taskDescription, String taskType) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskType = taskType;

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(new EmptyBorder(5, 20, 5, 10));
        JLabel lbl = new JLabel(taskName);
        lbl.setFont(new Font("Serif", Font.ITALIC, 20));
        add(lbl);
    }
}
