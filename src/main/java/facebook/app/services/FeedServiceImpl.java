package facebook.app.services;
import facebook.app.entities.Posts;
import facebook.app.entities.User;

import java.util.ArrayList;
import java.util.List;
public class FeedServiceImpl implements FeedServiceInterface {

    private final List<User> users;
    private final List<Posts> allPosts;

    public FeedServiceImpl() {
        this.users = new ArrayList<>();
        this.allPosts = new ArrayList<>();
    }
    public void addUser(User user) {
        users.add(user);
    }
    public void addPost(User user, Posts post) {
        user.addPost(post);
        allPosts.add(post);
    }
    public void showLatestPost() {

    }
    public void messageToUser(User user, String message) {
        user.sendMessage(user,message);
    }


}
