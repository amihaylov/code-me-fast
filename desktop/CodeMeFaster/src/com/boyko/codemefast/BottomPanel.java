package com.boyko.codemefast;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class BottomPanel extends JPanel {
    CardLayout cl = new CardLayout();
    JPanel contentPanel = new JPanel();
    private ImageIcon addProjectIcon = new ImageIcon(getClass().getResource("add.png"));
    private JButton submitTask = new JButton("add");
    JScrollPane scrollPane;
    ProjectForm projectForm = new ProjectForm();
    AdminToolPanel toolPanel = new AdminToolPanel();
    UserForm userForm = new UserForm();
    TaskForm taskForm = new TaskForm();
    CurrentProjectTasks currentProjectTasks = new CurrentProjectTasks();
    TaskWindow taskWindow = new TaskWindow();

    private void showPanel(JPanel panel, String panelName) {
        contentPanel.setLayout(cl);
        contentPanel.add(projectForm, "projectForm");
        contentPanel.add(toolPanel, "toolPanel");
        contentPanel.add(userForm, "userForm");
        contentPanel.add(taskForm, "taskForm");
        contentPanel.add(currentProjectTasks, "currentProjectTasks");
        contentPanel.add(taskWindow, "taskWindow");
        cl.show(panel, panelName);
    }

    public BottomPanel() {
        setBackground(Color.RED);
        setLayout(null);
        JPanel allTasksPanel = new JPanel();
        allTasksPanel.setLayout(new BoxLayout(allTasksPanel, BoxLayout.Y_AXIS));
        for (int i = 1; i < 100; i++) {
            JLabel lbl = new JLabel("CodemeFast" + "Task" + i + "");
            lbl.setFont(new Font("Serif", Font.ITALIC, 20));
            allTasksPanel.add(lbl);
            allTasksPanel.add(Box.createVerticalStrut(20));
        }
        allTasksPanel.setBackground(Color.YELLOW);
        scrollPane = new JScrollPane(allTasksPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(300, 100, 500, 550);
        add(scrollPane);

        JLabel lbl = new JLabel("Add Project");
        lbl.setFont(lbl.getFont().deriveFont(22.3f));
        lbl.setBounds(22, 50, 150, 30);
        add(lbl);

        JLabel addProjectLbl = new JLabel();
        addProjectLbl.setIcon(addProjectIcon);
        addProjectLbl.setBounds(170, 30, 62, 60);
        add(addProjectLbl);

        JPanel staticProjectPanel = new JPanel();
        staticProjectPanel.setLayout(new BoxLayout(staticProjectPanel, BoxLayout.Y_AXIS));
        staticProjectPanel.setBackground(Color.WHITE);
        staticProjectPanel.setBounds(10, 100, 250, 550);
        add(staticProjectPanel);

        JButton submitProject = new JButton("submit");
        submitProject.setBounds(300, 600, 90, 30);
        submitProject.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // get Request here
                JLabel projLbl = new JLabel("  " + projectForm.getNameArea().getText());
                projLbl.setFont(projLbl.getFont().deriveFont(20.0f));
                staticProjectPanel.add(Box.createVerticalStrut(20));
                staticProjectPanel.add(projLbl);
                staticProjectPanel.revalidate();
                remove(projectForm);
                remove(submitProject);
                toolPanel.setBounds(300, 100, 900, 50);
                add(toolPanel);
                currentProjectTasks.setBounds(300, 180, 900, 450);
                add(currentProjectTasks);
                repaint();
            }
        });
        addProjectLbl.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                remove(scrollPane);
                remove(submitTask);
                remove(taskForm);
                remove(toolPanel);
                remove(submitTask);
                remove(userForm);
                projectForm.setBounds(300, 100, 600, 450);
                add(projectForm);
                add(submitProject);
                repaint();
                projectForm.resetProjectForm();
            }
        });
        toolPanel.getAddUserLbl().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                taskForm.resetTaskForm();
                taskForm.revalidate();
                remove(taskForm);
                remove(currentProjectTasks);
                remove(submitTask);
                userForm.setBounds(540, 350, 350, 50);
                add(userForm);
                repaint();
            }
        });
        toolPanel.getAddTaskLbl().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                remove(userForm);
                remove(currentProjectTasks);
                taskForm.setBounds(300, 200, 500, 400);
                add(taskForm);
                submitTask.setBounds(300, 610, 90, 30);
                add(submitTask);
                repaint();
            }
        });
        submitTask.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // request here
                taskForm.resetTaskForm();
            }
        });
        userForm.getCanselUser().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                remove(userForm);
                repaint();
            }
        });
        userForm.getAddUser().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String username = userForm.getUserNameTextArea().getText();
                taskForm.getUserCombo().addItem(username);
                userForm.getUserNameTextArea().setText("");
                remove(userForm);
                repaint();
            }
        });
    }
}
