package com.boyko.codemefast;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import com.sun.glass.events.KeyEvent;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class Gui extends JFrame {
    public Gui() {
        setSize(new Dimension(1020, 700));
        setTitle("CodeMeFast");
        createFrame();
    }

    public void createFrame() {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }
        JButton login = Utility.createButton("login", KeyEvent.VK_ENTER);
        AuthenteficationPanel authenteficationPanel = new AuthenteficationPanel();
        authenteficationPanel.setPreferredSize(new Dimension(200, 200));
        getContentPane().add(authenteficationPanel);
        login.setBounds(300, 330, 70, 25);
        authenteficationPanel.add(login);
        login.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String username = authenteficationPanel.getUsernameText().getText();
                String password = authenteficationPanel.getPasswordText().getText();
                List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
                urlParameters.add(new BasicNameValuePair("username", username));
                urlParameters.add(new BasicNameValuePair("password", password));
                String serverResponse = null;
                try {
                    serverResponse = ServerConnectionUtils.postRequest("api/login/", urlParameters);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                if (serverResponse.equals("ok")) {
                    UserData.setCurrentUser(username);
                    remove(authenteficationPanel);
                    getContentPane().add(new BottomPanel());
                    revalidate();
                } else
                if(serverResponse.equals("no")){
                    authenteficationPanel.getPasswordText().setText("");
                    JOptionPane.showMessageDialog(null, "Invalid password");
                }
                else
                if(serverResponse.equals("nouser")){
                    authenteficationPanel.getUsernameText().setText("");
                    authenteficationPanel.getPasswordText().setText("");
                    JOptionPane.showMessageDialog(null, "No such user.");
                }
            }
        });

        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
