package Manager;

import Common.Customer;
import txtFileManager.txtfilemanager;
import java.util.Scanner;

public class CustomerInfoManager {
    private Scanner scanner;
    private txtfilemanager fileManager;

    public CustomerInfoManager() {
        scanner = new Scanner(System.in);
        fileManager = new txtfilemanager("LIST.txt");
    }

    public void collectCustomerInfo() {
        System.out.println("📋 Welcome to Customer Information Registration");

        System.out.print("Please enter your name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Please enter your room number: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); // Clear buffer

        System.out.print("Please enter your email: ");
        String email = scanner.nextLine().trim();

        System.out.print("Please enter your mobile number: ");
        String mobile = scanner.nextLine().trim();

        Customer customer = new Customer(name, roomNumber, email, mobile);

        StringBuilder info = new StringBuilder();
        info.append("\n[Customer Info]\n");
        info.append(customer.toString()).append("\n");

        fileManager.AppendRow(info.toString());

        System.out.println("✅ Customer information registered successfully!");
    }
}
