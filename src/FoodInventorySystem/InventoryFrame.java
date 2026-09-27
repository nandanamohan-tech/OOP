package FoodInventorySystem;

import javax.swing.*;

public class InventoryFrame extends JFrame {

    JLabel searchLabel;
    JTextField searchField;
    JButton searchButton, editButton, deleteButton, refreshButton;
    JTable inventoryTable;
    JScrollPane scrollPane;

    public InventoryFrame() {

        setTitle("Inventory Frame");
        setSize(850, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        searchLabel = new JLabel("Search:");
        searchLabel.setBounds(50, 40, 70, 30);
        add(searchLabel);

        searchField = new JTextField();
        searchField.setBounds(110, 40, 250, 30);
        add(searchField);

        searchButton = new JButton("Search");
        searchButton.setBounds(370, 40, 100, 30);
        add(searchButton);

        String[] columns = {
                "Food ID",
                "Food Name",
                "Category",
                "Quantity",
                "Unit",
                "Price",
                "Expiry Date"
        };

        inventoryTable = new JTable(new Object[0][7], columns);

        scrollPane = new JScrollPane(inventoryTable);
        scrollPane.setBounds(50, 90, 740, 250);
        add(scrollPane);

        editButton = new JButton("Edit");
        editButton.setBounds(180, 370, 100, 30);
        add(editButton);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(300, 370, 100, 30);
        add(deleteButton);

        refreshButton = new JButton("Refresh");
        refreshButton.setBounds(420, 370, 100, 30);
        add(refreshButton);
    }
    public static void main(String[] args) {
        new InventoryFrame().setVisible(true);
    }
}

