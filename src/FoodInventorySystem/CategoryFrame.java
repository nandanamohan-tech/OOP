package FoodInventorySystem;

import javax.swing.*;
import java.awt.*;

public class CategoryFrame extends JFrame {
    private final InventoryManager manager;
    private final DashboardFrame dashboard;
    private final JTextField categoryField = new JTextField(16);
    private final DefaultListModel<String> listModel = new DefaultListModel<>();
    private final JList<String> categoryList = new JList<>(listModel);

    public CategoryFrame(InventoryManager manager, DashboardFrame dashboard) {
        this.manager = manager; this.dashboard = dashboard;
        setTitle("Manage Categories"); setSize(450, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); setLocationRelativeTo(dashboard);
        setLayout(new BorderLayout(10, 10));

        JPanel top = new JPanel(new FlowLayout()); top.add(new JLabel("Category Name:")); top.add(categoryField);
        JButton addButton = new JButton("Add"); top.add(addButton); add(top, BorderLayout.NORTH);
        categoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(categoryList), BorderLayout.CENTER);
        JButton deleteButton = new JButton("Delete Selected Category");
        JPanel bottom = new JPanel(); bottom.add(deleteButton); add(bottom, BorderLayout.SOUTH);
        addButton.addActionListener(e -> addCategory()); deleteButton.addActionListener(e -> deleteCategory());
        refreshCategories();
        UiSupport.applyTheme(this);
        UiSupport.styleDangerButton(deleteButton);
    }

    private void refreshCategories() {
        listModel.clear();
        for (String category : manager.getCategories()) listModel.addElement(category);
    }

    private void addCategory() {
        try {
            manager.addCategory(categoryField.getText()); categoryField.setText(""); refreshCategories();
            UiSupport.showSuccess(this, "Category added and saved.");
        } catch (InventoryException ex) { UiSupport.showError(this, ex); }
    }

    private void deleteCategory() {
        String selected = categoryList.getSelectedValue();
        try {
            manager.deleteCategory(selected); refreshCategories();
            UiSupport.showSuccess(this, "Category deleted.");
        } catch (InventoryException ex) { UiSupport.showError(this, ex); }
    }
    
}
