package FoodInventorySystem;

import javax.swing.*;
public class LoginFrame extends JFrame {

    JLabel usernameLabel, passwordLabel;
    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton, clearButton, exitButton;

    public LoginFrame() {

        setTitle("Login Frame");
        setSize(400, 300);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 60, 100, 30);
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(150, 60, 180, 30);
        add(usernameField);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 110, 100, 30);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 110, 180, 30);
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(50, 170, 90, 30);
        add(loginButton);

        clearButton = new JButton("Clear");
        clearButton.setBounds(155, 170, 90, 30);
        add(clearButton);

        exitButton = new JButton("Exit");
        exitButton.setBounds(260, 170, 90, 30);
        add(exitButton);
    }

    public static void main(String[] args) {
        new LoginFrame().setVisible(true);
    }
}