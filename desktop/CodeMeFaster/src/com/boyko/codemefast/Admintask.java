package com.boyko.codemefast;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class Admintask extends JPanel {
    private String taskName;
    private String taskDescription;
    private String taskId;
    JPanel thePanel = this;

    public Admintask(String taskName, String taskDescription, String taskId) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskId = taskId;
        setLayout(null);
        setBounds(0, 0, 500, 100);
        setPreferredSize(new Dimension(500, 100));
        setBorder(BorderFactory.createLineBorder(Color.black));
        setBackground(Color.WHITE);
        ImageIcon deleteTaskIcon = new ImageIcon(getClass().getResource("Icons/DeleteTaskIcon.png"));
        ImageIcon markAsFinished = new ImageIcon(getClass().getResource("Icons/markAsFinished.png"));
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
        JLabel deleteTaskLbl;
        deleteTaskLbl = new JLabel();
        deleteTaskLbl.setIcon(deleteTaskIcon);
        deleteTaskLbl.setBounds(470,30, 60, 50);
        deleteTaskLbl.setToolTipText("Delete Task");
        deleteTaskLbl.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    ServerConnectionUtils.deleteRequest("api/tasks/" + taskId + "/" + UserData.getCurrentUser());
                    thePanel.setVisible(false);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        add(deleteTaskLbl);

        JLabel markAsFinishedLabel;
        markAsFinishedLabel = new JLabel();
        markAsFinishedLabel.setIcon(markAsFinished);
        markAsFinishedLabel.setBounds(530,30, 60, 50);
        markAsFinishedLabel.setToolTipText("Mark as finished");
        markAsFinishedLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    ServerConnectionUtils.putRequest("api/tasks/" + taskId);
                    thePanel.setVisible(false);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        add(markAsFinishedLabel);
    }
}
