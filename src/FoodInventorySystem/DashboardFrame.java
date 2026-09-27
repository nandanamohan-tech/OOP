package FoodInventorySystem;

import javax.swing.*;

public class DashboardFrame extends JFrame {

    JLabel totalFoodLabel, lowStockLabel, expiringLabel;
    JButton addFoodButton, inventoryButton, updateFoodButton;
    JButton categoryButton, stockButton, reportsButton;

    public DashboardFrame() {

        setTitle("Dashboard Frame");
        setSize(600, 400);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        totalFoodLabel = new JLabel("Total Food Items: 0");
        totalFoodLabel.setBounds(50, 50, 150, 30);
        add(totalFoodLabel);

        lowStockLabel = new JLabel("Low Stock: 0");
        lowStockLabel.setBounds(220, 50, 150, 30);
        add(lowStockLabel);

        expiringLabel = new JLabel("Expiring Soon: 0");
        expiringLabel.setBounds(390, 50, 150, 30);
        add(expiringLabel);

        addFoodButton = new JButton("Add Food");
        addFoodButton.setBounds(50, 120, 150, 40);
        add(addFoodButton);

        inventoryButton = new JButton("Inventory");
        inventoryButton.setBounds(220, 120, 150, 40);
        add(inventoryButton);

        updateFoodButton = new JButton("Update Food");
        updateFoodButton.setBounds(390, 120, 150, 40);
        add(updateFoodButton);

        categoryButton = new JButton("Category");
        categoryButton.setBounds(50, 190, 150, 40);
        add(categoryButton);

        stockButton = new JButton("Stock Management");
        stockButton.setBounds(220, 190, 150, 40);
        add(stockButton);

        reportsButton = new JButton("Reports");
        reportsButton.setBounds(390, 190, 150, 40);
        add(reportsButton);
    }
    public static void main(String[] args) {
        {
            DashboardFrame dashboardFrame = new DashboardFrame();
            dashboardFrame.setVisible(true);
        }
    }
}