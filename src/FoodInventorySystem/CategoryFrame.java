package FoodInventorySystem;

import javax.swing.*;

public class CategoryFrame extends JFrame {

    JLabel categoryLabel;
    JTextField categoryField;
    JButton addButton, deleteButton;
    JList<String> categoryList;
    JScrollPane scrollPane;

    public CategoryFrame() {

        setTitle("Category Frame");
        setSize(450, 400);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        categoryLabel = new JLabel("Category Name:");
        categoryLabel.setBounds(50, 50, 120, 30);
        add(categoryLabel);

        categoryField = new JTextField();
        categoryField.setBounds(170, 50, 180, 30);
        add(categoryField);

        addButton = new JButton("Add");
        addButton.setBounds(100, 100, 90, 30);
        add(addButton);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(210, 100, 90, 30);
        add(deleteButton);

        categoryList = new JList<>(
                new String[]{"Grains", "Dairy", "Beverages", "Snacks", "Vegetables"}
        );

        scrollPane = new JScrollPane(categoryList);
        scrollPane.setBounds(100, 160, 200, 120);
        add(scrollPane);
    }
    Public static void main(String[ ]args)
{
new CategoryFrame.setVisible(true);
}
}
