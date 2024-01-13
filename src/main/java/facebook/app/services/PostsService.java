package facebook.app.services;

import facebook.app.dao.UserDAO;
import facebook.app.dao.PostsDAO;
import facebook.app.entities.Posts;
import facebook.app.entities.User;
import facebook.app.exceptions.UserIOException;
import facebook.app.exceptions.UserNotFoundException;

import java.util.Collections;
import java.util.List;

public class PostsService {
    private PostsDAO userPostsDAO = new PostsDAO();
    private UserDAO userDAO = new UserDAO();
    private final User user = new User();
    public PostsService(PostsDAO userPostsDAO, UserDAO userDAO) {
        this.userPostsDAO = userPostsDAO;
        this.userDAO = userDAO;
    }
    public PostsService() {}

    public List<Posts> getAllPostsFromCurrentUser(User user) throws UserNotFoundException, UserIOException {
        user = userDAO.getUserByID((int) user.getUserId());
        if (user == null) {
            throw new UserNotFoundException("User with ID " + (int) user.getUserId() + " not found");
        } else {
            return userPostsDAO.getAllPostsFromCurrentUser(user);
        }
    }

    public List<Posts> getRecentPostsFromUser(User user, int limit) throws UserIOException {
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
    public Posts getLatestPost(User user) throws UserIOException {
        user = userDAO.getUserByID((int) user.getUserId());
        return userPostsDAO.getLatestPost(user);
    }
    public void createPost(User user, String content) {
        if (user == null || content == null || !content.matches("\\S{2,}.*")) {
            System.out.println("Invalid input for creating a post. User and content are required.");
            return;
        }
        Long timePosted = System.currentTimeMillis(); // Assuming you want to use the current time
        Posts appPost = new Posts(user, content, timePosted);
        // Call DAO to write the post to the database
        userPostsDAO.createPost(appPost);
        System.out.println("Post created successfully.");
    }

}
