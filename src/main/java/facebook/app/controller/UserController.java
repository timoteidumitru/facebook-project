package facebook.app.controller;

import facebook.app.entities.User;
import facebook.app.exceptions.InvalidEmailFormatException;
import facebook.app.exceptions.UserIOException;
import facebook.app.exceptions.UserNotFoundException;
import facebook.app.services.UserService;

import java.util.List;

public class UserController {
    private final UserService userService;

    public UserController() {
        this.userService = new UserService();
    }

    public void addUser(User user) throws InvalidEmailFormatException, UserIOException {
        userService.addUser(user);
        if (isValidEmailFormat(user.getEmail())) {
            throw new InvalidEmailFormatException("Invalid email format for: " + user.getEmail());
        }
    }

    public User getUserByEmail(String email) throws UserIOException {
        return userService.getUserByEmail(email);
    }

    public User getUserByID(int id) throws UserNotFoundException, UserIOException {
        return userService.getUserByID(id);
    }

    public List<User> getAllUsers() throws UserIOException {
        return userService.getAllUsers();
    }

    public boolean login(String email, String password) throws UserIOException {
        if (userService.login(email, password)) {
            System.out.println(" ### Login successfully! Welcome, " + email.split("@")[0].toUpperCase() + "! ###");
            return true;
        } else {
            System.out.println("Login failed. Incorrect email or password.");
            return false;
        }
    }

    public boolean isValidEmailFormat(String email) {
        // This is a simple check; you might want to use a regular expression for a more thorough check
        return email == null || !email.contains("@");
    }

    public void logoutAllUsers() throws UserIOException {
        userService.logoutAllUsers();
    }
}
