package facebook.app.ui;

import facebook.app.controller.UserController;
import java.util.Scanner;

public class LoginUI {
    private final UserController userController;
    private final Scanner scanner;
    private String userEmail;  // Variable to store the logged-in user's email

    public LoginUI(UserController userController) {
        this.userController = userController;
        this.scanner = new Scanner(System.in);
    }

    public String getEmail() {
        return userEmail;
    }

    public boolean startLogin() {
        System.out.println("Login");
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (userController.login(email, password)) {

            // Set the userEmail for reference in the main Application class
            userEmail = email;

            // Additional logic or UI changes after successful login can be added here
            return true;
        } else {
            System.out.println("Login failed.");
            return false;
        }
    }
}
