//برنامه از کلاسی به نام InventoryManager که در manager قرار داره استفاده می‌کنه
import manager.InventoryManager;
//این خط یکی از کلاس‌های استاندارد جاوا رو وارد می‌کنه: کلاس Scanner.
//Scanner برای گرفتن ورودی از کاربراستفاده می‌شه.
//مثلاً اگه بخوای از کاربر یه عدد یا رشته بگیری، از Scanner استفاده می‌کنی.
import java.util.Scanner;
// تعریف کلاس اصلی برنامه به نام Main
public class Main {
    public static void main(String[] args) {
        // ذخیره‌سازی خودکار هنگام بستن برنامه
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\nدر حال ذخیره‌سازی داده‌ها...");
            //متد InventoryManager.saveProducts() برای ذخیره‌سازی داده‌های انبار فراخوانی می‌شه.
            InventoryManager.saveProducts();
        }));

        Scanner scanner = new Scanner(System.in);
        // یه حلقه بی‌نهایت شروع می‌شه که تا وقتی کاربر گزینه خروج نزنه (6)، ادامه پیدا می‌کنه
        while (true) {
            // این قسمت منوی برنامه رو در ترمینال نشون می‌ده
            System.out.println("\n*** سیستم مدیریت انبار ***");
            System.out.println("1. ثبت کالای جدید");
            System.out.println("2. نمایش موجودی بر اساس مدت");
            System.out.println("3. نمایش تمام کالاها");
            System.out.println("4. جستجوی کالا");
            System.out.println("5. به‌روزرسانی تعداد کالا");
            System.out.println("6. خروج");
            System.out.print("لطفاً گزینه مورد نظر را انتخاب کنید: ");

            //یک متغیر به اسم choice تعریف می‌شه که قراره عدد وارد شده توسط کاربر (گزینه‌ی منو) رو نگه‌داره.
            int choice;
            try {
                // ورودی کاربر به صورت رشته دریافت می‌شه و بعد به عدد تبدیل می‌شه با Integer.parseInt()
                choice = Integer.parseInt(scanner.nextLine());
            //اگه کاربر چیز غیرعددی وارد کنه، برنامه به‌جای اینکه کرش کنه، یه پیام خطا چاپ می‌کنه و برمی‌گرده به ابتدای حلقه
            } catch (NumberFormatException e) {
                System.out.println("⚠ لطفاً عدد وارد کنید!");
                continue;
            }

            // یه دستور switch برای بررسی گزینه‌ای که کاربر وارد کرده
            switch (choice) {
                //بررسی میکنه کدام گزینه برای چه کاری هست
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
                    //System.exit(0) باعث می‌شه برنامه بلافاصله بسته بشه (و همون جا اون shutdown hook فعال می‌شه)
                case 6:
                    System.out.println("خروج از سیستم...");
                    System.exit(0);
                    // اگر عدد واردشده غیر از ۱ تا ۶ باشه، پیام "گزینه نامعتبر" نمایش داده می‌شه
                default:
                    System.out.println("⚠ گزینه نامعتبر!");
            }
        }
    }
}