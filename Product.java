package common;

import java.io.Serializable;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String features;
    private int monthsInStock;
    private int quantity; // مقدار جدید برای تعداد کالا

    public Product(String name, String features, int monthsInStock, int quantity) {
        this.name = name;
        this.features = features;
        this.monthsInStock = monthsInStock;
        this.quantity = quantity;
    }

    public String getProductInfo() {
        return "کالا: " + name +
                "\nویژگی‌ها: " + features +
                "\nمدت موجودی: " + monthsInStock + " ماه" +
                "\nتعداد: " + quantity;
    }

    // Getter و Setter ها
    public String getName() { return name; }
    public String getFeatures() { return features; }
    public int getMonthsInStock() { return monthsInStock; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}