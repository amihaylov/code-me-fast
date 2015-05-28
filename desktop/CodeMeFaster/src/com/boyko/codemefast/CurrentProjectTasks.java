package com.boyko.codemefast;

import java.awt.Color;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class CurrentProjectTasks extends JPanel {
    public CurrentProjectTasks() {

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBackground(Color.PINK);
        for (int i = 1; i < 100; i++) {
            JLabel lbl = new JLabel("CodemeFast" + "Task" + i + "");
            lbl.setFont(new Font("Serif", Font.ITALIC, 20));
            add(lbl);
            add(Box.createVerticalStrut(20));
        }
    }
}
