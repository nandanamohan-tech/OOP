package FoodInventorySystem;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StockManagementFrame extends JFrame {
    private final InventoryManager manager;
    private final DashboardFrame dashboard;
    private final JComboBox<FoodChoice> foodBox = new JComboBox<>();
    private final JTextField currentQuantityField = new JTextField(15);
    private final JTextField stockAmountField = new JTextField(15);

    public StockManagementFrame(InventoryManager manager, DashboardFrame dashboard) {
        this.manager = manager; this.dashboard = dashboard;
        setTitle("Stock Management"); setSize(520, 320);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); setLocationRelativeTo(dashboard);
        setLayout(new BorderLayout(10, 10));

        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createEmptyBorder(15, 20, 10, 20));
        GridBagConstraints c = new GridBagConstraints(); c.insets = new Insets(8, 8, 8, 8); c.fill = GridBagConstraints.HORIZONTAL;
        addRow(form, c, 0, "Select Food Item:", foodBox);
        addRow(form, c, 1, "Current Quantity:", currentQuantityField);
        addRow(form, c, 2, "Stock Amount:", stockAmountField);
        currentQuantityField.setEditable(false); add(form, BorderLayout.CENTER);

        JButton addStockButton = new JButton("Add Stock");
        JButton removeStockButton = new JButton("Remove Stock");
        JPanel buttons = new JPanel(new FlowLayout()); buttons.add(addStockButton); buttons.add(removeStockButton);
        add(buttons, BorderLayout.SOUTH);
        foodBox.addActionListener(e -> showCurrentQuantity());
        addStockButton.addActionListener(e -> adjustStock(true));
        removeStockButton.addActionListener(e -> adjustStock(false));
        refreshFoodChoices();
    }

    private void addRow(JPanel panel, GridBagConstraints c, int row, String label, Component field) {
        c.gridx = 0; c.gridy = row; c.weightx = 0; panel.add(new JLabel(label), c);
        c.gridx = 1; c.weightx = 1; panel.add(field, c);
    }

    private void refreshFoodChoices() {
        foodBox.removeAllItems();
        List<FoodItem> items = manager.getAllItems();
        for (FoodItem item : items) foodBox.addItem(new FoodChoice(item.getFoodId(), item.toString()));
        showCurrentQuantity();
    }

    private void showCurrentQuantity() {
        FoodChoice choice = (FoodChoice) foodBox.getSelectedItem();
        FoodItem item = choice == null ? null : manager.findById(choice.id);
        currentQuantityField.setText(item == null ? "" : item.getQuantity() + " " + item.getUnit());
    }

    private void adjustStock(boolean add) {
        FoodChoice choice = (FoodChoice) foodBox.getSelectedItem();
        if (choice == null) { JOptionPane.showMessageDialog(this, "Add a food item first."); return; }
        try {
            double amount = UiSupport.parseNonNegativeNumber(stockAmountField.getText(), "Stock amount");
            if (amount == 0) throw new InventoryException("Stock amount must be greater than zero.");
            manager.adjustStock(choice.id, amount, add);
            stockAmountField.setText(""); showCurrentQuantity(); dashboard.refreshSummary();
            UiSupport.showSuccess(this, add ? "Stock added." : "Stock removed.");
        } catch (InventoryException ex) { UiSupport.showError(this, ex); }
    }

    private static class FoodChoice {
        private final String id; private final String label;
        FoodChoice(String id, String label) { this.id = id; this.label = label; }
        @Override public String toString() { return label; }
    }
}