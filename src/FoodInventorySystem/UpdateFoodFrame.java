package FoodInventorySystem;

import javax.swing.*;
import java.awt.*;

public class UpdateFoodFrame extends JFrame {

    JLabel idLabel, nameLabel, categoryLabel, quantityLabel;
    JLabel unitLabel, priceLabel, expiryLabel;

    JTextField idField, nameField, quantityField;
    JTextField unitField, priceField, expiryField;

    JComboBox<String> categoryBox;

    JButton updateButton, clearButton;

    public UpdateFoodFrame() {

        setTitle("Update Food Item");
        setSize(450, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        idLabel = new JLabel("Food ID:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(idLabel, gbc);

        idField = new JTextField(15);
        gbc.gridx = 1;
        add(idField, gbc);

        nameLabel = new JLabel("Food Name:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(nameLabel, gbc);

        nameField = new JTextField(15);
        gbc.gridx = 1;
        add(nameField, gbc);

        categoryLabel = new JLabel("Category:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(categoryLabel, gbc);

        categoryBox = new JComboBox<>(
                new String[]{"Grains", "Dairy", "Beverages", "Snacks", "Vegetables"}
        );
        gbc.gridx = 1;
        add(categoryBox, gbc);

        quantityLabel = new JLabel("Quantity:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(quantityLabel, gbc);

        quantityField = new JTextField(15);
        gbc.gridx = 1;
        add(quantityField, gbc);

        unitLabel = new JLabel("Unit:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(unitLabel, gbc);

        unitField = new JTextField(15);
        gbc.gridx = 1;
        add(unitField, gbc);

        priceLabel = new JLabel("Price:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(priceLabel, gbc);

        priceField = new JTextField(15);
        gbc.gridx = 1;
        add(priceField, gbc);

        expiryLabel = new JLabel("Expiry Date:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(expiryLabel, gbc);

        expiryField = new JTextField(15);
        gbc.gridx = 1;
        add(expiryField, gbc);

        updateButton = new JButton("Update");
        gbc.gridx = 0;
        gbc.gridy = 7;
        add(updateButton, gbc);

        clearButton = new JButton("Clear");
        gbc.gridx = 1;
        add(clearButton, gbc);
    }
    Public static void main(String[ ]args)
{
new UpdateFoodFrame().setVisible(true);
}
}
