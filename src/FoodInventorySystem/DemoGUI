package FoodInventorySystem;

import javax.swing.*;

public class StockManagementFrame extends JFrame {

    JLabel foodLabel, currentQuantityLabel, stockAmountLabel;
    JComboBox<String> foodBox;
    JTextField currentQuantityField, stockAmountField;
    JButton addStockButton, removeStockButton, updateButton;

    public StockManagementFrame() {

        setTitle("Stock Management Frame");
        setSize(500, 400);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        foodLabel = new JLabel("Select Food Item:");
        foodLabel.setBounds(50, 70, 120, 30);
        add(foodLabel);

        foodBox = new JComboBox<>(
                new String[]{"Rice", "Milk", "Bread", "Juice"}
        );
        foodBox.setBounds(180, 70, 200, 30);
        add(foodBox);

        currentQuantityLabel = new JLabel("Current Quantity:");
        currentQuantityLabel.setBounds(50, 120, 120, 30);
        add(currentQuantityLabel);

        currentQuantityField = new JTextField();
        currentQuantityField.setBounds(180, 120, 200, 30);
        add(currentQuantityField);

        stockAmountLabel = new JLabel("Stock Amount:");
        stockAmountLabel.setBounds(50, 170, 120, 30);
        add(stockAmountLabel);

        stockAmountField = new JTextField();
        stockAmountField.setBounds(180, 170, 200, 30);
        add(stockAmountField);

        addStockButton = new JButton("Add Stock");
        addStockButton.setBounds(50, 230, 120, 30);
        add(addStockButton);

        removeStockButton = new JButton("Remove Stock");
        removeStockButton.setBounds(180, 230, 130, 30);
        add(removeStockButton);

        updateButton = new JButton("Update");
        updateButton.setBounds(320, 230, 100, 30);
        add(updateButton);
    }
public static void main(String[ ]args)
{
new StockManagementFrame.setVisible(true);
}
}