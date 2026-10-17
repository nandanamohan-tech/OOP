package FoodInventorySystem;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                InventoryManager manager = new InventoryManager();
                new LoginFrame(manager).setVisible(true);
            } catch (InventoryException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Startup Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
