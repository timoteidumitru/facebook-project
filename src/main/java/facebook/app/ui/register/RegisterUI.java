package facebook.app.ui.register;
import facebook.app.controller.UserCtr;
import facebook.app.model.user.User;

import java.util.Scanner;

public class RegisterUI {
    private final UserCtr userController;
    private final Scanner scanner;

    public RegisterUI(UserCtr userController) {
        this.userController = userController;
        this.scanner = new Scanner(System.in);
    }

    public void startRegistration() {
        System.out.println("Register");
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Validate password length
        if (password.length() <= 3) {
            System.out.println("Password must be at least 3 characters long. Registration failed.");
            return;
        }

        // Generate a unique ID
        long userId = generateUniqueUserId();

        User newUser = new User(userId, email, password);

        // Validate email format
//        if (!userController.isValidEmailFormat(email)) {
//            System.out.println("Invalid email format. Registration failed.");
//            return;
//        }

        if (userController.getUserByEmail(email) == null) {
            userController.addUser(newUser);
            System.out.println("Registration successful! Welcome, " + newUser.getEmail() + "!");
        } else {
            System.out.println("User with email " + email + " already exists. Registration failed.");
        }
    }

    // Method to generate a unique user ID
    private long generateUniqueUserId() {
        // For simplicity, here's a basic implementation using the current time in milliseconds.
        return userController.getAllUsers().size() + 1;
    }
}
