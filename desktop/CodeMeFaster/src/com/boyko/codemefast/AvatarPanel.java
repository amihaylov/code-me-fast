package com.boyko.codemefast;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AvatarPanel extends JPanel {
    private ImageIcon avatar = new ImageIcon(getClass().getResource("Icons/avatar.jpg"));
    public JLabel levelLbl;
    public JLabel xp;

    public AvatarPanel(String level,String name,String xPoints) {
        setLayout(null);
        setBackground(Color.WHITE);
        JLabel avatarLbl = new JLabel();
        avatarLbl.setBounds(5, 5, 80, 85);
        avatarLbl.setIcon(avatar);
        add(avatarLbl);
        levelLbl = new JLabel("Level: " + level);
        levelLbl.setFont(new Font("Serif", Font.ITALIC, 15));
        levelLbl.setBounds(80, 15, 70, 30);
        add(levelLbl);
        JLabel avatarName = new JLabel(name);
        avatarName.setBounds(80, 33, 70, 30);
        avatarName.setFont(new Font("Serif", Font.ITALIC, 15));
        add(avatarName);
        xp = new JLabel("XP: "+xPoints);
        xp.setBounds(80, 50, 70, 30);
        xp.setFont(new Font("Serif", Font.ITALIC, 15));
        add(xp);
    }
}