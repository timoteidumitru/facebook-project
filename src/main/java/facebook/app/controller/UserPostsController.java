package facebook.app.controller;

import facebook.app.entities.AppPost;
import facebook.app.entities.User;
import facebook.app.exceptions.UserNotFoundException;
import facebook.app.services.UserPostsService;
import facebook.app.services.UserService;

import java.util.List;

public class UserPostsController {
    private UserPostsService userPostsService;

    private UserService userservice;

    private final long userId = userservice.getCurrentUserId();
    User user = userservice.getUserByID((int) userId);

    public UserPostsController(UserPostsService userPostsService) {
        this.userPostsService = userPostsService;
    }

    public List<AppPost> getAllPostsFromUser() throws UserNotFoundException {
        return userPostsService.getAllPostsFromUser(user);
    }

    public List<AppPost> getLatestPostsFromUser(int limit) {
        return userPostsService.getLatestPostsFromUser(limit);
    }


    public AppPost getLatestPost(User user) {
        return userPostsService.getLatestPost(user);
    }


}