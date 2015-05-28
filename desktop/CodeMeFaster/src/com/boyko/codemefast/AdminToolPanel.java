package com.boyko.codemefast;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AdminToolPanel extends JPanel {
    private ImageIcon participantImage = new ImageIcon(getClass().getResource("participantImage.jpg"));
    // private ImageIcon adminImage = new
    // ImageIcon(getClass().getResource("boiski.jpg"));
    private ImageIcon addTaskIcon = new ImageIcon(getClass().getResource("addTaskIcon.jpg"));
    private ImageIcon deleteTaskIcon = new ImageIcon(getClass().getResource("deleteTask.png"));
    private ImageIcon editDescriptionIcon = new ImageIcon(getClass().getResource("editDescription.jpg"));
    private ImageIcon deleteUserIcon = new ImageIcon(getClass().getResource("deleteUser.jpg"));
    private ImageIcon deleteProjectIcon = new ImageIcon(getClass().getResource("deleteProject.jpg"));
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
        addUserLbl.setBounds(5, 0, 40, 50);
        addUserLbl.setIcon(participantImage);
        addUserLbl.setToolTipText("Add User");
        add(addUserLbl);

        addTaskLbl = new JLabel();
        addTaskLbl.setIcon(addTaskIcon);
        addTaskLbl.setBounds(50, 0, 40, 50);
        addTaskLbl.setToolTipText("Add Task");
        add(addTaskLbl);

        deleteTaskLbl = new JLabel();
        deleteTaskLbl.setIcon(deleteTaskIcon);
        deleteTaskLbl.setBounds(95, 0, 40, 50);
        deleteTaskLbl.setToolTipText("Delete Task");
        add(deleteTaskLbl);

        editDescriptionLbl = new JLabel();
        editDescriptionLbl.setIcon(editDescriptionIcon);
        editDescriptionLbl.setBounds(140, 0, 40, 50);
        editDescriptionLbl.setToolTipText("Edit Description");
        add(editDescriptionLbl);

        deleteUserLbl = new JLabel();
        deleteUserLbl.setIcon(deleteUserIcon);
        deleteUserLbl.setBounds(190, 0, 40, 50);
        deleteUserLbl.setToolTipText("Delete User");
        add(deleteUserLbl);

        deleteProjectLbl = new JLabel();
        deleteProjectLbl.setIcon(deleteProjectIcon);
        deleteProjectLbl.setBounds(240, 0, 40, 50);
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
