package facebook.app.services;

import facebook.app.dao.UserDAO;
import facebook.app.dao.UserPostsDAO;
import facebook.app.entities.AppPost;
import facebook.app.entities.User;
import facebook.app.exceptions.UserNotFoundException;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class UserPostsService {
    private UserPostsDAO userPostsDAO = new UserPostsDAO();
    private UserDAO userDAO = new UserDAO();
    private User user = new User();
    public UserPostsService(UserPostsDAO userPostsDAO, UserDAO userDAO) {
        this.userPostsDAO = userPostsDAO;
        this.userDAO = userDAO;
    }
    public UserPostsService() {}

    public List<AppPost> getAllPostsFromCurrentUser(User user) throws UserNotFoundException {
        user = userDAO.getUserByID((int) user.getUserId());
        if (user == null) {
            throw new UserNotFoundException("User with ID " + (int) user.getUserId() + " not found");
        } else {
            return userPostsDAO.getAllPostsFromCurrentUser(user);
        }
    }

    public List<AppPost> getRecentPostsFromUser(User user, int limit) {
        user = userDAO.getUserByID((int) user.getUserId());
        if (user != null) {
            user = userDAO.getUserByID((int) user.getUserId());
            if (user != null) {
                return userPostsDAO.getRecentPostsFromUser(user, limit);
            } else {
                System.out.println("User not found.");
            }
        } else {
            System.out.println("User is null.");
        }
        return Collections.emptyList();
    }
    public AppPost getLatestPost(User user) {
        user = userDAO.getUserByID((int) user.getUserId());
        return userPostsDAO.getLatestPost(user);
    }
    public void createPost(User user, String content) {
        if (user == null || content == null || !content.matches("\\S{2,}.*")) {
            System.out.println("Invalid input for creating a post. User and content are required.");
            return;
        }
        Long timePosted = System.currentTimeMillis(); // Assuming you want to use the current time
        AppPost appPost = new AppPost(user, content, timePosted);
        // Call DAO to write the post to the database
        userPostsDAO.createPost(appPost);
        System.out.println("Post created successfully.");
    }

}
