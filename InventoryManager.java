package manager;

import common.Product;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InventoryManager {
    private static final String FILE_NAME = "inventory_data.dat";
    private static List<Product> products = loadProducts();
    private static Scanner scanner = new Scanner(System.in);

    // بارگذاری خودکار داده‌ها هنگام شروع برنامه
    private static List<Product> loadProducts() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Product>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("فایل ذخیره‌سازی یافت نشد. یک لیست جدید ایجاد می‌شود.");
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("خطا در بارگذاری داده‌ها: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // ذخیره‌سازی داده‌ها
    public static void saveProducts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(products);
        } catch (IOException e) {
            System.err.println("خطا در ذخیره‌سازی داده‌ها: " + e.getMessage());
        }
    }

    // ثبت کالای جدید
    public static void addProduct() {
        System.out.println("\n--- ثبت کالای جدید ---");
        System.out.print("نام کالا: ");
        String name = scanner.nextLine();

        System.out.print("ویژگی‌های کالا: ");
        String features = scanner.nextLine();

        System.out.print("مدت موجودی در انبار (ماه): ");
        int months = Integer.parseInt(scanner.nextLine());

        System.out.print("تعداد: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        products.add(new Product(name, features, months, quantity));
        saveProducts(); // ذخیره پس از تغییر
        System.out.println("✅ کالا با موفقیت ثبت شد!");
    }

    // نمایش کالاها بر اساس مدت موجودی
    public static void displayProductsByMonths() {
        if (products.isEmpty()) {
            System.out.println("⛔ هیچ کالایی ثبت نشده است.");
            return;
        }

        System.out.println("\n--- موجودی انبار بر اساس مدت ---");
        for (int i = 1; i <= 3; i++) {
            System.out.println("\nکالاهای با موجودی " + i + " ماه:");
            boolean found = false;
            for (Product p : products) {
                if (p.getMonthsInStock() == i) {
                    System.out.println(p.getProductInfo());
                    System.out.println("-------------------");
                    found = true;
                }
            }
            if (!found) {
                System.out.println("-- هیچ کالایی یافت نشد --");
            }
        }
    }

    // نمایش تمام کالاها
    public static void displayAllProducts() {
        if (products.isEmpty()) {
            System.out.println("⛔ هیچ کالایی ثبت نشده است.");
            return;
        }

        System.out.println("\n--- تمام کالاهای ثبت‌شده ---");
        for (Product p : products) {
            System.out.println(p.getProductInfo());
            System.out.println("-------------------");
        }
    }

    // جستجوی کالا بر اساس نام
    public static void searchProduct() {
        System.out.print("\nنام کالا برای جستجو: ");
        String name = scanner.nextLine();

        boolean found = false;
        for (Product p : products) {
            if (p.getName().equalsIgnoreCase(name)) {
                System.out.println(p.getProductInfo());
                found = true;
            }
        }

        if (!found) {
            System.out.println("⛔ کالایی با این نام یافت نشد.");
        }
    }

    // به‌روزرسانی تعداد کالا
    public static void updateProductQuantity() {
        System.out.print("\nنام کالا برای به‌روزرسانی: ");
        String name = scanner.nextLine();

        for (Product p : products) {
            if (p.getName().equalsIgnoreCase(name)) {
                System.out.print("تعداد جدید: ");
                int newQuantity = Integer.parseInt(scanner.nextLine());
                p.setQuantity(newQuantity);
                saveProducts();
                System.out.println("✅ تعداد کالا به‌روزرسانی شد.");
                return;
            }
        }

        System.out.println("⛔ کالایی با این نام یافت نشد.");
    }
}