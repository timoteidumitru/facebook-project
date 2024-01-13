package facebook.app.controller;

import facebook.app.entities.AppPost;
import facebook.app.entities.User;
import facebook.app.exceptions.UserIOException;
import facebook.app.exceptions.UserNotFoundException;
import facebook.app.services.UserPostsService;
import facebook.app.services.UserService;

import java.util.List;

public class UserPostsController {
    private UserPostsService userPostsService = new UserPostsService();

    private final UserService userservice = new UserService();

    private final long userId = userservice.getCurrentUserId();
    User user = userservice.getUserByID((int) userId);

    public UserPostsController(UserPostsService userPostsService) throws UserNotFoundException, UserIOException {
        this.userPostsService =  userPostsService;
    }
    public UserPostsController() throws UserNotFoundException, UserIOException {

    }


    public List<AppPost> getAllPostsFromCurrentUser() throws UserNotFoundException, UserIOException {
        return userPostsService.getAllPostsFromCurrentUser(user);
    }

    public List<AppPost> getRecentPostsFromUser(int limit) throws UserIOException {
        return userPostsService.getRecentPostsFromUser(user, limit);
    }


    public AppPost getLatestPost() throws UserIOException {
        return userPostsService.getLatestPost(user);
    }


    public void createPost(String content)
    {
        userPostsService.createPost(user, content);
    }
}