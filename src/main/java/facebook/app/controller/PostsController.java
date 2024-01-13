package facebook.app.controller;

import facebook.app.entities.Posts;
import facebook.app.entities.User;
import facebook.app.exceptions.UserIOException;
import facebook.app.exceptions.UserNotFoundException;
import facebook.app.services.PostsService;
import facebook.app.services.UserService;

import java.util.List;

public class PostsController {
    private PostsService userPostsService = new PostsService();
    private final UserService userservice = new UserService();
    private final long userId = userservice.getCurrentUserId();
    User user = userservice.getUserByID((int) userId);

    public PostsController(PostsService userPostsService) throws UserNotFoundException, UserIOException {
        this.userPostsService =  userPostsService;
    }
    public PostsController() throws UserNotFoundException, UserIOException {

    }

    public List<Posts> getAllPostsFromCurrentUser() throws UserNotFoundException, UserIOException {
        return userPostsService.getAllPostsFromCurrentUser(user);
    }

    public List<Posts> getRecentPostsFromUser(int limit) throws UserIOException {
        return userPostsService.getRecentPostsFromUser(user, limit);
    }


    public Posts getLatestPost() throws UserIOException {
        return userPostsService.getLatestPost(user);
    }


    public void createPost(String content)
    {
        userPostsService.createPost(user, content);
    }
}