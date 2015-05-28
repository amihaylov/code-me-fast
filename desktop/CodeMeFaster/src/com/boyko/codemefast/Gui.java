package com.boyko.codemefast;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import com.sun.glass.events.KeyEvent;

public class Gui extends JFrame {
    public Gui() {
        setSize(new Dimension(1000, 768));
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
        login.setBounds(620, 330, 70, 25);
        authenteficationPanel.add(login);
        login.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (authenteficationPanel.getUsernameText().getText().equals("Pesho")
                        && authenteficationPanel.getPasswordText().getText().equals("Gosho")) {
                    remove(authenteficationPanel);
                    getContentPane().add(new BottomPanel());
                    revalidate();
                } else {
                    authenteficationPanel.getUsernameText().setText("");
                    authenteficationPanel.getPasswordText().setText("");
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password");
                }
            }
        });

        setSize(1300, 700);
        setLocation(40, 20);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
