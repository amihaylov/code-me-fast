package com.boyko.codemefast;

import java.awt.BorderLayout;
import java.awt.Color;
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
    private ImageIcon addProjectIcon = new ImageIcon(getClass().getResource("add.png"));
    private JButton submitTask = new JButton("add");
    ProjectForm projectForm = new ProjectForm();
    AdminToolPanel toolPanel = new AdminToolPanel();
    UserForm userForm = new UserForm();
    TaskForm taskForm = new TaskForm();
    CurrentProjectTasks currentProjectTasks = new CurrentProjectTasks();
    TaskWindow taskWindow = new TaskWindow();

    public BottomPanel() {
        JPanel allTasksPanel = new JPanel();
        int a = 20;
        for (int i = 0; i < 20; i++) {
            TaskWindow task = new TaskWindow("CodemeFast.", "Task" + i + "");
            task.setBounds(200, a, 300, 50);
            allTasksPanel.add(task);
            // allTasksPanel.add(Box.createVerticalStrut(20));
            allTasksPanel.repaint();
            ;
            a += 70;
        }
        allTasksPanel.setLayout(new BorderLayout());
        allTasksPanel.setBackground(Color.YELLOW);
        JScrollPane scrollPane = new JScrollPane();

        allTasksPanel.setBounds(300, 100, 900, 500);
        allTasksPanel.add(scrollPane,BorderLayout.CENTER);
        setLayout(null);
        setBackground(Color.RED);

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
                remove(allTasksPanel);
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
        userForm.getAddUser().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String username = userForm.getUserNameTextArea().getText();
                taskForm.getUserCombo().addItem(username);
            }
        });
    }
}
