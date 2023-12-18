package facebook.app.services;
import facebook.app.entitites.AppPost;
import facebook.app.model.user.User;

import java.util.ArrayList;
import java.util.List;
public class FeedService {

    private List<User> users;
    private List<AppPost> allPosts;

    public FeedService() {
        this.users = new ArrayList<>();
        this.allPosts = new ArrayList<>();
    }
    public void addUser(User user) {
        users.add(user);
    }
    public void addPost(User user, AppPost post) {
        user.addPost(post);
        allPosts.add(post);
    }
    
    public void showLatestPost() {

    }
    
    public void messageToUser(User user) {
        String message=;
        user.sendMessage(user,message);
    }

}
