package com.boyko.codemefast;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
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

        BottomPanel panel = new BottomPanel();
        setBackground(Color.WHITE);
        setSize(1300, 700);
        getContentPane().add(panel);
        setLocation(40, 20);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
