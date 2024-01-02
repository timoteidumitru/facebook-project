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

    public UserPostsService(UserPostsDAO userPostsDAO, UserDAO userDAO) {
        this.userPostsDAO = userPostsDAO;
        this.userDAO = userDAO;
    }


    public List<AppPost> getAllPostsFromUser(User user) throws UserNotFoundException {
        user = userDAO.getUserByID((int) user.getUserId());
        if (user == null) {
            throw new UserNotFoundException("User with ID " + (int) user.getUserId() + " not found");
        } else {
            return userPostsDAO.getAllPostsFromUser(user);
        }
    }

    public List<AppPost> getLatestPostsFromUser(User user, int limit) {
        return userPostsDAO.getLatestPostsFromUser(user, limit);
    }

    public AppPost getLatestPost(User user) {
        return userPostsDAO.getLatestPost(user);
    }

    // Add more methods as needed for your application
}
