package FoodInventorySystem;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class DataStorage {
    private final Path dataFile;

    public DataStorage() {
        this.dataFile = Paths.get("food_inventory.dat").toAbsolutePath();
    }

    public StoreData load() throws IOException, ClassNotFoundException {
        if (!Files.exists(dataFile)) {
            return new StoreData(new ArrayList<>(), new ArrayList<>(List.of(
                    "Grains", "Dairy", "Beverages", "Snacks", "Vegetables")));
        }
        try (ObjectInputStream input = new ObjectInputStream(
                new BufferedInputStream(Files.newInputStream(dataFile)))) {
            Object object = input.readObject();
            if (!(object instanceof StoreData)) {
                throw new IOException("The data file has an unexpected format.");
            }
            return (StoreData) object;
        }
    }

    public void save(List<FoodItem> items, List<String> categories) throws IOException {
        Path parent = dataFile.getParent();
        if (parent != null) Files.createDirectories(parent);
        Path temporary = dataFile.resolveSibling(dataFile.getFileName() + ".tmp");
        StoreData data = new StoreData(new ArrayList<>(items), new ArrayList<>(categories));
        try (ObjectOutputStream output = new ObjectOutputStream(
                new BufferedOutputStream(Files.newOutputStream(temporary)))) {
            output.writeObject(data);
            output.flush();
        }
        try {
            Files.move(temporary, dataFile, StandardCopyOption.REPLACE_EXISTING,
                    StandardCopyOption.ATOMIC_MOVE);
        } catch (AtomicMoveNotSupportedException ex) {
            Files.move(temporary, dataFile, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    public static class StoreData implements Serializable {
        private static final long serialVersionUID = 1L;
        private final List<FoodItem> items;
        private final List<String> categories;

        public StoreData(List<FoodItem> items, List<String> categories) {
            this.items = items;
            this.categories = categories;
        }
        public List<FoodItem> getItems() { return items; }
        public List<String> getCategories() { return categories; }
    }
}