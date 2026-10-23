package FoodInventorySystem;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class AddFoodFrame extends JFrame {
    private final InventoryManager manager;
    private final DashboardFrame dashboard;
    private final JTextField idField = new JTextField(15);
    private final JTextField nameField = new JTextField(15);
    private final JComboBox<String> categoryBox;
    private final JTextField quantityField = new JTextField(15);
    private final JTextField unitField = new JTextField(15);
    private final JTextField priceField = new JTextField(15);
    private final JTextField expiryField = new JTextField(15);

    public AddFoodFrame(InventoryManager manager, DashboardFrame dashboard) {
        this.manager = manager; this.dashboard = dashboard;
        setTitle("Add Food Item"); setSize(480, 470);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); setLocationRelativeTo(dashboard);
        setLayout(new BorderLayout(10, 10));
        categoryBox = UiSupport.categoryCombo(manager);

        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6, 6, 6, 6); c.fill = GridBagConstraints.HORIZONTAL;
        addRow(form, c, 0, "Food ID:", idField);
        addRow(form, c, 1, "Food Name:", nameField);
        addRow(form, c, 2, "Category:", categoryBox);
        addRow(form, c, 3, "Quantity:", quantityField);
        addRow(form, c, 4, "Unit (kg, litre, etc.):", unitField);
        addRow(form, c, 5, "Price:", priceField);
        addRow(form, c, 6, "Expiry (yyyy-MM-dd):", expiryField);
        add(form, BorderLayout.CENTER);

        JButton addButton = new JButton("Add"); JButton clearButton = new JButton("Clear");
        JPanel buttons = new JPanel(new FlowLayout()); buttons.add(addButton); buttons.add(clearButton);
        add(buttons, BorderLayout.SOUTH);
        addButton.addActionListener(e -> addFood());
        clearButton.addActionListener(e -> clearFields());
    }

    private void addRow(JPanel panel, GridBagConstraints c, int row, String label, Component field) {
        c.gridy = row; c.gridx = 0; c.weightx = 0; panel.add(new JLabel(label), c);
        c.gridx = 1; c.weightx = 1; panel.add(field, c);
    }

    private void addFood() {
        try {
            if (categoryBox.getSelectedItem() == null) throw new InventoryException("Add a category before adding food.");
            double quantity = UiSupport.parseNonNegativeNumber(quantityField.getText(), "Quantity");
            double price = UiSupport.parseNonNegativeNumber(priceField.getText(), "Price");
            LocalDate expiry = UiSupport.parseDate(expiryField.getText());
            FoodItem item = new FoodItem(idField.getText(), nameField.getText(),
                    categoryBox.getSelectedItem().toString(), quantity, unitField.getText(), price, expiry);
            manager.addItem(item);
            dashboard.refreshSummary(); UiSupport.showSuccess(this, "Food item added and saved successfully.");
            clearFields();
        } catch (InventoryException ex) { UiSupport.showError(this, ex); }
    }

    private void clearFields() {
        idField.setText(""); nameField.setText(""); quantityField.setText("");
        unitField.setText(""); priceField.setText(""); expiryField.setText("");
        if (categoryBox.getItemCount() > 0) categoryBox.setSelectedIndex(0);
    }
}
