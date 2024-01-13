package facebook.app.services;

import facebook.app.entities.AppPost;

import facebook.app.entities.User;
import java.util.List;

public interface PostServiceInterface {
    AppPost getLatestPost(User user);

     List<AppPost> getAllPostsFromCurrentUser(User user);

    List<AppPost> getRecentPostsFromUser(User user, int posts);

    void createPost(AppPost appPost);


}
