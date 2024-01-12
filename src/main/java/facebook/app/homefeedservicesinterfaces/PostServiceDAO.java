package facebook.app.homefeedservicesinterfaces;

import facebook.app.entities.AppPost;

import facebook.app.entities.User;
import java.util.List;

public interface PostServiceDAO {
    AppPost getLatestPost(User user);

     List<AppPost> getAllPostsFromCurrentUser(User user);

    List<AppPost> getRecentPostsFromUser(User user, int posts);

    void createPost(AppPost appPost);


}
