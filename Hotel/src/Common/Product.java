//این خط مشخص می‌کنه که فایلی که الان داخلش هستیم، متعلق به پکیجی (package) به نام common هست
package common;
//این خط می‌گه که برنامه قراره از رابط (interface) به نام Serializable استفاده کنه که در پکیج استاندارد java.io قرار داره
import java.io.Serializable;
// این کلاس Product طرفیم که خیلی مهمه چون نماینده‌ی هر کالای موجود در انبار هست
// implements Serializable به جاوا می‌گه که اشیاء این کلاس می‌تونن سریال‌سازی بشن (یعنی ذخیره یا ارسال بشن).
public class Product implements Serializable {
    // این خط یه مقدار خاص به اسم serialVersionUID تعریف می‌کنه
    private static final long serialVersionUID = 1L;
 //گزینه های مربوطه
    private String name;
    private String features;
    private int monthsInStock;
    private int quantity; // مقدار جدید برای تعداد کالا

    // سازنده (constructor) کلاس Product هست.
    //🔹 وقتی بخوای یه کالا بسازی، باید این ۴ مقدار رو بهش بدی.
    public Product(String name, String features, int monthsInStock, int quantity) {
        this.name = name;
        this.features = features;
        this.monthsInStock = monthsInStock;
        this.quantity = quantity;
    }

    // این یه متد کمکی برای نمایش خلاصه‌ای از اطلاعات یک کالا هست.
    // خروجی این متد یه رشته (string) مرتب و قابل خواندنه که می‌شه مستقیماً چاپش کرد
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