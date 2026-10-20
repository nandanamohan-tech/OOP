package FoodInventorySystem;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class FoodItem implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String foodId;
    private String foodName;
    private String category;
    private double quantity;
    private String unit;
    private double price;
    private LocalDate expiryDate;

    public FoodItem(String foodId, String foodName, String category,
                    double quantity, String unit, double price, LocalDate expiryDate) {
        this.foodId = foodId.trim();
        this.foodName = foodName.trim();
        this.category = category.trim();
        this.quantity = quantity;
        this.unit = unit.trim();
        this.price = price;
        this.expiryDate = expiryDate;
    }

    public String getFoodId() { return foodId; }
    public String getFoodName() { return foodName; }
    public String getCategory() { return category; }
    public double getQuantity() { return quantity; }
    public String getUnit() { return unit; }
    public double getPrice() { return price; }
    public LocalDate getExpiryDate() { return expiryDate; }

    public void setFoodName(String foodName) { this.foodName = foodName.trim(); }
    public void setCategory(String category) { this.category = category.trim(); }
    public void setQuantity(double quantity) { this.quantity = quantity; }
    public void setUnit(String unit) { this.unit = unit.trim(); }
    public void setPrice(double price) { this.price = price; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }

    public boolean isLowStock() { return quantity <= 5; }
    public boolean isExpired() { return expiryDate.isBefore(LocalDate.now()); }
    public boolean isExpiringSoon() {
        LocalDate today = LocalDate.now();
        return !expiryDate.isBefore(today) && !expiryDate.isAfter(today.plusDays(7));
    }

    public static LocalDate parseExpiryDate(String text) throws InventoryException {
        try {
            return LocalDate.parse(text.trim()); // Required format: yyyy-MM-dd
        } catch (DateTimeParseException | NullPointerException ex) {
            throw new InventoryException("Enter expiry date in yyyy-MM-dd format, for example 2027-03-25.", ex);
        }
    }

    @Override
    public String toString() {
        return foodName + " (ID: " + foodId + ")";
    }
}
