package com.boyko.codemefast;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class Utility  extends JPanel{
    static JLabel createLable(ImageIcon image, String toolTipText, int x, int y, int width, int hight) {
        JLabel lable = new JLabel(image);
        lable.setToolTipText(toolTipText);
        lable.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lable.setBounds(x, y, width, hight);
        return lable;
    }

    static JTextField createTextField(int x, int y, int width, int hight) {
        JTextField textArea = new JTextField();
        textArea.setFont(new Font("Serif", Font.ITALIC, 20));
        textArea.setBounds(x, y, width, hight);
        return textArea;
    }

    public static JLabel createLable(String projectName, float fontSize, int x, int y, int width, int hight) {
        JLabel nameLable = new JLabel(projectName);
        nameLable.setFont(nameLable.getFont().deriveFont(fontSize));
        nameLable.setBounds(x, y, width, hight);
        return nameLable;
    }

    public static JButton createButton(String name, int virtualKey) {
        JButton btn = new JButton(name);
        InputMap im = btn.getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = btn.getActionMap();
        im.put(KeyStroke.getKeyStroke(virtualKey, 0), "");
        am.put("clickMe", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton btn = (JButton) e.getSource();
                btn.doClick();
            }
        });
        return btn;
    }
}
