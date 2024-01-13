package facebook.app.services;

import facebook.app.entities.Posts;

import facebook.app.entities.User;
import java.util.List;

public interface PostServiceInterface {
    Posts getLatestPost(User user);

     List<Posts> getAllPostsFromCurrentUser(User user);

    List<Posts> getRecentPostsFromUser(User user, int posts);

    void createPost(Posts appPost);


}
