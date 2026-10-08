package FoodInventorySystem;

import javax.swing.*;
import java.awt.*;

public class CategoryFrame extends JFrame {

    JLabel categoryLabel;
    JTextField categoryField;
    JButton addButton, deleteButton;
    JList<String> categoryList;
    JScrollPane scrollPane;

    public CategoryFrame() {

        setTitle("Category Frame");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new FlowLayout());

        categoryLabel = new JLabel("Category Name:");
        topPanel.add(categoryLabel);

        categoryField = new JTextField(15);
        topPanel.add(categoryField);

        addButton = new JButton("Add");
        topPanel.add(addButton);

        deleteButton = new JButton("Delete");
        topPanel.add(deleteButton);

        add(topPanel, BorderLayout.NORTH);

        categoryList = new JList<>(
                new String[]{"Grains", "Dairy", "Beverages", "Snacks", "Vegetables"}
        );

        scrollPane = new JScrollPane(categoryList);
        add(scrollPane, BorderLayout.CENTER);
    }
    public static void main(String[ ]args)
{
new CategoryFrame().setVisible(true);
}
}
