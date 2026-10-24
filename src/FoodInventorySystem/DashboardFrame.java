package FoodInventorySystem;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {
    private final InventoryManager manager;
    private final JLabel totalFoodLabel = new JLabel("Total Food Items: 0", SwingConstants.CENTER);
    private final JLabel lowStockLabel = new JLabel("Low Stock: 0", SwingConstants.CENTER);
    private final JLabel expiringLabel = new JLabel("Expiring Soon: 0", SwingConstants.CENTER);

    public DashboardFrame(InventoryManager manager) {
        this.manager = manager;
        setTitle("Food Inventory - Dashboard");
        setSize(650, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel heading = new JLabel("Dashboard", SwingConstants.CENTER);
        heading.setFont(heading.getFont().deriveFont(Font.BOLD, 22f));
        add(heading, BorderLayout.NORTH);

        JPanel summary = new JPanel(new GridLayout(1, 3, 10, 10));
        summary.add(totalFoodLabel); summary.add(lowStockLabel); summary.add(expiringLabel);
        add(summary, BorderLayout.CENTER);

        JPanel navigation = new JPanel(new GridLayout(2, 3, 10, 10));
        addNavigationButton(navigation, "Add Food", () -> new AddFoodFrame(manager, this).setVisible(true));
        addNavigationButton(navigation, "Inventory", () -> new InventoryFrame(manager, this).setVisible(true));
        addNavigationButton(navigation, "Update Food", () -> new UpdateFoodFrame(manager, this, null).setVisible(true));
        addNavigationButton(navigation, "Category", () -> new CategoryFrame(manager, this).setVisible(true));
        addNavigationButton(navigation, "Stock Management", () -> new StockManagementFrame(manager, this).setVisible(true));
        addNavigationButton(navigation, "Reports", () -> new ReportsFrame(manager, this).setVisible(true));
        add(navigation, BorderLayout.SOUTH);
        refreshSummary();
}
    

    private void addNavigationButton(JPanel panel, String text, Runnable action) {
        JButton button = new JButton(text);
        button.addActionListener(e -> action.run());
        panel.add(button);
    }
    public void refreshSummary() {
        totalFoodLabel.setText("Total Food Items: " + manager.getTotalItems());
        lowStockLabel.setText("Low Stock: " + manager.getLowStockCount());
        expiringLabel.setText("Expiring Soon: " + manager.getExpiringSoonCount());
    }
}