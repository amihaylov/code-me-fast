package com.boyko.codemefast;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AuthenteficationPanel extends JPanel {

    private JLabel userName;
    private JLabel password;
    private JTextField usernameText;
    private JTextField passwordText;

    public AuthenteficationPanel() {
        setLayout(null);
        setBackground(Color.WHITE);
        JLabel welcome = new JLabel("Welcome");
        welcome.setBounds(500, 130, 300, 60);
        welcome.setFont(welcome.getFont().deriveFont(70.0f));
        userName = new JLabel("UserName");
        userName.setBounds(550, 250, 100, 30);
        userName.setFont(userName.getFont().deriveFont(20.0f));
        password = new JLabel("Password");
        password.setBounds(550, 285, 100, 30);
        password.setFont(password.getFont().deriveFont(20.0f));
        usernameText = new JTextField();
        usernameText.setBounds(650, 250, 120, 25);
        passwordText = new JTextField();
        passwordText.setBounds(650, 290, 120, 25);

        add(welcome);
        add(usernameText);
        add(passwordText);
        add(userName);
        add(password);

    }

    public JTextField getUsernameText() {
        return usernameText;
    }

    public void setUsernameText(JTextField usernameText) {
        this.usernameText = usernameText;
    }

    public JTextField getPasswordText() {
        return passwordText;
    }

    public void setPasswordText(JTextField passwordText) {
        this.passwordText = passwordText;
    }
}
