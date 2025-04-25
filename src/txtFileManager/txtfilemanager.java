package txtFileManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class txtfilemanager {
    private String FileName;
    private int Row;

    public txtfilemanager(String FileName) {
        this.FileName = FileName;
        Row = 0;
    }

    // متد برای جستجوی سطر بر اساس شماره اتاق
    public String searchRow(int RoomNum) {
        String D[] = getArrayFromFile(); // دریافت تمام داده‌های فایل

        if (RoomNum < 0 || RoomNum >= D.length) // بررسی اعتبار شماره اتاق
            return "this room not found!";

        return D[RoomNum]; // بازگرداندن سطر مطابق با شماره اتاق
    }

    // متد برای به‌روزرسانی سطر
    public void updateRow(int RoomNum, String newData) {
        String[] D = getArrayFromFile(); // خواندن تمام داده‌های فایل

        if (RoomNum < 0 || RoomNum >= D.length) {
            System.out.println("This row not found!");
            return;
        }

        D[RoomNum] = newData + "\n"; // تغییر داده‌ی سطر و اضافه کردن خط جدید

        writeArrayToFile(D); // نوشتن تغییرات به فایل
    }

    // نوشتن آرایه به فایل
    public void writeArrayToFile(String[] data) {
        try {
            PrintWriter out = new PrintWriter(new FileWriter(this.FileName)); // استفاده از FileWriter برای نوشتن در فایل

            for (String datum : data) {
                out.println(datum); // نوشتن هر داده در یک خط جدید
            }

            out.close(); // بستن فایل
        } catch (Exception e) {
            e.printStackTrace(); // مدیریت خطا
        }
    }

    // حذف یک سطر از فایل
    public void deletRow(int row) {
        if (row < 0 || row >= Row) return;

        String[] s = getArrayFromFile();
        StringBuilder newData = new StringBuilder();

        for (int i = 0; i < s.length; i++) {
            if (i != row) {
                newData.append(s[i]).append("\n");
            }
        }

        setIntoFile(newData.toString()); // ذخیره‌ی داده‌های جدید
    }

    // اضافه کردن سطر جدید به فایل
    public void AppendRow(String newRow) {
        String s = getFromFile();
        if (s.equals(""))
            s = newRow;
        else
            s += "\n" + newRow;

        setIntoFile(s); // ذخیره‌سازی داده‌ها در فایل
    }

    // نوشتن داده‌ها به فایل
    public void setIntoFile(String s) {
        try {
            PrintWriter out = new PrintWriter(this.FileName);
            out.print(s); // نوشتن محتوا به فایل
            out.close();
            _UpdateRow(); // به‌روزرسانی تعداد سطرها
        } catch (Exception e) {
            e.printStackTrace(); // مدیریت خطا
        }
    }

    // دریافت تمام داده‌های فایل
    public String[] getArrayFromFile() {
        File file = new File(this.FileName);
        List<String> lines = new ArrayList<>();

        try {
            Scanner input = new Scanner(file);
            while (input.hasNextLine()) {
                lines.add(input.nextLine());
            }
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return lines.toArray(new String[0]); // تبدیل لیست به آرایه
    }

    // دریافت تمام داده‌ها از فایل به صورت یک رشته
    private String getFromFile() {
        File file = new File(this.FileName);
        StringBuilder s = new StringBuilder();

        try {
            Scanner input = new Scanner(file);
            while (input.hasNextLine()) {
                if (s.length() == 0)
                    s.append(input.nextLine());
                else
                    s.append("\n").append(input.nextLine());
            }
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return s.toString();
    }

    // ایجاد یک فایل خالی
    public void CreateFile() {
        setIntoFile(""); // ایجاد فایل خالی
        Row = 0; // تعداد سطرها برابر با صفر است
    }

    // پاک کردن محتوای فایل
    public void Clear() {
        CreateFile(); // ایجاد یک فایل جدید خالی
    }

    // شمارش تعداد سطرهای حاوی یک کلمه خاص
    public void countRowsContaining(String keyword) {
        String[] data = getArrayFromFile();
        int count = 0;

        for (String datum : data) {
            if (datum.contains(keyword)) {
                count++;
            }
        }

        System.out.println("Number of rows containing the keyword '" + keyword + "': " + count);
    }

    // به‌روزرسانی تعداد سطرهای فایل
    private void _UpdateRow() {
        int c = 0;

        try {
            Scanner input = new Scanner(new File(FileName));
            while (input.hasNextLine()) {
                input.nextLine();
                c++;
            }
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Row = c;
    }

    // بازگشت تعداد سطرها
    public int seleccount() {
        return Row;
    }
}
