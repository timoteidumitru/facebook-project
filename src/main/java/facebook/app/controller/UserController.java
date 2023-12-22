package facebook.app.controller;

import facebook.app.model.user.User;
import facebook.app.services.UserService;

import java.util.List;

public class UserController {
    private final UserService userService;

    public UserController() {
        this.userService = new UserService();
    }

    public void addUser(User user) {
        System.out.println(user);
        // Basic validation: Check if the email is in a valid format
        if (isValidEmailFormat(user.getEmail())) {
            System.out.println("Invalid email format. Please try again.");
            return;
        }

        userService.addUser(user);
    }

    public User getUserByEmail(String email) {
        return userService.getUserByEmail(email);
    }

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    public boolean login(String email, String password) {
        if (userService.login(email, password)) {
            System.out.println("Login successful! Welcome, " + email + "!");
            return true;
        } else {
            System.out.println("Login failed. Incorrect email or password.");
            return false;
        }
    }

    public boolean logout(long userId) {
        if (userService.logout(userId)) {
            System.out.println("Logout successful!");
            return true;
        } else {
            System.out.println("Logout failed. User not found or not logged in.");
            return false;
        }
    }

    public List<User> getLoggedInUsers() {
        return userService.getLoggedInUsers();
    }

    // Validation method for email format
    public boolean isValidEmailFormat(String email) {
        // This is a simple check; you might want to use a regular expression for a more thorough check
        return email == null || !email.contains("@");
    }

    public void logoutAllUsers() {
        userService.logoutAllUsers();
        System.out.println("All users have been logged out.");
    }
}
