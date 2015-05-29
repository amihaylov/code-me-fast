package com.boyko.codemefast;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class AuthenteficationPanel extends JPanel {

    private JLabel userName;
    private JLabel password;
    private JTextField usernameText;
    private JPasswordField passwordText;

    public AuthenteficationPanel() {
        setLayout(null);
        setBackground(Color.WHITE);
        JLabel welcome = new JLabel("CODEmeFast.Login");
        welcome.setBounds(100, 130, 800, 80);
        welcome.setFont(new Font("Segoe UI", Font.PLAIN, 55));
        userName = new JLabel("UserName");
        userName.setBounds(150, 250, 100, 30);
        userName.setFont(userName.getFont().deriveFont(20.0f));
        password = new JLabel("Password");
        password.setBounds(150, 285, 100, 30);
        password.setFont(password.getFont().deriveFont(20.0f));
        usernameText = new JTextField();
        usernameText.setBounds(250, 250, 120, 25);
        passwordText = new JPasswordField();
        passwordText.setBounds(250, 290, 120, 25);

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

    public void setPasswordText(JPasswordField passwordText) {
        this.passwordText = passwordText;
    }
}
