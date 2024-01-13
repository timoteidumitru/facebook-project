package facebook.app.ui;

import facebook.app.controller.UserController;
import facebook.app.exceptions.UserIOException;

import java.util.Scanner;

public class LoginUI {
    private final UserController userController;
    private final Scanner scanner;
    private String userEmail;

    public LoginUI(UserController userController) {
        this.userController = userController;
        this.scanner = new Scanner(System.in);
    }

    public String getEmail() {
        return userEmail;
    }

    public boolean startLogin() throws UserIOException {
        System.out.println("Login");
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (userController.login(email, password)) {
            // Set the userEmail for reference in the main Application class
            userEmail = email;

            return true;
        } else {
            System.out.println("Login failed.");
            return false;
        }
    }
}
