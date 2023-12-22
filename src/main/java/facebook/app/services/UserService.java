package facebook.app.services;
import facebook.app.dao.UserDAO;
import facebook.app.model.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {
    private final UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public boolean login(String email, String password) {
        List<User> userList = userDAO.readUsers(); // Change here
        Optional<User> userToLoginOptional = userList.stream()
                .filter(user -> user.getEmail().equals(email) && user.getPassword().equals(password))
                .findFirst();

        if (userToLoginOptional.isPresent()) {
            User userToLogin = userToLoginOptional.get();

            // Update the isLoggedIn field for all users
            userList.forEach(user -> user.setLoggedIn(user.equals(userToLogin)));

            // Save the changes to the file
            userDAO.writeUsers(userList); // Change here
            return true;
        } else {
            return false;
        }
    }

    public void logoutAllUsers() {
        List<User> userList = userDAO.readUsers();
        userList.forEach(user -> user.setLoggedIn(false));
        userDAO.writeUsers(userList);
    }

    public boolean logout(long userId) {
        List<User> userList = userDAO.readUsers();
        for (User user : userList) {
            if (user.getUserId() == userId) {
                user.setLoggedIn(false);
                userDAO.writeUsers(userList);
                return true;
            }
        }
        return false;
    }

    public List<User> getLoggedInUsers() {
        List<User> userList = userDAO.readUsers();
        List<User> loggedInUsers = new ArrayList<>();
        for (User user : userList) {
            if (user.isLoggedIn()) {
                loggedInUsers.add(user);
            }
        }
        return loggedInUsers;
    }

    public void addUser(User user) {
        // Basic business logic: Ensure password meets minimum length requirement
        if (user.getPassword().length() < 3) {
            System.out.println("Password must be at least 4 characters long. Please try again!");
            return;
        }

        // Check if the email is in a valid format
        if (!isValidEmailFormat(user.getEmail())) {
            System.out.println(user);
            System.out.println("Invalid email format. Please try again!");
            return;
        }

        // Perform additional business logic/validation before adding to the DAO
        List<User> userList = userDAO.readUsers();
        if (!isUserUnique(userList, user.getEmail())) {
            System.out.println("User with email " + user.getEmail() + " already exists. Please try a different one!");
            return;
        }

        userList.add(user);
        userDAO.writeUsers(userList);
    }

    public User getUserById(long user_id) {
        // Basic business logic: Check if the user_id is valid
        if (user_id <= 0) {
            System.out.println("Invalid user_id. Please provide a valid user_id.");
            return null;
        }

        // Perform additional business logic/validation before retrieving from the DAO
        return userDAO.getUserById(user_id);
    }

    public User getUserByEmail(String email) {
        // Basic business logic: Check if the email is not empty
        if (email == null || email.trim().isEmpty()) {
            System.out.println("Email cannot be empty. Please provide a valid email.");
            return null;
        }

        // Perform additional business logic/validation before retrieving from the DAO
        return userDAO.getUserByEmail(email);
    }

    public List<User> getAllUsers() {
        // Perform additional business logic if needed before reading from the DAO
        return userDAO.readUsers();
    }

    // Basic business logic: Check if the email has a valid format
    private boolean isValidEmailFormat(String email) {
        // This is a simple check; you might want to use a regular expression for a more thorough check
        return email != null && email.contains("@");
    }

    // Check if the user email is unique in the provided list
    private boolean isUserUnique(List<User> userList, String email) {
        return userList.stream().noneMatch(user -> user.getEmail().equals(email));
    }
}
