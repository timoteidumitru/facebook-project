package facebook.app.controller;

import facebook.app.entities.AppPost;
import facebook.app.entities.User;
import facebook.app.exceptions.UserNotFoundException;
import facebook.app.services.UserPostsService;

import java.util.List;

public class UserPostsController {
    private UserPostsService userPostsService;

    public UserPostsController(UserPostsService userPostsService) {
        this.userPostsService = userPostsService;
    }

    public List<AppPost> getAllPostsFromUser(User user) throws UserNotFoundException {
        return userPostsService.getAllPostsFromUser(user);
    }

    public List<AppPost> getLatestPostsFromUser(User user, int limit) {
        return userPostsService.getLatestPostsFromUser(user, limit);
    }

    public AppPost getLatestPost(User user) {
        return userPostsService.getLatestPost(user);
    }


}