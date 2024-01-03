package facebook.app.services;

import facebook.app.dao.UserDAO;
import facebook.app.dao.UserPostsDAO;
import facebook.app.entities.AppPost;
import facebook.app.entities.User;
import facebook.app.exceptions.UserNotFoundException;

import java.util.List;

public class UserPostsService {

    private UserPostsDAO userPostsDAO;
    private UserDAO userDAO;
    private User user;
    public UserPostsService(UserPostsDAO userPostsDAO, UserDAO userDAO) {
        this.userPostsDAO = userPostsDAO;
        this.userDAO = userDAO;
    }

//    public boolean isValidUserId(int userId) {
//        // Check if the provided userId is a valid integer and exists in the database
//        return userDAO.getUserByID(userId);
//    }
//
//    public boolean hasPostsForUser(int userId) {
//        // Check if the user has any posts in the database
//        return userDAO.getNumPostsForUser(userId) > 0;
//    }

    public List<AppPost> getAllPostsFromUser(User user) throws UserNotFoundException {
        user = userDAO.getUserByID((int) user.getUserId());
        if (user == null) {
            throw new UserNotFoundException("User with ID " + (int) user.getUserId() + " not found");
        } else {
            return userPostsDAO.getAllPostsFromUser(user);
        }
    }



    public List<AppPost> getLatestPostsFromUser(int limit) {
         user = userDAO.getUserByID((int) user.getUserId());
         return userPostsDAO.getLatestPostsFromUser(user, limit);
    }

    public AppPost getLatestPost(User user) {
        return userPostsDAO.getLatestPost(user);
    }

    // Add more methods as needed for your application
}
