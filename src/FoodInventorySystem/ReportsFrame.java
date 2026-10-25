package FoodInventorySystem;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class ReportsFrame extends JFrame {
    private final InventoryManager manager;
    private final JLabel totalItemsLabel = new JLabel();
    private final JLabel lowStockLabel = new JLabel();
    private final JLabel expiredLabel = new JLabel();
    private final JLabel expiringLabel = new JLabel();

    public ReportsFrame(InventoryManager manager, DashboardFrame dashboard) {
        this.manager = manager;
        setTitle("Inventory Reports"); setSize(650, 420);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); setLocationRelativeTo(dashboard);
        setLayout(new BorderLayout(10, 10));
        JLabel heading = new JLabel("Inventory Summary Report", SwingConstants.CENTER);
        heading.setFont(heading.getFont().deriveFont(Font.BOLD, 20f)); add(heading, BorderLayout.NORTH);

        JPanel summary = new JPanel(new GridLayout(4, 1, 8, 8));
        summary.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
        summary.add(totalItemsLabel); summary.add(lowStockLabel); summary.add(expiredLabel); summary.add(expiringLabel);
        add(summary, BorderLayout.CENTER);
        JButton viewReportButton = new JButton("View Detailed Report");
        JPanel bottom = new JPanel(); bottom.add(viewReportButton); add(bottom, BorderLayout.SOUTH);
        viewReportButton.addActionListener(e -> showDetailedReport());
        refreshReport();
        UiSupport.applyTheme(this);
    }

    private void refreshReport() {
        totalItemsLabel.setText("Total food items: " + manager.getTotalItems());
        lowStockLabel.setText("Low-stock items (quantity <= 5): " + manager.getLowStockCount());
        expiredLabel.setText("Expired items: " + manager.getExpiredCount());
        expiringLabel.setText("Expiring within 7 days: " + manager.getExpiringSoonCount());
    }

    private void showDetailedReport() {
        refreshReport();
        StringBuilder report = new StringBuilder("FOOD INVENTORY REPORT\nGenerated: ")
                .append(java.time.LocalDate.now()).append("\n\n");
        if (manager.getAllItems().isEmpty()) report.append("No food items have been added yet.\n");
        for (FoodItem item : manager.getAllItems()) {
            report.append("ID: ").append(item.getFoodId())
                    .append(" | Name: ").append(item.getFoodName())
                    .append(" | Category: ").append(item.getCategory())
                    .append(" | Quantity: ").append(item.getQuantity()).append(' ').append(item.getUnit())
                    .append(" | Price: ").append(String.format("%.2f", item.getPrice()))
                    .append(" | Expiry: ").append(item.getExpiryDate().format(DateTimeFormatter.ISO_LOCAL_DATE))
                    .append(" | Status: ").append(item.isExpired() ? "EXPIRED" : item.isExpiringSoon() ? "EXPIRING SOON" : item.isLowStock() ? "LOW STOCK" : "OK")
                    .append('\n');
        }
        JTextArea textArea = new JTextArea(report.toString(), 20, 70);
        textArea.setEditable(false); textArea.setCaretPosition(0);
        JOptionPane.showMessageDialog(this, new JScrollPane(textArea), "Detailed Report", JOptionPane.INFORMATION_MESSAGE);
    }
}
