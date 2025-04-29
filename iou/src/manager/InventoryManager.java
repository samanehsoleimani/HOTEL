package manager;

import common.Product;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryManager {
    private static final String FILE_NAME = "inventory.dat";
    private List<Product> products = loadProducts();

    private List<Product> loadProducts() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Product>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void saveProducts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(products);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addProduct(String name, String features, int months, int quantity) {
        products.add(new Product(name, features, months, quantity));
        saveProducts();
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

    public void updateProductQuantity(String name, int newQuantity) {
        for (Product p : products) {
            if (p.getName().equalsIgnoreCase(name)) {
                p.setQuantity(newQuantity);
                saveProducts();
                break;
            }
        }
    }
}