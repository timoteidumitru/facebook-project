package facebook.app.ui;
import facebook.app.controller.UserController;
import facebook.app.entities.User;
import facebook.app.exceptions.InvalidEmailFormatException;
import facebook.app.exceptions.UserIOException;

import java.util.Scanner;

public class RegisterUI {
    private final UserController userController;
    private final Scanner scanner;

    public RegisterUI(UserController userController) {
        this.userController = userController;
        this.scanner = new Scanner(System.in);
    }

    public void startRegistration() throws InvalidEmailFormatException, UserIOException {
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

        if (userController.getUserByEmail(email) == null) {
            userController.addUser(newUser);
            System.out.println("Registration successful! Welcome aboard, " + newUser.getEmail().split("@")[0].toUpperCase() + "!");
        } else {
            System.out.println("User with email " + email + " already exists. Registration failed.");
        }
    }

    // Method to generate a unique user ID
    private long generateUniqueUserId() throws UserIOException {
        // For simplicity, here's a basic implementation using the current time in milliseconds.
        return userController.getAllUsers().size() + 1;
    }
}
