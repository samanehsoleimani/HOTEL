import maneger.EmployeeManagementSystem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // ذخیره خودکار هنگام بستن برنامه
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\nدر حال ذخیره‌سازی داده‌های کارمندان...");
            EmployeeManagementSystem.saveEmployees();
        }));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n*** سیستم مدیریت کارمندان هتل ***");
            System.out.println("1. ثبت کارمند جدید");
            System.out.println("2. نمایش همه کارمندان");
            System.out.println("3. جستجو بر اساس سابقه کار");
            System.out.println("4. جستجو بر اساس کد ملی");
            System.out.println("5. خروج");
            System.out.print("لطفاً گزینه مورد نظر را انتخاب کنید: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        EmployeeManagementSystem.addEmployee();
                        break;
                    case 2:
                        EmployeeManagementSystem.displayAllEmployees();
                        break;
                    case 3:
                        EmployeeManagementSystem.searchByExperience();
                        break;
                    case 4:
                        EmployeeManagementSystem.searchByNationalId();
                        break;
                    case 5:
                        System.out.println("خروج از سیستم...");
                        System.exit(0);
                    default:
                        System.out.println("گزینه نامعتبر!");
                }
            } catch (NumberFormatException e) {
                System.out.println("لطفاً عدد وارد کنید!");
            }
        }
    }
}