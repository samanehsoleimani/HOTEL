package common;

import java.io.Serializable;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    public String name;
    String features;
    int monthsInStock;
    public int quantity;

    public Product(String name, String features, int monthsInStock, int quantity) {
        this.name = name;
        this.features = features;
        this.monthsInStock = monthsInStock;
        this.quantity = quantity;
    }

    public void showInfo() {
        System.out.println("نام کالا: " + name);
        System.out.println("ویژگی‌ها: " + features);
        System.out.println("مدت موجودی (ماه): " + monthsInStock);
        System.out.println("تعداد: " + quantity);
        System.out.println("------------------------");
    }
}
