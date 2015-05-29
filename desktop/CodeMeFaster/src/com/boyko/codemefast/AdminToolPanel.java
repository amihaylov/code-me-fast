package com.boyko.codemefast;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AdminToolPanel extends JPanel {
    private ImageIcon participantImage = new ImageIcon(getClass().getResource("Icons/AddUser.png"));
    private ImageIcon addTaskIcon = new ImageIcon(getClass().getResource("Icons/AddTask.png"));
    private ImageIcon deleteTaskIcon = new ImageIcon(getClass().getResource("Icons/DeleteTaskIcon.png"));
    private ImageIcon editDescriptionIcon = new ImageIcon(getClass().getResource("Icons/EditProjectIcon.png"));
    private ImageIcon deleteUserIcon = new ImageIcon(getClass().getResource("Icons/DeleteUser.png"));
    private ImageIcon deleteProjectIcon = new ImageIcon(getClass().getResource("Icons/DeleteProject.png"));
    private JLabel addUserLbl;
    private JLabel addTaskLbl;
    private JLabel deleteTaskLbl;
    private JLabel editDescriptionLbl;
    private JLabel deleteUserLbl;
    private JLabel deleteProjectLbl;

    public AdminToolPanel() {
        setLayout(null);
        setBackground(Color.WHITE);

        addUserLbl = new JLabel();
        addUserLbl.setBounds(5, 0, 60, 50);
        addUserLbl.setIcon(participantImage);
        addUserLbl.setToolTipText("Add User");
        add(addUserLbl);

        addTaskLbl = new JLabel();
        addTaskLbl.setIcon(addTaskIcon);
        addTaskLbl.setBounds(65, 0, 60, 50);
        addTaskLbl.setToolTipText("Add Task");
        add(addTaskLbl);

        editDescriptionLbl = new JLabel();
        editDescriptionLbl.setIcon(editDescriptionIcon);
        editDescriptionLbl.setBounds(185, 0, 60, 50);
        editDescriptionLbl.setToolTipText("Edit Description");
        add(editDescriptionLbl);

        deleteUserLbl = new JLabel();
        deleteUserLbl.setIcon(deleteUserIcon);
        deleteUserLbl.setBounds(245, 0, 60, 50);
        deleteUserLbl.setToolTipText("Delete User");
        add(deleteUserLbl);

        deleteProjectLbl = new JLabel();
        deleteProjectLbl.setIcon(deleteProjectIcon);
        deleteProjectLbl.setBounds(125, 0, 60, 50);
        deleteProjectLbl.setToolTipText("Delete Project");
        add(deleteProjectLbl);

    }

    public JLabel getAddUserLbl() {
        return addUserLbl;
    }

    public JLabel getAddTaskLbl() {
        return addTaskLbl;
    }

    public JLabel getDeleteTaskLbl() {
        return deleteTaskLbl;
    }

    public JLabel getEditDescriptionLbl() {
        return editDescriptionLbl;
    }

    public JLabel getDeleteUserLbl() {
        return deleteUserLbl;
    }

    public JLabel getDeleteProjectLbl() {
        return deleteProjectLbl;
    }

}