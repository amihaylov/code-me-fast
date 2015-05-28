package com.boyko.codemefast;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserForm extends JPanel {
    private ImageIcon add = new ImageIcon(getClass().getResource("addButton.jpg"));
    private ImageIcon cancel = new ImageIcon(getClass().getResource("cancelButton.jpg"));
    private JLabel userNameLbl;
    private JLabel addUserLbl;
    private JLabel cancelUserLbl;
    private JTextField userNameTextField;

    public UserForm() {
        setLayout(null);
        setBackground(Color.WHITE);
        userNameLbl = new JLabel("UserName");
        userNameLbl.setFont(userNameLbl.getFont().deriveFont(20.0f));
        userNameLbl.setBounds(5, 3, 100, 40);
        add(userNameLbl);

        userNameTextField = new JTextField();
        userNameTextField.setBounds(110, 9, 150, 30);
        add(userNameTextField);

        addUserLbl = new JLabel();
        addUserLbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addUserLbl.setIcon(add);
        addUserLbl.setToolTipText("add user");
        addUserLbl.setBounds(260, 9, 40, 32);
        add(addUserLbl);

        cancelUserLbl = new JLabel();
        cancelUserLbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cancelUserLbl.setIcon(cancel);
        cancelUserLbl.setToolTipText("cancel user");
        cancelUserLbl.setBounds(300, 9, 40, 30);
        add(cancelUserLbl);

    }

    public JLabel getUserNameLbl() {
        return userNameLbl;
    }

    public JLabel getAddUser() {
        return addUserLbl;
    }

    public JLabel getCanselUser() {
        return cancelUserLbl;
    }

    public JTextField getUserNameTextArea() {
        return userNameTextField;
    }

}
