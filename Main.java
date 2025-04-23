import manager.InventoryManager;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // ذخیره‌سازی خودکار هنگام بستن برنامه
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\nدر حال ذخیره‌سازی داده‌ها...");
            InventoryManager.saveProducts();
        }));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n*** سیستم مدیریت انبار ***");
            System.out.println("1. ثبت کالای جدید");
            System.out.println("2. نمایش موجودی بر اساس مدت");
            System.out.println("3. نمایش تمام کالاها");
            System.out.println("4. جستجوی کالا");
            System.out.println("5. به‌روزرسانی تعداد کالا");
            System.out.println("6. خروج");
            System.out.print("لطفاً گزینه مورد نظر را انتخاب کنید: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("⚠ لطفاً عدد وارد کنید!");
                continue;
            }

            switch (choice) {
                case 1:
                    InventoryManager.addProduct();
                    break;
                case 2:
                    InventoryManager.displayProductsByMonths();
                    break;
                case 3:
                    InventoryManager.displayAllProducts();
                    break;
                case 4:
                    InventoryManager.searchProduct();
                    break;
                case 5:
                    InventoryManager.updateProductQuantity();
                    break;
                case 6:
                    System.out.println("خروج از سیستم...");
                    System.exit(0);
                default:
                    System.out.println("⚠ گزینه نامعتبر!");
            }
        }
    }
}