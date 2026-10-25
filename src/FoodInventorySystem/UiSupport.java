
package FoodInventorySystem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.time.LocalDate;

public final class UiSupport {

    // Food Inventory System colour palette
    public static final Color FOREST_GREEN = new Color(36, 92, 67);
    public static final Color DARK_GREEN = new Color(27, 66, 49);
    public static final Color SAGE = new Color(220, 232, 216);
    public static final Color IVORY = new Color(247, 245, 237);
    public static final Color WHITE = Color.WHITE;
    public static final Color TEXT = new Color(43, 53, 46);
    public static final Color MUTED = new Color(112, 122, 113);
    public static final Color BORDER = new Color(220, 224, 215);
    public static final Color AMBER = new Color(217, 154, 40);
    public static final Color RED = new Color(185, 74, 72);

    private static final Font BODY_FONT =
            new Font("SansSerif", Font.PLAIN, 14);

    private static final Font BUTTON_FONT =
            new Font("SansSerif", Font.BOLD, 13);

    private static final Font TITLE_FONT =
            new Font("SansSerif", Font.BOLD, 25);

    private UiSupport() {
    }

    // Apply the shared theme to a completed frame.
    public static void applyTheme(JFrame frame) {
        frame.getContentPane().setBackground(IVORY);
        styleComponent(frame.getContentPane());

        frame.getRootPane().setBorder(
                new EmptyBorder(10, 12, 10, 12)
        );

        frame.revalidate();
        frame.repaint();
    }

    private static void styleComponent(Component component) {

        if (component instanceof JPanel) {
            JPanel panel = (JPanel) component;
            panel.setBackground(IVORY);
        }

        if (component instanceof JLabel) {
            JLabel label = (JLabel) component;
            label.setFont(BODY_FONT);
            label.setForeground(TEXT);
        }

        if (component instanceof JButton) {
            styleButton((JButton) component);
        }

        if (component instanceof JTextField) {
            styleTextField((JTextField) component);
        }

        if (component instanceof JPasswordField) {
            styleTextField((JPasswordField) component);
        }

        if (component instanceof JComboBox<?>) {
            JComboBox<?> combo = (JComboBox<?>) component;
            combo.setFont(BODY_FONT);
            combo.setBackground(WHITE);
            combo.setForeground(TEXT);
            combo.setFocusable(true);
        }

        if (component instanceof JList<?>) {
            JList<?> list = (JList<?>) component;
            list.setFont(BODY_FONT);
            list.setBackground(WHITE);
            list.setForeground(TEXT);
            list.setSelectionBackground(SAGE);
            list.setSelectionForeground(DARK_GREEN);
        }

        if (component instanceof JTable) {
            styleTable((JTable) component);
        }

        if (component instanceof JTextArea) {
            JTextArea area = (JTextArea) component;
            area.setFont(BODY_FONT);
            area.setForeground(TEXT);
            area.setBackground(WHITE);
            area.setMargin(new Insets(10, 10, 10, 10));
        }

        if (component instanceof JScrollPane) {
            JScrollPane scroll = (JScrollPane) component;
            scroll.setBorder(
                    BorderFactory.createLineBorder(BORDER)
            );
            scroll.getViewport().setBackground(WHITE);
        }

        if (component instanceof Container) {
            for (Component child :
                    ((Container) component).getComponents()) {
                styleComponent(child);
            }
        }
    }

    public static void styleButton(JButton button) {
        button.setFont(BUTTON_FONT);
        button.setForeground(WHITE);
        button.setBackground(FOREST_GREEN);
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setCursor(
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        );
        button.setBorder(
                BorderFactory.createEmptyBorder(10, 16, 10, 16)
        );
    }

    public static void styleDangerButton(JButton button) {
        styleButton(button);
        button.setBackground(RED);
    }

    public static void styleSecondaryButton(JButton button) {
        styleButton(button);
        button.setForeground(DARK_GREEN);
        button.setBackground(SAGE);
    }

    public static void styleTextField(JTextField field) {
        field.setFont(BODY_FONT);
        field.setForeground(TEXT);
        field.setBackground(WHITE);
        field.setCaretColor(FOREST_GREEN);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                BorderFactory.createEmptyBorder(7, 9, 7, 9)
        ));
    }

    public static void styleTitle(JLabel label) {
        label.setFont(TITLE_FONT);
        label.setForeground(DARK_GREEN);
    }

    public static void styleTable(JTable table) {
        table.setFont(BODY_FONT);
        table.setForeground(TEXT);
        table.setBackground(WHITE);
        table.setSelectionBackground(SAGE);
        table.setSelectionForeground(DARK_GREEN);
        table.setRowHeight(32);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setFillsViewportHeight(true);

        JTableHeader header = table.getTableHeader();
        header.setFont(BUTTON_FONT);
        header.setForeground(WHITE);
        header.setBackground(FOREST_GREEN);
        header.setPreferredSize(new Dimension(0, 38));
        header.setReorderingAllowed(false);
    }

    public static JPanel createCard() {
        JPanel card = new JPanel();
        card.setBackground(WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                new EmptyBorder(16, 16, 16, 16)
        ));
        return card;
    }

    public static double parseNonNegativeNumber(
            String text, String fieldName
    ) throws InventoryException {
        try {
            double value = Double.parseDouble(text.trim());

            if (!Double.isFinite(value) || value < 0) {
                throw new NumberFormatException();
            }

            return value;

        } catch (NumberFormatException | NullPointerException ex) {
            throw new InventoryException(
                    fieldName
                            + " must be a valid number greater than or equal to zero.",
                    ex
            );
        }
    }

    public static LocalDate parseDate(String text)
            throws InventoryException {
        return FoodItem.parseExpiryDate(text);
    }

    public static void showError(Component parent, Exception ex) {
        JOptionPane.showMessageDialog(
                parent,
                ex.getMessage(),
                "Something went wrong",
                JOptionPane.ERROR_MESSAGE
        );
    }

    public static void showSuccess(Component parent, String message) {
        JOptionPane.showMessageDialog(
                parent,
                message,
                "Success",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    public static JComboBox<String> categoryCombo(
            InventoryManager manager
    ) {
        return new JComboBox<>(
                manager.getCategories().toArray(new String[0])
        );
    }
}
