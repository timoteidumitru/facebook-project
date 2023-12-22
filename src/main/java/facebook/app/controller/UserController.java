package facebook.app.controller;

import facebook.app.entitites.User;
import facebook.app.services.UserService;

import java.util.List;

public class UserController {
    private final UserService userService;

    public UserController() {
        this.userService = new UserService();
    }

    public void addUser(User user) {
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

    public User getUserByID(int id){
        return userService.getUserByID(id);
    }

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    public boolean login(String email, String password) {
        if (userService.login(email, password)) {
            System.out.println("Login successful! Welcome, " + email.split("@")[0].toUpperCase() + "!");
            return true;
        } else {
            System.out.println("Login failed. Incorrect email or password.");
            return false;
        }
    }


    // Validation method for email format
    public boolean isValidEmailFormat(String email) {
        // This is a simple check; you might want to use a regular expression for a more thorough check
        return email == null || !email.contains("@");
    }

    public void logoutAllUsers() {
        userService.logoutAllUsers();
    }
}
