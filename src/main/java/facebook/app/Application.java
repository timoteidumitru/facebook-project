package facebook.app;

import facebook.app.controller.FriendsController;
import facebook.app.controller.MessageController;
import facebook.app.controller.UserController;
import facebook.app.exceptions.InvalidEmailFormatException;
import facebook.app.exceptions.MessageValidationException;
import facebook.app.exceptions.UserIOException;
import facebook.app.exceptions.UserNotFoundException;
import facebook.app.services.FriendsService;
import facebook.app.services.MessageService;
import facebook.app.dao.MessageDAO;
import facebook.app.ui.*;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws MessageValidationException, UserNotFoundException, InvalidEmailFormatException, UserIOException {
        UserController userController = new UserController();
        MessageDAO messageDAO = new MessageDAO();
        MessageController messageCtr = new MessageController(new MessageService(), new MessageDAO());
        MessageUI messageUI = new MessageUI(messageCtr, new Scanner(System.in));
        messageCtr.setMessageUI(messageUI);
        FriendsController friendsController = new FriendsController(new FriendsService());
        FriendsUI friendsUI = new FriendsUI(friendsController, new Scanner(System.in));
        PostsUI homeFeedUI = new PostsUI();
        ProfileUI profileUI = new ProfileUI();
        Scanner scanner = new Scanner(System.in);

        int choice;
        long loggedInUserId = -1; // Initialize as an invalid user ID

        do {
            System.out.println("        Welcome to the Facebook App");

            if (loggedInUserId != -1) {
                // Display options for a logged-in user
                System.out.println("Please choose one of the following options: ");
                System.out.println("      1. Messages           2. Friends");
                System.out.println("      3. Posts              4. Groups");
                System.out.println("      5. Profile");
            } else {
                // Display options for a user not logged in
                System.out.println("Please choose one of the following options: ");
                System.out.println("      1. Register           2. Login");
            }

            System.out.println("                   0. Exit");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            if (loggedInUserId != -1) {
                // When User is logged in
                switch (choice) {
                    case 1:
                        messageCtr.startMessaging();
                        break;
                    case 2:
                        friendsUI.startFriendsManagement();
                        break;
                    case 3:
                        homeFeedUI.postsSection();
                        System.out.println("Option 3: Welcome to Posts section!");
                        break;
                    case 4:
                        System.out.println("Option 4: Welcome to Groups section!");
                        break;
                    case 5:
                        profileUI.createProfile();
                        break;
                    case 0:
                        userController.logoutAllUsers();
                        System.out.println("Exiting Facebook App. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                // When User is not logged in
                switch (choice) {
                    case 1:
                        // Register new user
                        RegisterUI registerUI = new RegisterUI(userController);
                        registerUI.startRegistration();
                        break;
                    case 2:
                        // Login
                        LoginUI loginUI = new LoginUI(userController);
                        if (loginUI.startLogin()) {
                            loggedInUserId = userController.getUserByEmail(loginUI.getEmail()).getUserId();
                        }
                        break;
                    case 0:
                        System.out.println("Exiting Facebook App. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } while (choice != 0);

        scanner.close();
    }
}
