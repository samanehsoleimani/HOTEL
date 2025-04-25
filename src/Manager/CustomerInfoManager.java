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
        System.out.println("\n==============================");
        System.out.println("  Welcome to Customer Registration");
        System.out.println("==============================\n");

        System.out.print("→ Enter your name: ");
        String name = scanner.nextLine().trim();

        System.out.print("→ Enter your room number: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("→ Enter your email: ");
        String email = scanner.nextLine().trim();

        System.out.print("→ Enter your mobile number: ");
        String mobile = scanner.nextLine().trim();

        Customer customer = new Customer(name, roomNumber, email, mobile);

        StringBuilder info = new StringBuilder();
        info.append("\n=== Customer Info ===\n");
        info.append(customer.toString()).append("\n");

        fileManager.AppendRow(info.toString());

        System.out.println("\n✅ Customer information registered successfully!\n");
    }
}
