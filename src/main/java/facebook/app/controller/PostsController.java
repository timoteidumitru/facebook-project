package facebook.app.controller;

import facebook.app.entities.Posts;
import facebook.app.entities.User;
import facebook.app.exceptions.UserIOException;
import facebook.app.exceptions.UserNotFoundException;
import facebook.app.services.PostsService;

import java.util.List;

public class PostsController {
    private PostsService postsService = new PostsService();
    private User user;

    public PostsController(PostsService postsService, User user) {
        this.postsService =  postsService;
        this.user = user;
    }
    public PostsController() {

    }

    public List<Posts> getAllPosts() throws UserNotFoundException, UserIOException {
        return postsService.getAllPosts();
    }

    public List<Posts> getRecentPosts(int limit) throws UserIOException {
        return postsService.getRecentPosts(user, limit);
    }

    public Posts getLatestPost() throws UserIOException {
        return postsService.getLatestPost(user);
    }

    public void createPost(String content) throws UserIOException {
        postsService.createPost(content);
    }
}
