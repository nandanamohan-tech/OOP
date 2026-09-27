package FoodInventorySystem;

import javax.swing.*;

public class AddFoodFrame extends JFrame {

    JLabel idLabel, nameLabel, categoryLabel, quantityLabel;
    JLabel unitLabel, priceLabel, expiryLabel;

    JTextField idField, nameField, quantityField;
    JTextField unitField, priceField, expiryField;

    JComboBox<String> categoryBox;

    JButton addButton, clearButton;

    public AddFoodFrame() {

        setTitle("Add Food Item");
        setSize(450, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        idLabel = new JLabel("Food ID:");
        idLabel.setBounds(50, 50, 100, 30);
        add(idLabel);

        idField = new JTextField();
        idField.setBounds(170, 50, 200, 30);
        add(idField);

        nameLabel = new JLabel("Food Name:");
        nameLabel.setBounds(50, 90, 100, 30);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(170, 90, 200, 30);
        add(nameField);

        categoryLabel = new JLabel("Category:");
        categoryLabel.setBounds(50, 130, 100, 30);
        add(categoryLabel);

        categoryBox = new JComboBox<>(
                new String[]{"Grains", "Dairy", "Beverages", "Snacks", "Vegetables"}
        );
        categoryBox.setBounds(170, 130, 200, 30);
        add(categoryBox);

        quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(50, 170, 100, 30);
        add(quantityLabel);

        quantityField = new JTextField();
        quantityField.setBounds(170, 170, 200, 30);
        add(quantityField);

        unitLabel = new JLabel("Unit:");
        unitLabel.setBounds(50, 210, 100, 30);
        add(unitLabel);

        unitField = new JTextField();
        unitField.setBounds(170, 210, 200, 30);
        add(unitField);

        priceLabel = new JLabel("Price:");
        priceLabel.setBounds(50, 250, 100, 30);
        add(priceLabel);

        priceField = new JTextField();
        priceField.setBounds(170, 250, 200, 30);
        add(priceField);

        expiryLabel = new JLabel("Expiry Date:");
        expiryLabel.setBounds(50, 290, 100, 30);
        add(expiryLabel);

        expiryField = new JTextField();
        expiryField.setBounds(170, 290, 200, 30);
        add(expiryField);

        addButton = new JButton("Add");
        addButton.setBounds(120, 350, 90, 30);
        add(addButton);

        clearButton = new JButton("Clear");
        clearButton.setBounds(230, 350, 90, 30);
        add(clearButton);
    }
public static void main(String[] args) {
    new AddFoodFrame().setVisible(true);
}
}