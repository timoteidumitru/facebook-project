package facebook.app;

import facebook.app.controller.MessageController;
import facebook.app.controller.UserController;
import facebook.app.model.user.User;
import facebook.app.services.MessageService;
import facebook.app.dao.MessageDAO;
import facebook.app.ui.login.LoginUI;
import facebook.app.ui.message.MessageUI;
import facebook.app.ui.register.RegisterUI;

import java.util.List;
import java.util.Optional;
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
        long loggedInUserId = -1; // Initialize as an invalid user ID

        do {
            System.out.println("        Welcome to the Facebook App");

            if (loggedInUserId != -1) {
                List<User> users = userController.getAllUsers();
                long finalLoggedInUserId = loggedInUserId;
                Optional<User> loggedInUserOptional = users.stream()
                        .filter(user -> user.getUserId() == finalLoggedInUserId)
                        .findFirst();

                if (loggedInUserOptional.isPresent()) {
                    String loggedInUserEmail = loggedInUserOptional.get().getEmail();
                    String username = loggedInUserEmail.split("@")[0];
                    String capitalizedUsername = username.substring(0, 1).toUpperCase() + username.substring(1);
                    System.out.println("User " + capitalizedUsername + " is currently logged in.");
                } else {
                    System.out.println("Logged-in user not found.");
                }

                // Display the options for a logged-in user
                System.out.println("Please choose one of the following options: ");
                System.out.println("      1. Posts             2. Friends");
                System.out.println("      3. Messages          4. Groups");
            } else {
                // Display the options for a user not logged in
                System.out.println("Please choose one of the following options: ");
                System.out.println("      1. Register           2. Login");
            }

            System.out.println("                  0. Exit");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    if (loggedInUserId != -1) {
                        // Handle Posts for a logged-in user
                        System.out.println("Option 1: View Posts");
                    } else {
                        // Register
                        RegisterUI registerUI = new RegisterUI(userController);
                        registerUI.startRegistration();
                    }
                    break;
                case 2:
                    if (loggedInUserId != -1) {
                        // Handle Friends for a logged-in user
                        System.out.println("Option 2: View Friends");
                    } else {
                        // Login
                        LoginUI loginUI = new LoginUI(userController);
                        if (loginUI.startLogin()) {
                            loggedInUserId = userController.getUserByEmail(loginUI.getEmail()).getUserId();
                        }
                    }
                    break;
                case 3:
                    // Messages option, only available when logged in
                    if (loggedInUserId != -1) {
                        messageCtr.startMessaging();
                    } else {
                        System.out.println("You need to log in first to access messages.");
                    }
                    break;
                case 4:
                    if (loggedInUserId != -1) {
                        // Handle Groups for a logged-in user
                        System.out.println("Option 4: View Groups");
                    }
                    break;
                case 0:
                    // Logout all users before exiting
                    userController.logoutAllUsers();
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
