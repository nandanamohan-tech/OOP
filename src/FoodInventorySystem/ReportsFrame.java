package FoodInventorySystem;

import javax.swing.*;
import java.awt.*;

public class ReportsFrame extends JFrame {

    JLabel totalItemsLabel;
    JLabel lowStockLabel;
    JLabel expiringLabel;
    JButton viewReportButton;

    public ReportsFrame() {

        setTitle("Reports Frame");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(4, 1, 10, 10));

        totalItemsLabel = new JLabel(
                "Total Items: 0",
                SwingConstants.CENTER
        );
        add(totalItemsLabel);

        lowStockLabel = new JLabel(
                "Low-stock Items: 0",
                SwingConstants.CENTER
        );
        add(lowStockLabel);

        expiringLabel = new JLabel(
                "Expired/Expiring Items: 0",
                SwingConstants.CENTER
        );
        add(expiringLabel);

        viewReportButton = new JButton("View Report");
        add(viewReportButton);
    }
public static void main(String[ ]args)
{
new ReportsFrame().setVisible(true);
}
}

