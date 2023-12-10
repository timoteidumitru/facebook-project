package facebook.app;

import facebook.app.controller.MessageController;
import facebook.app.controller.UserController;
import facebook.app.services.MessageService;
import facebook.app.dao.MessageDAO;
import facebook.app.ui.login.LoginUI;
import facebook.app.ui.message.MessageUI;
import facebook.app.ui.register.RegisterUI;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        UserController userController = new UserController();
        MessageController messageCtr = new MessageController(new MessageService(), new MessageDAO());
        MessageUI messageUI = new MessageUI(messageCtr, new Scanner(System.in));

        // Set the MessageUI instance in MessageController
        messageCtr.setMessageUI(messageUI);

        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("        Welcome to the Facebook App");
            System.out.println("Please choose one of the following options: ");
            System.out.println("      1. Register           2. Login");
            System.out.println("      3. Messages           0. Exit");

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
                case 3:
                    // Message options
                    messageCtr.startMessaging();
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
