package com.boyko.codemefast;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

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
        JButton login = new JButton("login");
        AuthenteficationPanel authenteficationPanel = new AuthenteficationPanel();
        authenteficationPanel.setPreferredSize(new Dimension(200, 200));
        getContentPane().add(authenteficationPanel);
        login.setBounds(620, 330, 70, 25);
        authenteficationPanel.add(login);
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
