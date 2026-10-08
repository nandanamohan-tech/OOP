package FoodInventorySystem;

import java.awt.GridBagConstraints;
import java.awt.*;

import javax.swing.*;
public class LoginFrame extends JFrame {

    JLabel usernameLabel, passwordLabel;
    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton, clearButton, exitButton;

    public LoginFrame() {

        setTitle("Login Frame");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        usernameLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(usernameLabel,gbc);

        usernameField = new JTextField(15);
        gbc.gridx = 1;
        add(usernameField,gbc);

        passwordLabel = new JLabel("Password:");
        gbc.gridx=0;
        gbc.gridy=1;
        add(passwordLabel,gbc);

        passwordField = new JPasswordField(15);
        gbc.gridx=1;
        add(passwordField,gbc);

        loginButton = new JButton("Login");
        gbc.gridx=0;
        gbc.gridy=2;
        add(loginButton,gbc);

        clearButton = new JButton("Clear");
        gbc.gridx=1;
        add(clearButton,gbc);

        exitButton = new JButton("Exit");
        gbc.gridx=2;
        add(exitButton,gbc);
    }

    public static void main(String[] args) {
        new LoginFrame().setVisible(true);
    }
}