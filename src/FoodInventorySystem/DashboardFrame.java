package FoodInventorySystem;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {

    JLabel totalFoodLabel, lowStockLabel, expiringLabel;
    JButton addFoodButton, inventoryButton, updateFoodButton;
    JButton categoryButton, stockButton, reportsButton;

    public DashboardFrame() {

        setTitle("Dashboard Frame");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(3, 3, 10, 10));

        totalFoodLabel = new JLabel("Total Food Items: 0", SwingConstants.CENTER);
        add(totalFoodLabel);

        lowStockLabel = new JLabel("Low Stock: 0", SwingConstants.CENTER);
        add(lowStockLabel);

        expiringLabel = new JLabel("Expiring Soon: 0", SwingConstants.CENTER);
        add(expiringLabel);

        addFoodButton = new JButton("Add Food");
        add(addFoodButton);

        inventoryButton = new JButton("Inventory");
        add(inventoryButton);

        updateFoodButton = new JButton("Update Food");
        add(updateFoodButton);

        categoryButton = new JButton("Category");
        add(categoryButton);

        stockButton = new JButton("Stock Management");
        add(stockButton);

        reportsButton = new JButton("Reports");
        add(reportsButton);
    }

    public static void main(String[] args) {
        {
            DashboardFrame dashboardFrame = new DashboardFrame();
            dashboardFrame.setVisible(true);
        }
    }
}