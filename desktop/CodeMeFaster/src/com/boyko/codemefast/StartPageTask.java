package com.boyko.codemefast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

public class StartPageTask extends JPanel {
    private String taskName;
    private String taskDescription;
    private String taskId;
    JPanel thePanel = this;

    public StartPageTask(String taskName, String taskDescription, String taskId) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskId = taskId;
        setLayout(null);
        setBounds(0, 0, 500, 50);
        setPreferredSize(new Dimension(500, 50));
        setBorder(BorderFactory.createLineBorder(Color.black));
        setBackground(Color.WHITE);
        ImageIcon uploadIcon = new ImageIcon(getClass().getResource("Icons/upload.png"));
        setLayout(null);
        setBorder(new EmptyBorder(5, 20, 5, 10));
        JLabel lbl = new JLabel(taskName);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        lbl.setBounds(10, 5, 300, 20);
        add(lbl);
        JTextArea descArea = new JTextArea(taskDescription);
        descArea.setLineWrap(true);
        descArea.setEditable(false);
        descArea.setBounds(10, 30, 450, 50);
        descArea.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        add(descArea);
    }
}
