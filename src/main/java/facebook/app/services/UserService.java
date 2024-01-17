package facebook.app.services;
import facebook.app.dao.UserDAO;
import facebook.app.entities.User;
import facebook.app.exceptions.UserIOException;

import java.util.List;
import java.util.Optional;

public class UserService {
    private final UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public boolean login(String email, String password) throws UserIOException {
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

    public void logoutAllUsers() throws UserIOException {
        List<User> userList = userDAO.readUsers();
        userList.forEach(user -> user.setLoggedIn(false));
        userDAO.writeUsers(userList);
    }


    public void addUser(User user) throws UserIOException {
        // Ensure password meets minimum length requirement
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

    public User getUserByEmail(String email) throws UserIOException {
        // Check if the email is not empty
        if (email == null || email.trim().isEmpty()) {
            System.out.println("Email cannot be empty. Please provide a valid email.");
            return null;
        }

        // Perform additional business logic/validation before retrieving from the DAO
        return userDAO.getUserByEmail(email);
    }
    public User getUserByID(int userID) throws  UserIOException {
        return userDAO.getUserByID(userID);
    }

    public int getCurrentUserId() throws UserIOException {
        List<User> userList = userDAO.readUsers();
        for (User user : userList) {
            if (user.isLoggedIn()) {
                return (int) user.getUserId();
            }
        }
        return -1; // Return -1 if no user is currently logged in
    }

    public List<User> getAllUsers() throws UserIOException {
        // Perform additional business logic if needed before reading from the DAO
        return userDAO.readUsers();
    }

    // Check if the email has a valid format
    private boolean isValidEmailFormat(String email) {
        // This is a simple check; you might want to use a regular expression for a more thorough check
        return email != null && email.contains("@");
    }

    // Check if the user email is unique in the provided list
    private boolean isUserUnique(List<User> userList, String email) {
        return userList.stream().noneMatch(user -> user.getEmail().equals(email));
    }
}
