 package FoodInventorySystem;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private final InventoryManager manager;
    private final JTextField usernameField = new JTextField(15);
    private final JPasswordField passwordField = new JPasswordField(15);

    public LoginFrame(InventoryManager manager) {
        this.manager = manager;
        setTitle("Food Inventory System - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 260);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Food Inventory Management System", SwingConstants.CENTER);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 17f));
        add(title, BorderLayout.NORTH);

        JPanel fields = new JPanel(new GridLayout(2, 2, 10, 10));
        fields.setBorder(BorderFactory.createEmptyBorder(15, 25, 5, 25));
        fields.add(new JLabel("Username:")); fields.add(usernameField);
        fields.add(new JLabel("Password:")); fields.add(passwordField);
        add(fields, BorderLayout.CENTER);

        JButton loginButton = new JButton("Login");
        JButton clearButton = new JButton("Clear");
        JButton exitButton = new JButton("Exit");
        JPanel buttons = new JPanel(new FlowLayout());
        buttons.add(loginButton); buttons.add(clearButton); buttons.add(exitButton);
        add(buttons, BorderLayout.SOUTH);

        loginButton.addActionListener(e -> login());
        passwordField.addActionListener(e -> login());
        clearButton.addActionListener(e -> { usernameField.setText(""); passwordField.setText(""); });
        exitButton.addActionListener(e -> dispose());
        UiSupport.applyTheme(this);
        UiSupport.styleDangerButton(clearButton);
    }

    private void login() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());
        // Demonstration credentials; change these before any real deployment.
        if (username.equals("admin") && password.equals("admin123")) {
            new DashboardFrame(manager).setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Failed", JOptionPane.WARNING_MESSAGE);
            passwordField.setText("");
        }
    }
}