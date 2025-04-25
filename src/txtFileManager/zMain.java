package txtFileManager;

import Manager.*;
import java.util.Scanner;

public class zMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RoomRateManager roomManager = new RoomRateManager();
        CustomerInfoManager customerInfoManager = new CustomerInfoManager();

        System.out.println("👋 Welcome to Hotel Services");
        System.out.println("1. Reserve a Room");
        System.out.println("2. Register Your Information");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            roomManager.startRoomBooking();
        } else if (choice == 2) {
            customerInfoManager.collectCustomerInfo();
        } else {
            System.out.println("❌ Invalid choice");
        }

        scanner.close();
    }
}
