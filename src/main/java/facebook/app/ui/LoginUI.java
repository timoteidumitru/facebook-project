package facebook.app.ui;
import facebook.app.controller.UserCtr;
import java.util.Scanner;

public class LoginUI {
    private final UserCtr userController;
    private final Scanner scanner;

    public LoginUI(UserCtr userController) {
        this.userController = userController;
        this.scanner = new Scanner(System.in);
    }

    public void startLogin() {
        System.out.println("Login");
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (userController.getUserByEmail(email) != null) {
            // Validate password
            if (userController.getUserByEmail(email).getPassword().equals(password)) {
                System.out.println("Login successful! Welcome, " + email + "!");
            } else {
                System.out.println("Login failed. Incorrect password.");
            }
        } else {
            System.out.println("Login failed. User with email " + email + " not found.");
        }
    }
}
