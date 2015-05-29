package com.boyko.codemefast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class BottomPanel extends JPanel {
    CardLayout cl = new CardLayout();
    JPanel contentPanel = new JPanel();
    private ImageIcon addProjectIcon = new ImageIcon(getClass().getResource("Icons/AddIcon.png"));
    private JButton submitTask = Utility.createButton("add", KeyEvent.VK_ENTER);
    private JScrollPane allTaskScrollPane;
    private JScrollPane projectPanelScrollPane;
    private JScrollPane currentTasksScroll;
    private ImageIcon homeIcon = new ImageIcon(getClass().getResource("Icons/Home.png"));
    private JButton cancelProjectButton = Utility.createButton("cancel", KeyEvent.VK_ENTER);
    ProjectForm projectForm = new ProjectForm();
    AdminToolPanel toolPanel = new AdminToolPanel();
    UserForm userForm = new UserForm();
    TaskForm taskForm = new TaskForm();
    CurrentProjectTasks currentProjectTasks = new CurrentProjectTasks();
    TaskWindow taskWindow = new TaskWindow();

    private JScrollPane createAllTasksPanel() {
        JPanel allTasksPanel = new JPanel();
        allTasksPanel.setLayout(new BoxLayout(allTasksPanel, BoxLayout.Y_AXIS));
        try {
            String allTasksJsonArrayString = ServerConnectionUtils.getRequest("api/alltasks/" + UserData.getCurrentUser());
            if(!allTasksJsonArrayString.equals("no") && !allTasksJsonArrayString.equals("nouser")) {
                JSONArray tasksArray = new JSONArray(allTasksJsonArrayString);
                for (int i = 0; i < tasksArray.length(); i++) {
                    JSONObject singleTask = tasksArray.getJSONObject(i);
                    String taskId = singleTask.getString("id");
                    String taskName = singleTask.getString("name");
                    String taskDescription = singleTask.getString("description");
                    String taskType = singleTask.getString("type");
                    StartPageTask taskPanel = new StartPageTask(taskName, taskDescription, taskType);
                    taskPanel.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            JOptionPane.showMessageDialog(null, taskDescription, "Task description", JOptionPane.PLAIN_MESSAGE);
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
                    allTasksPanel.add(taskPanel);
                    allTasksPanel.add(Box.createVerticalStrut(20));
                }/*
            for (int i = 1; i < 100; i++) {
                JLabel lbl = new JLabel("CodemeFast" + "Task" + i + "");
                lbl.setFont(new Font("Serif", Font.ITALIC, 20));
                allTasksPanel.add(lbl);
                allTasksPanel.add(Box.createVerticalStrut(20));
            }*/
                allTasksPanel.setBackground(Color.decode("#fbfbfb"));
                allTaskScrollPane = new JScrollPane(allTasksPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                allTaskScrollPane.setBounds(300, 100, 500, 550);
                return allTaskScrollPane;
            }
            else{
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (JSONException e) {
            // JSON Parsing error
            e.printStackTrace();
        }
        return null;
    }

    private void loadAllProjects(JPanel staticPanel){
        //Get all projects
        try {
            String rawProjectsData = ServerConnectionUtils.getRequest("api/users/projects/" + UserData.getCurrentUser());
            JSONArray projectsArray = new JSONArray(rawProjectsData);
            for (int i = 0; i < projectsArray.length(); i++){
                JSONObject singleProject = projectsArray.getJSONObject(i);
                String projectName = singleProject.getString("name");
                String projectId = singleProject.getString("id");
                JLabel projLbl = new JLabel(projectName);
                projLbl.setFont(new Font("Segoe UI", Font.PLAIN, 18));
                projLbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
                projLbl.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        try {
                            String isAdmin = ServerConnectionUtils.getRequest("/api/projects/admin/"
                                    + UserData.getCurrentUser() + "/" + projectId);
                            System.out.println(isAdmin);
                            if(isAdmin.equals("yes")){
                                remove(toolPanel);
                                remove(currentTasksScroll);
                                currentProjectTasks.removeAll();
                                String rawData = ServerConnectionUtils.getRequest("/api/unfinishedtasksforproject/"
                                        + projectId + "/" + UserData.getCurrentUser());
                                if(!rawData.equals("no")) {
                                    JSONArray tasksArray = new JSONArray(rawData);
                                    currentProjectTasks.populate(tasksArray);
                                }
                                remove(projectForm);
                                remove(allTaskScrollPane);
                                remove(cancelProjectButton);
                                repaint();
                                revalidate();
                                toolPanel.setBounds(300, 100, 900, 50);
                                add(toolPanel);
                                add(currentTasksScroll);
                                revalidate();
                                repaint();
                            }
                            else {
                                remove(toolPanel);
                                remove(currentTasksScroll);
                                remove(cancelProjectButton);
                                currentProjectTasks.removeAll();
                                String rawData = ServerConnectionUtils.getRequest("/api/unfinishedtasksforproject/"
                                        + projectId + "/" + UserData.getCurrentUser());
                                if(!rawData.equals("no")) {
                                    JSONArray tasksArray = new JSONArray(rawData);
                                    currentProjectTasks.populateNoAdmin(tasksArray);
                                }
                                remove(projectForm);
                                remove(allTaskScrollPane);
                                repaint();
                                revalidate();
                                add(currentTasksScroll);
                                revalidate();
                                repaint();
                            }
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        } catch (JSONException e1) {
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
                staticPanel.add(Box.createVerticalStrut(10));
                staticPanel.add(projLbl);
                staticPanel.revalidate();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public BottomPanel() {
        setBackground(Color.WHITE);
        setLayout(null);
        add(createAllTasksPanel());

        AvatarPanel avatarPanel = new AvatarPanel("10","pep4eto1211","1431");
        avatarPanel.setBounds(820, 5, 170, 80);
        add(avatarPanel);

        JLabel lbl = new JLabel("Projects:");
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 25));
        lbl.setBounds(22, 50, 150, 30);
        add(lbl);

        JButton submitProject = Utility.createButton("Submit", KeyEvent.VK_ENTER);
        submitProject.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submitProject.setBounds(300, 590, 90, 30);

        JLabel homeLbl = new JLabel();
        homeLbl.setIcon(homeIcon);
        homeLbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
        homeLbl.setBounds(150, 40, 50, 50);
        homeLbl.setToolTipText("Home");
        add(homeLbl);
        homeLbl.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                remove(projectForm);
                remove(currentTasksScroll);
                remove(submitTask);
                remove(taskForm);
                remove(toolPanel);
                remove(submitTask);
                remove(userForm);
                remove(cancelProjectButton);
                remove(submitProject);
                add(allTaskScrollPane);
                revalidate();
                repaint();
            }
        });

        JLabel addProjectLbl = new JLabel();
        addProjectLbl.setIcon(addProjectIcon);
        addProjectLbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addProjectLbl.setBounds(210, 40, 50, 50);
        addProjectLbl.setToolTipText("Add new project");
        add(addProjectLbl);

        JPanel staticProjectPanel = new JPanel();
        staticProjectPanel.setBorder(new EmptyBorder(0, 5, 0, 0));
        staticProjectPanel.setLayout(new BoxLayout(staticProjectPanel, BoxLayout.Y_AXIS));
        staticProjectPanel.setBackground(Color.decode("#fbfbfb"));
        loadAllProjects(staticProjectPanel);
        projectPanelScrollPane = new JScrollPane(staticProjectPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        projectPanelScrollPane.setBounds(10, 100, 250, 550);
        projectPanelScrollPane.setBackground(Color.decode("#fbfbfb"));
        add(projectPanelScrollPane);

        //Cancel button

        cancelProjectButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cancelProjectButton.setBounds(400, 590, 90, 30);
        cancelProjectButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                remove(projectForm);
                remove(submitProject);
                remove(cancelProjectButton);
                add(allTaskScrollPane);
                revalidate();
                repaint();

            }
        });
        add(cancelProjectButton);
        //
        currentTasksScroll = new JScrollPane(currentProjectTasks, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        currentTasksScroll.setBounds(300, 180, 670, 460);
        currentTasksScroll.getViewport().setView(currentProjectTasks);
        submitProject.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String username = UserData.getCurrentUser();
                String projectName = projectForm.getNameArea().getText();
                String projectDescription = projectForm.getDescriptionArea().getText();
                String type = projectForm.getSelectedType();
                List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
                urlParameters.add(new BasicNameValuePair("username", username));
                urlParameters.add(new BasicNameValuePair("projectName", projectName));
                urlParameters.add(new BasicNameValuePair("projectDescription", projectDescription));
                urlParameters.add(new BasicNameValuePair("type", type));
                try {
                    ServerConnectionUtils.postRequest("api/projects/", urlParameters);
                    remove(projectForm);
                    remove(submitProject);
                    repaint();
                    revalidate();
                    toolPanel.setBounds(300, 100, 900, 50);
                    add(toolPanel);
                    add(currentTasksScroll);
                    staticProjectPanel.removeAll();
                    staticProjectPanel.revalidate();
                    staticProjectPanel.repaint();
                    loadAllProjects(staticProjectPanel);
                    if (!EventDoer.ach) {
                        JOptionPane.showMessageDialog(null, "You recieved 'The manager' achievement!");
                        avatarPanel.xp.setText("XP: 1520");
                    }
                   // remove(projectForm);
                   // remove(submitProject);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
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
                add(cancelProjectButton);
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
                String taskName = taskForm.getName();
                String description = taskForm.getDescriptionArea().getText();
                String type = taskForm.getComboType();
                List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
                urlParameters.add(new BasicNameValuePair("username", UserData.getCurrentUser()));
                urlParameters.add(new BasicNameValuePair("description", description));
                urlParameters.add(new BasicNameValuePair("taskName", taskName));
                urlParameters.add(new BasicNameValuePair("type", type));
                urlParameters.add(new BasicNameValuePair("projectId", "1"));
                urlParameters.add(new BasicNameValuePair("issidequest", "0"));
                urlParameters.add(new BasicNameValuePair("difficulty", "1"));
                urlParameters.add(new BasicNameValuePair("dldate", "11"));
                urlParameters.add(new BasicNameValuePair("dlmonth", "11"));
                urlParameters.add(new BasicNameValuePair("dlyear", "1111"));
                try {
                    ServerConnectionUtils.postRequest("/api/task", urlParameters);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
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
