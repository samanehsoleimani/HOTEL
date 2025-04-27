package manager;

// این خط مشخص می‌کنه که این فایل متعلق به پکیج manager هست.
// معمولاً پکیج manager برای مدیریت عملیات‌های مربوط به انبار و کالاها استفاده می‌شه.
package manager;
//کلاس Product به ما کمک می‌کنه که هر کالا رو با ویژگی‌های خاصش ذخیره کنیم و باهاش کار کنیم
import common.Product;
//این خط کتابخانه java.io رو وارد می‌کنه که برای کار با فایل‌ها و عملیات ورودی/خروجی استفاده می‌شه.
import java.io.*;
//وارد کردن لیست‌ها و اسکنر
        import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InventoryManager {
    // این خط یک ثابت (constant) تعریف می‌کنه به نام FILE_NAME.
    private static final String FILE_NAME = "inventory_data.dat";
    // این خط یک لیست از کالاها به نام products تعریف می‌کنه که از نوع List<Product> هست.
    private static List<Product> products = loadProducts();
    //این خط یه شیء از Scanner رو برای گرفتن ورودی از کاربر ایجاد می‌کنه.
    private static Scanner scanner = new Scanner(System.in);

    //یک لیست از کالاها (List<Product>) برمی‌گردونه
    // متد استاتیک (static) هست
    // بارگذاری خودکار داده‌ها هنگام شروع برنامه
    private static List<Product> loadProducts() {
        //دو شیء را می‌سازد
        //FileInputStream(FILE_NAME): این کلاسی برای باز کردن و خواندن داده‌ها از فایل به نام FILE_NAME است
        //ObjectInputStream(ois): این کلاس مسئول تبدیل داده‌های باینری که از فایل خوانده می‌شه به اشیاء جاوا است.
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            //لیست کالا هارو برمیگردونه
            return (List<Product>) ois.readObject();
            // اگر فایل به هر دلیلی پیدا نشه
        } catch (FileNotFoundException e) {
            System.out.println("فایل ذخیره‌سازی یافت نشد. یک لیست جدید ایجاد می‌شود.");
            return new ArrayList<>();
            // اگر در فرآیند بارگذاری داده‌ها خطای I/O (ورودی/خروجی) یا کلاس پیدا نشد
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("خطا در بارگذاری داده‌ها: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // ذخیره‌سازی داده‌ها نام متد saveProducts
    public static void saveProducts() {
        //FileOutputStream(FILE_NAME): این کلاس برای نوشتن داده‌ها در فایل استفاده می‌شه
        //ObjectOutputStream(oos): این کلاس مسئول تبدیل داده‌های جاوا به فرمت باینری (برای ذخیره‌سازی در فایل) هست
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            //در اینجا از متد writeObject() استفاده می‌کنیم تا لیست کالاها (products) رو که از نوع List<Product> هست به فایل بنویسیم
            oos.writeObject(products);
            //اگر هنگام نوشتن داده‌ها در فایل خطایی به وجود بیاد
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
        // این شرط بررسی می‌کنه که آیا لیست products خالی است یا نه
        if (products.isEmpty()) {
            System.out.println("⛔ هیچ کالایی ثبت نشده است.");
            return;
        }

        System.out.println("\n--- موجودی انبار بر اساس مدت ---");
        for (int i = 1; i <= 12; i++) {
            // این خط عنوانی چاپ می‌کنه که مشخص می‌کنه کالاهایی که مدت موجودی‌شون معادل با i ماه هست، در حال نمایش هستن
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
        // این شرط بررسی می‌کند که آیا لیست products خالی است یا نه
        if (products.isEmpty()) {
            System.out.println("⛔ هیچ کالایی ثبت نشده است.");
            return;
        }

        System.out.println("\n--- تمام کالاهای ثبت‌شده ---");
        // این حلقه for-each به ازای هر محصول در لیست products اجرا می‌شود
        for (Product p : products) {
            System.out.println(p.getProductInfo());
            System.out.println("-------------------");
        }
    }

    // جستجوی کالا بر اساس نام
    public static void searchProduct() {
        System.out.print("\nنام کالا برای جستجو: ");
        // این خط ورودی نام کالا را از کاربر دریافت کرده و آن را در متغیر name ذخیره می‌کند
        String name = scanner.nextLine();

        //متغیر found به عنوان یک نشانگر برای بررسی اینکه آیا کالا با نام وارد شده پیدا شده است یا خیر، استفاده می‌شود
        boolean found = false;
        // این حلقه for-each برای پیمایش در لیست products و بررسی هر محصول به ازای هر تکرار استفاده می‌شود
        for (Product p : products) {
            //ین شرط بررسی می‌کند که آیا نام کالا (p.getName()) با نام وارد شده توسط کاربر (name) برابر است یا نه
            if (p.getName().equalsIgnoreCase(name)) {
                System.out.println(p.getProductInfo());
                //زمانی که یک کالا با نام وارد شده پیدا شود، متغیر found به true تغییر می‌کند. این بدان معنی است که کالای مورد نظر پیدا شده است
                found = true;
            }
        }

        // پس از اتمام جستجو در لیست کالاها، اگر متغیر found همچنان false باشد
        if (!found) {
            System.out.println("⛔ کالایی با این نام یافت نشد.");
        }
    }

    // به‌روزرسانی تعداد کالا
    public static void updateProductQuantity() {
        System.out.print("\nنام کالا برای به‌روزرسانی: ");
        // ورودی رو به صورت رشته دریافت می‌کنه و توی متغیر name ذخیره می‌کنه
        String name = scanner.nextLine();

        // با یه حلقه for-each روی همه‌ی کالاها (products) می‌گرده
        for (Product p : products) {
            if (p.getName().equalsIgnoreCase(name)) {
                //🔹 از کاربر می‌خواد تعداد جدید رو وارد کنه.
                //🔹 ورودی رو به int تبدیل می‌کنه.
                //🔹 مقدار جدید رو با استفاده از Setter داخل شیء Product ذخیره می‌کنه.
                System.out.print("تعداد جدید: ");
                int newQuantity = Integer.parseInt(scanner.nextLine());
                p.setQuantity(newQuantity);
                //🔹 با فراخوانی saveProducts() داده‌ها رو داخل فایل ذخیره می‌کنه.
                //🔹 پیام موفقیت نشون می‌ده.
                //🔹 با return از متد خارج می‌شه (چون کالا پیدا شده و دیگه نیازی به ادامه دادن نیست).
                saveProducts();
                System.out.println("✅ تعداد کالا به‌روزرسانی شد.");
                return;
            }
        }

        System.out.println("⛔ کالایی با این نام یافت نشد.");
    }
}