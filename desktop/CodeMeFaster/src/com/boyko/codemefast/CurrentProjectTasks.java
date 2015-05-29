package com.boyko.codemefast;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.awt.*;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class CurrentProjectTasks extends JPanel {
    public CurrentProjectTasks() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBackground(Color.decode("#fbfbfb"));
        setLayout(new GridLayout(0, 1));
    }

    public void populateNoAdmin (JSONArray jsonData) throws JSONException {
        this.removeAll();
        for (int i = 0; i < jsonData.length(); i++){
            JSONObject singleObject = jsonData.getJSONObject(i);
            NonAdminTask task = new NonAdminTask(singleObject.getString("name"), singleObject.getString("description"), singleObject.getString("id"));
            add(task);
            add(Box.createVerticalStrut(1));
        }
    }

    public void populate (JSONArray jsonData) throws JSONException {
        this.removeAll();
        for (int i = 0; i < jsonData.length(); i++){
            JSONObject singleObject = jsonData.getJSONObject(i);
            Admintask task = new Admintask(singleObject.getString("name"), singleObject.getString("description"), singleObject.getString("id"));
            add(task);
            add(Box.createVerticalStrut(1));
        }
    }
}
