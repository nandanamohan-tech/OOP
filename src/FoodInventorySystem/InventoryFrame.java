package FoodInventorySystem;

import javax.swing.*;
import java.awt.*;

public class InventoryFrame extends JFrame {

    JLabel searchLabel;
    JTextField searchField;
    JButton searchButton, editButton, deleteButton, refreshButton;
    JTable inventoryTable;
    JScrollPane scrollPane;

    public InventoryFrame() {

        setTitle("Inventory Frame");
        setSize(850, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10, 10));

        JPanel searchPanel = new JPanel(new FlowLayout());

        searchLabel = new JLabel("Search:");
        searchPanel.add(searchLabel);

        searchField = new JTextField(20);
        searchPanel.add(searchField);

        searchButton = new JButton("Search");
        searchPanel.add(searchButton);

        add(searchPanel, BorderLayout.NORTH);

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
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());

        editButton = new JButton("Edit");
        buttonPanel.add(editButton);

        deleteButton = new JButton("Delete");
        buttonPanel.add(deleteButton);

        refreshButton = new JButton("Refresh");
        buttonPanel.add(refreshButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }
}
    public static void main(String[] args) {
        new InventoryFrame().setVisible(true);
    }
}

