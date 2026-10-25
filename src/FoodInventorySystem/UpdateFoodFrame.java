package FoodInventorySystem;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class UpdateFoodFrame extends JFrame {
    private final InventoryManager manager;
    private final DashboardFrame dashboard;
    private final JTextField idField = new JTextField(15);
    private final JTextField nameField = new JTextField(15);
    private final JComboBox<String> categoryBox;
    private final JTextField quantityField = new JTextField(15);
    private final JTextField unitField = new JTextField(15);
    private final JTextField priceField = new JTextField(15);
    private final JTextField expiryField = new JTextField(15);

    public UpdateFoodFrame(InventoryManager manager, DashboardFrame dashboard, FoodItem selectedItem) {
        this.manager = manager; this.dashboard = dashboard;
        setTitle("Update Food Item"); setSize(480, 470);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); setLocationRelativeTo(dashboard);
        setLayout(new BorderLayout(10, 10));
        categoryBox = UiSupport.categoryCombo(manager);

        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6, 6, 6, 6); c.fill = GridBagConstraints.HORIZONTAL;
        addRow(form, c, 0, "Food ID (cannot change):", idField);
        addRow(form, c, 1, "Food Name:", nameField);
        addRow(form, c, 2, "Category:", categoryBox);
        addRow(form, c, 3, "Quantity:", quantityField);
        addRow(form, c, 4, "Unit:", unitField);
        addRow(form, c, 5, "Price:", priceField);
        addRow(form, c, 6, "Expiry (yyyy-MM-dd):", expiryField);
        idField.setEditable(false);
        add(form, BorderLayout.CENTER);

        JButton updateButton = new JButton("Update"); JButton clearButton = new JButton("Clear");
        JPanel buttons = new JPanel(new FlowLayout()); buttons.add(updateButton); buttons.add(clearButton);
        add(buttons, BorderLayout.SOUTH);
        updateButton.addActionListener(e -> updateFood());
        clearButton.addActionListener(e -> clearFields());
        if (selectedItem != null) populate(selectedItem);
        UiSupport.applyTheme(this);
    }

    private void addRow(JPanel panel, GridBagConstraints c, int row, String label, Component field) {
        c.gridy = row; c.gridx = 0; c.weightx = 0; panel.add(new JLabel(label), c);
        c.gridx = 1; c.weightx = 1; panel.add(field, c);
    }

    private void populate(FoodItem item) {
        idField.setText(item.getFoodId()); nameField.setText(item.getFoodName());
        categoryBox.setSelectedItem(item.getCategory()); quantityField.setText(String.valueOf(item.getQuantity()));
        unitField.setText(item.getUnit()); priceField.setText(String.valueOf(item.getPrice()));
        expiryField.setText(item.getExpiryDate().toString());
    }

    private void updateFood() {
        try {
            double quantity = UiSupport.parseNonNegativeNumber(quantityField.getText(), "Quantity");
            double price = UiSupport.parseNonNegativeNumber(priceField.getText(), "Price");
            LocalDate expiry = UiSupport.parseDate(expiryField.getText());
            if (categoryBox.getSelectedItem() == null) throw new InventoryException("Choose a category.");
            manager.updateItem(idField.getText(), nameField.getText(), categoryBox.getSelectedItem().toString(),
                    quantity, unitField.getText(), price, expiry);
            dashboard.refreshSummary(); UiSupport.showSuccess(this, "Food item updated successfully.");
            dispose();
        } catch (InventoryException ex) { UiSupport.showError(this, ex); }
    }

    private void clearFields() {
        nameField.setText(""); quantityField.setText(""); unitField.setText("");
        priceField.setText(""); expiryField.setText("");
    }
}
