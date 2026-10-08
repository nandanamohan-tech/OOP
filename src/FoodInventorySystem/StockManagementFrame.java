package FoodInventorySystem;

import javax.swing.*;
import java.awt.*;

public class StockManagementFrame extends JFrame {

    JLabel foodLabel, currentQuantityLabel, stockAmountLabel;
    JComboBox<String> foodBox;
    JTextField currentQuantityField, stockAmountField;
    JButton addStockButton, removeStockButton, updateButton;

    public StockManagementFrame() {

        setTitle("Stock Management Frame");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        foodLabel = new JLabel("Select Food Item:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(foodLabel, gbc);

        foodBox = new JComboBox<>(
                new String[]{"Rice", "Milk", "Bread", "Juice"}
        );
        gbc.gridx = 1;
        add(foodBox, gbc);

        currentQuantityLabel = new JLabel("Current Quantity:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(currentQuantityLabel, gbc);

        currentQuantityField = new JTextField(15);
        gbc.gridx = 1;
        add(currentQuantityField, gbc);

        stockAmountLabel = new JLabel("Stock Amount:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(stockAmountLabel, gbc);

        stockAmountField = new JTextField(15);
        gbc.gridx = 1;
        add(stockAmountField, gbc);

        addStockButton = new JButton("Add Stock");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(addStockButton, gbc);

        removeStockButton = new JButton("Remove Stock");
        gbc.gridx = 1;
        add(removeStockButton, gbc);

        updateButton = new JButton("Update");
        gbc.gridx = 2;
        add(updateButton, gbc);
    }
}
    }
public static void main(String[ ]args)
{
new StockManagementFrame().setVisible(true);
}
}