package FoodInventorySystem;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public final class UiSupport {
    private UiSupport() { }

    public static double parseNonNegativeNumber(String text, String fieldName) throws InventoryException {
        try {
            double value = Double.parseDouble(text.trim());
            if (!Double.isFinite(value) || value < 0) throw new NumberFormatException();
            return value;
        } catch (NumberFormatException | NullPointerException ex) {
            throw new InventoryException(fieldName + " must be a valid number greater than or equal to zero.", ex);
        }
    }

    public static LocalDate parseDate(String text) throws InventoryException {
        return FoodItem.parseExpiryDate(text);
    }

    public static void showError(Component parent, Exception ex) {
        JOptionPane.showMessageDialog(parent, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void showSuccess(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public static JComboBox<String> categoryCombo(InventoryManager manager) {
        return new JComboBox<>(manager.getCategories().toArray(new String[0]));
    }
}