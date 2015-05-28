package com.boyko.codemefast;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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
    private JButton submitTask = Utility.createButton("add", KeyEvent.VK_ENTER);
    private JScrollPane allTaskScrollPane;
    private JScrollPane projectPanelScrollPane;
    private JScrollPane currentTasksScroll;
    ProjectForm projectForm = new ProjectForm();
    AdminToolPanel toolPanel = new AdminToolPanel();
    UserForm userForm = new UserForm();
    TaskForm taskForm = new TaskForm();
    CurrentProjectTasks currentProjectTasks = new CurrentProjectTasks();
    TaskWindow taskWindow = new TaskWindow();

    private JScrollPane createAllTasksPanel() {
        JPanel allTasksPanel = new JPanel();
        allTasksPanel.setLayout(new BoxLayout(allTasksPanel, BoxLayout.Y_AXIS));
        for (int i = 1; i < 100; i++) {
            JLabel lbl = new JLabel("CodemeFast" + "Task" + i + "");
            lbl.setFont(new Font("Serif", Font.ITALIC, 20));
            allTasksPanel.add(lbl);
            allTasksPanel.add(Box.createVerticalStrut(20));
        }
        allTasksPanel.setBackground(Color.YELLOW);
        allTaskScrollPane = new JScrollPane(allTasksPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        allTaskScrollPane.setBounds(300, 100, 500, 550);
        return allTaskScrollPane;
    }

    public BottomPanel() {
        setBackground(Color.WHITE);
        setLayout(null);
        add(createAllTasksPanel());

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
        projectPanelScrollPane = new JScrollPane(staticProjectPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        projectPanelScrollPane.setBounds(10, 100, 250, 550);
        add(projectPanelScrollPane);

        JButton submitProject = Utility.createButton("Submit", KeyEvent.VK_ENTER);
        submitProject.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submitProject.setBounds(320, 590, 90, 30);
        currentTasksScroll = new JScrollPane(currentProjectTasks, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        currentTasksScroll.setBounds(300, 180, 500, 460);

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
                add(currentTasksScroll);
                repaint();
            }
        });
        addProjectLbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addProjectLbl.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                remove(currentTasksScroll);
                remove(allTaskScrollPane);
                remove(submitTask);
                remove(taskForm);
                remove(toolPanel);
                remove(submitTask);
                remove(userForm);
                projectForm.setBounds(280, 100, 600, 470);
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
                remove(currentTasksScroll);
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
                remove(currentTasksScroll);
                taskForm.setBounds(300, 200, 500, 400);
                add(taskForm);
                submitTask.setBounds(300, 610, 90, 30);
                add(submitTask);
                repaint();
            }
        });
        submitTask.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submitTask.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // request here
                taskForm.resetTaskForm();
                taskForm.getDescriptionArea().setText("  ADD NEW TASK");
            }
        });
        userForm.getCanselUser().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                remove(userForm);
                add(currentTasksScroll);
                repaint();
            }
        });
        userForm.getAddUser().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String username = userForm.getUserNameTextArea().getText();
                taskForm.getUserCombo().addItem(username);
                userForm.getUserNameTextArea().setText("");
                repaint();
            }
        });
    }
}
