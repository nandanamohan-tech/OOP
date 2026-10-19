package FoodInventorySystem;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class InventoryManager {
    private final List<FoodItem> items = new ArrayList<>();
    private final List<String> categories = new ArrayList<>();
    private final DataStorage storage;

    public InventoryManager() throws InventoryException {
        storage = new DataStorage();
        try {
            DataStorage.StoreData data = storage.load();
            items.addAll(data.getItems());
            categories.addAll(data.getCategories());
            if (categories.isEmpty()) categories.add("General");
        } catch (IOException | ClassNotFoundException | RuntimeException ex) {
            throw new InventoryException("Could not load food_inventory.dat. Check the file and restart. Details: " + ex.getMessage(), ex);
        }
    }

    private void save() throws InventoryException {
        try {
            storage.save(items, categories);
        } catch (IOException ex) {
            throw new InventoryException("Could not save data. Check folder permissions and available disk space. Details: " + ex.getMessage(), ex);
        }
    }

    public synchronized List<FoodItem> getAllItems() {
        List<FoodItem> copy = new ArrayList<>(items);
        copy.sort(Comparator.comparing(FoodItem::getFoodName, String.CASE_INSENSITIVE_ORDER));
        return copy;
    }

    public synchronized List<String> getCategories() {
        return new ArrayList<>(categories);
    }

    public synchronized FoodItem findById(String id) {
        if (id == null) return null;
        for (FoodItem item : items) if (item.getFoodId().equalsIgnoreCase(id.trim())) return item;
        return null;
    }

    public synchronized List<FoodItem> search(String query) {
        String q = query == null ? "" : query.trim().toLowerCase(Locale.ROOT);
        List<FoodItem> result = new ArrayList<>();
        for (FoodItem item : getAllItems()) {
            if (item.getFoodId().toLowerCase(Locale.ROOT).contains(q)
                    || item.getFoodName().toLowerCase(Locale.ROOT).contains(q)
                    || item.getCategory().toLowerCase(Locale.ROOT).contains(q)) result.add(item);
        }
        return result;
    }

    public synchronized void addItem(FoodItem item) throws InventoryException {
        validateItem(item);
        if (findById(item.getFoodId()) != null) throw new InventoryException("A food item with this ID already exists.");
        items.add(item);
        try { save(); } catch (InventoryException ex) { items.remove(item); throw ex; }
    }

    public synchronized void updateItem(String id, String name, String category, double quantity,
                                        String unit, double price, LocalDate expiry) throws InventoryException {
        FoodItem item = findById(id);
        if (item == null) throw new InventoryException("No food item found with ID " + id + ".");
        FoodItem replacement = new FoodItem(item.getFoodId(), name, category, quantity, unit, price, expiry);
        validateItem(replacement);
        String oldName = item.getFoodName(), oldCategory = item.getCategory(), oldUnit = item.getUnit();
        double oldQuantity = item.getQuantity(), oldPrice = item.getPrice();
        LocalDate oldExpiry = item.getExpiryDate();
        item.setFoodName(name); item.setCategory(category); item.setQuantity(quantity);
        item.setUnit(unit); item.setPrice(price); item.setExpiryDate(expiry);
        try { save(); } catch (InventoryException ex) {
            item.setFoodName(oldName); item.setCategory(oldCategory); item.setUnit(oldUnit);
            item.setQuantity(oldQuantity); item.setPrice(oldPrice); item.setExpiryDate(oldExpiry);
            throw ex;
        }
    }

    public synchronized void deleteItem(String id) throws InventoryException {
        FoodItem item = findById(id);
        if (item == null) throw new InventoryException("Select an existing food item to delete.");
        items.remove(item);
        try { save(); } catch (InventoryException ex) { items.add(item); throw ex; }
    }

    public synchronized void adjustStock(String id, double amount, boolean add) throws InventoryException {
        FoodItem item = findById(id);
        if (item == null) throw new InventoryException("Select a valid food item.");
        if (!Double.isFinite(amount) || amount <= 0) throw new InventoryException("Stock amount must be a positive number.");
        double old = item.getQuantity();
        if (!add && amount > old) throw new InventoryException("Cannot remove more stock than the current quantity.");
        item.setQuantity(add ? old + amount : old - amount);
        try { save(); } catch (InventoryException ex) { item.setQuantity(old); throw ex; }
    }

    public synchronized void addCategory(String category) throws InventoryException {
        if (category == null || category.trim().isEmpty()) throw new InventoryException("Category name cannot be empty.");
        String value = category.trim();
        for (String existing : categories) if (existing.equalsIgnoreCase(value)) throw new InventoryException("This category already exists.");
        categories.add(value);
        try { save(); } catch (InventoryException ex) { categories.remove(value); throw ex; }
    }

    public synchronized void deleteCategory(String category) throws InventoryException {
        if (category == null || !categories.contains(category)) throw new InventoryException("Select a category to delete.");
        for (FoodItem item : items) if (item.getCategory().equalsIgnoreCase(category))
            throw new InventoryException("This category is assigned to food items. Change their category before deleting it.");
        categories.remove(category);
        try { save(); } catch (InventoryException ex) { categories.add(category); throw ex; }
    }

    public synchronized int getTotalItems() { return items.size(); }
    public synchronized long getLowStockCount() { return items.stream().filter(FoodItem::isLowStock).count(); }
    public synchronized long getExpiredCount() { return items.stream().filter(FoodItem::isExpired).count(); }
    public synchronized long getExpiringSoonCount() { return items.stream().filter(FoodItem::isExpiringSoon).count(); }

    private void validateItem(FoodItem item) throws InventoryException {
        if (item == null) throw new InventoryException("Food details are missing.");
        if (item.getFoodId().isBlank() || item.getFoodName().isBlank()
                || item.getCategory().isBlank() || item.getUnit().isBlank())
            throw new InventoryException("Food ID, name, category, and unit are required.");
        if (!Double.isFinite(item.getQuantity()) || item.getQuantity() < 0)
            throw new InventoryException("Quantity must be zero or a positive number.");
        if (!Double.isFinite(item.getPrice()) || item.getPrice() < 0)
            throw new InventoryException("Price must be zero or a positive number.");
        if (item.getExpiryDate() == null) throw new InventoryException("Expiry date is required.");
        if (categories.stream().noneMatch(c -> c.equalsIgnoreCase(item.getCategory())))
            throw new InventoryException("Choose a category from the Category frame first.");
    }
}
