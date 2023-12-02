package facebook.app.controller;
import facebook.app.model.user.User;
import facebook.app.services.user.UserService;

import java.util.List;

public class UserCtr {
    private final UserService userService;

    public UserCtr() {
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

    public User getUserById(long user_id) {
        return userService.getUserById(user_id);
    }

    public User getUserByEmail(String email) {
        return userService.getUserByEmail(email);
    }

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Validation method for email format
    public boolean isValidEmailFormat(String email) {
        // This is a simple check; you might want to use a regular expression for a more thorough check
        return email == null || !email.contains("@");
    }
}
