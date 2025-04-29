package maneger;

import comon.Employee;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeManagementSystem {
    private static final String DATA_FILE = "employees.dat";
    private static List<Employee> employees = loadEmployees();
    private static Scanner scanner = new Scanner(System.in);

    private static List<Employee> loadEmployees() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            return (List<Employee>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("خطا در بارگذاری اطلاعات کارمندان: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void saveEmployees() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(employees);
        } catch (IOException e) {
            System.out.println("خطا در ذخیره اطلاعات کارمندان: " + e.getMessage());
        }
    }

    public static void addEmployee() {
        System.out.println("\n--- ثبت کارمند جدید ---");

        System.out.print("نام: ");
        String firstName = scanner.nextLine();

        System.out.print("نام خانوادگی: ");
        String lastName = scanner.nextLine();

        System.out.print("کد ملی (10 رقم): ");
        String nationalId = scanner.nextLine();

        System.out.print("شماره همراه (11 رقم): ");
        String phoneNumber = scanner.nextLine();

        System.out.print("سابقه کار (سال): ");
        int workExperience = Integer.parseInt(scanner.nextLine());

        System.out.print("مهارت‌ها: ");
        String skills = scanner.nextLine();

        System.out.print("سمت (مدیر/رزرویشن/نگهبان/خدمتکار): ");
        String position = scanner.nextLine();

        employees.add(new Employee(firstName, lastName, nationalId,
                phoneNumber, workExperience, skills, position));
        saveEmployees();
        System.out.println("\nکارمند جدید با موفقیت ثبت شد!");
    }

    public static void displayAllEmployees() {
        if (employees.isEmpty()) {
            System.out.println("\nهیچ کارمندی ثبت نشده است.");
            return;
        }

        System.out.println("\n--- لیست کارمندان ---");
        for (int i = 0; i < employees.size(); i++) {
            System.out.println("کارمند شماره " + (i+1) + ":");
            System.out.println(employees.get(i).getEmployeeInfo());
            System.out.println("------------------");
        }
    }

    public static void searchByExperience() {
        if (employees.isEmpty()) {
            System.out.println("\nهیچ کارمندی ثبت نشده است.");
            return;
        }

        System.out.print("\nحداقل سابقه کار مورد نظر (سال): ");
        int minExperience = Integer.parseInt(scanner.nextLine());

        boolean found = false;
        for (Employee emp : employees) {
            if (emp.getWorkExperience() >= minExperience) {
                System.out.println(emp.getEmployeeInfo());
                System.out.println("------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("کارمندی با این سابقه کار یافت نشد.");
        }
    }

    public static void searchByNationalId() {
        if (employees.isEmpty()) {
            System.out.println("\nهیچ کارمندی ثبت نشده است.");
            return;
        }

        System.out.print("\nکد ملی کارمند: ");
        String nationalId = scanner.nextLine();

        for (Employee emp : employees) {
            if (emp.getNationalId().equals(nationalId)) {
                System.out.println(emp.getEmployeeInfo());
                return;
            }
        }

        System.out.println("کارمندی با این کد ملی یافت نشد.");
    }
}