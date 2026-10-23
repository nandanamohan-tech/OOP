package FoodInventorySystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class InventoryFrame extends JFrame {
    private final InventoryManager manager;
    private final DashboardFrame dashboard;
    private final JTextField searchField = new JTextField(20);
    private final String[] columns = {"Food ID", "Food Name", "Category", "Quantity", "Unit", "Price", "Expiry Date", "Status"};
    private final DefaultTableModelWrapper tableModel = new DefaultTableModelWrapper(columns);
    private final JTable inventoryTable = new JTable(tableModel);

    public InventoryFrame(InventoryManager manager, DashboardFrame dashboard) {
        this.manager = manager; this.dashboard = dashboard;
        setTitle("Inventory"); setSize(1000, 520);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); setLocationRelativeTo(dashboard);
        setLayout(new BorderLayout(8, 8));

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(new JLabel("Search ID, name or category:")); searchPanel.add(searchField);
        JButton searchButton = new JButton("Search"); JButton refreshButton = new JButton("Refresh");
        searchPanel.add(searchButton); searchPanel.add(refreshButton);
        add(searchPanel, BorderLayout.NORTH);
        inventoryTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        inventoryTable.setAutoCreateRowSorter(true);
        add(new JScrollPane(inventoryTable), BorderLayout.CENTER);

        JPanel actions = new JPanel(new FlowLayout());
        JButton editButton = new JButton("Edit"); JButton deleteButton = new JButton("Delete");
        actions.add(editButton); actions.add(deleteButton); add(actions, BorderLayout.SOUTH);
        searchButton.addActionListener(e -> refreshTable(manager.search(searchField.getText())));
        searchField.addActionListener(e -> refreshTable(manager.search(searchField.getText())));
        refreshButton.addActionListener(e -> { searchField.setText(""); refreshTable(manager.getAllItems()); });
        editButton.addActionListener(e -> editSelected());
        deleteButton.addActionListener(e -> deleteSelected());
        refreshTable(manager.getAllItems());
    }

    private void refreshTable(List<FoodItem> items) {
        tableModel.setRowCount(0);
        for (FoodItem item : items) {
            String status = item.isExpired() ? "EXPIRED" : item.isExpiringSoon() ? "Expiring soon" : item.isLowStock() ? "Low stock" : "OK";
            tableModel.addRow(new Object[]{item.getFoodId(), item.getFoodName(), item.getCategory(),
                    item.getQuantity(), item.getUnit(), String.format("%.2f", item.getPrice()),
                    item.getExpiryDate(), status});
        }
    }

    private FoodItem selectedItem() {
        int viewRow = inventoryTable.getSelectedRow();
        if (viewRow < 0) return null;
        int modelRow = inventoryTable.convertRowIndexToModel(viewRow);
        return manager.findById(String.valueOf(tableModel.getValueAt(modelRow, 0)));
    }

    private void editSelected() {
        FoodItem item = selectedItem();
        if (item == null) { JOptionPane.showMessageDialog(this, "Select a food item first."); return; }
        UpdateFoodFrame frame = new UpdateFoodFrame(manager, dashboard, item);
        frame.addWindowListener(new WindowAdapter() {
            @Override public void windowClosed(WindowEvent e) { refreshTable(manager.getAllItems()); dashboard.refreshSummary(); }
        });
        frame.setVisible(true);
    }

    private void deleteSelected() {
        FoodItem item = selectedItem();
        if (item == null) { JOptionPane.showMessageDialog(this, "Select a food item first."); return; }
        int answer = JOptionPane.showConfirmDialog(this,
                "Delete " + item.getFoodName() + "? This cannot be undone.", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.YES_OPTION) {
            try {
                manager.deleteItem(item.getFoodId()); refreshTable(manager.getAllItems()); dashboard.refreshSummary();
                UiSupport.showSuccess(this, "Food item deleted.");
            } catch (InventoryException ex) { UiSupport.showError(this, ex); }
        }
    }

    private static class DefaultTableModelWrapper extends javax.swing.table.DefaultTableModel {
        DefaultTableModelWrapper(String[] columns) { super(columns, 0); }
        @Override public boolean isCellEditable(int row, int column) { return false; }
    }
}
