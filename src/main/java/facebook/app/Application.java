package facebook.app;

import facebook.app.controller.UserCtr;
import facebook.app.ui.LoginUI;
import facebook.app.ui.RegisterUI;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        UserCtr userController = new UserCtr();
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("        Welcome to the Facebook App");
            System.out.println("Please chose one of the following options: ");
            System.out.println("      1. Register           2. Login");
            System.out.println("                  0. Exit");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    // Register
                    RegisterUI registerUI = new RegisterUI(userController);
                    registerUI.startRegistration();
                    break;
                case 2:
                    // Login
                    LoginUI loginUI = new LoginUI(userController);
                    loginUI.startLogin();
                    break;
                case 0:
                    System.out.println("Exiting Facebook App. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);

        // Close the scanner to avoid resource leak
        scanner.close();
    }
}
