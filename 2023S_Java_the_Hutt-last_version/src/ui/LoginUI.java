package ui;
import javax.swing.*;

import domain.UserManager;
import domain.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginUI {
    public UserManager userManager = new UserManager();
    private JButton loginButton = new JButton("Login");
    private JButton registerButton = new JButton("Register") ;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JFrame loginFrame;
    
    public void showLoginScreen() {
        loginFrame = new JFrame("Login");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(300, 200);

        JPanel loginPanel = new JPanel(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);
        loginPanel.add(registerButton);

        loginFrame.add(loginPanel);
        loginFrame.setVisible(true);
    }
    
    public void addActionListener(ActionListener listener) {
    	this.loginButton.addActionListener(listener);
    	this.registerButton.addActionListener(listener);
    }
    
    public JButton getRegisterButton() {
    	return registerButton;
    }
    
    public JButton getLoginButton() {
    	return loginButton;
    }
    
    public JTextField getUsernameField() {
    	return usernameField;
    }
    
    public JPasswordField getPasswordField() {
    	return passwordField;
    }
    
    public JFrame getLoginFrame() {
    	return loginFrame;
    }
}