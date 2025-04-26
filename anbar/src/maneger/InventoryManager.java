package maneger;

import common.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class InventoryManager {
    static ArrayList<Product> products = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static String fileName = "inventory_data.dat";

    public static void load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            products = (ArrayList<Product>) ois.readObject();
        } catch (Exception e) {
            products = new ArrayList<>();
        }
    }

    public static void save() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(products);
        } catch (IOException e) {
            System.out.println("خطا در ذخیره سازی اطلاعات!");
        }
    }

    public static void addProduct() {
        System.out.print("نام کالا: ");
        String name = scanner.nextLine();
        System.out.print("ویژگی‌ها: ");
        String features = scanner.nextLine();
        System.out.print("مدت موجودی (ماه): ");
        int months = Integer.parseInt(scanner.nextLine());
        System.out.print("تعداد: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        products.add(new Product(name, features, months, quantity));
        save();
        System.out.println("✅ کالا اضافه شد.");
    }

    public static void showAllProducts() {
        if (products.isEmpty()) {
            System.out.println("⛔ هیچ کالایی ثبت نشده است.");
            return;
        }
        for (Product p : products) {
            p.showInfo();
        }
    }

    public static void searchProduct() {
        System.out.print("نام کالا برای جستجو: ");
        String name = scanner.nextLine();
        boolean found = false;
        for (Product p : products) {
            if (p.name.equalsIgnoreCase(name)) {
                p.showInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("⛔ کالایی با این نام پیدا نشد.");
        }
    }

    public static void updateQuantity() {
        System.out.print("نام کالا برای تغییر تعداد: ");
        String name = scanner.nextLine();
        for (Product p : products) {
            if (p.name.equalsIgnoreCase(name)) {
                System.out.print("تعداد جدید: ");
                int quantity = Integer.parseInt(scanner.nextLine());
                p.quantity = quantity;
                save();
                System.out.println("✅ تعداد کالا به روز شد.");
                return;
            }
        }
        System.out.println("⛔ کالایی با این نام پیدا نشد.");
    }

    // 🔥🔥🔥 این متد جدید برای کنترل کل برنامه اضافه شد
    public static void run() {
        load();

        while (true) {
            System.out.println("\n*** سیستم مدیریت انبار ***");
            System.out.println("1. افزودن کالا");
            System.out.println("2. نمایش همه کالاها");
            System.out.println("3. جستجوی کالا");
            System.out.println("4. به‌روزرسانی تعداد کالا");
            System.out.println("5. خروج");
            System.out.print("انتخاب شما: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("⚠ لطفاً عدد وارد کنید!");
                continue;
            }

            switch (choice) {
                case 1: addProduct(); break;
                case 2: showAllProducts(); break;
                case 3: searchProduct(); break;
                case 4: updateQuantity(); break;
                case 5:
                    System.out.println("خروج...");
                    save();
                    System.exit(0);
                    break;
                default:
                    System.out.println("⚠ انتخاب نامعتبر!");
            }
        }
    }
}
