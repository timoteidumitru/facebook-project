package facebook.app.homefeedservicesinterfaces;

import facebook.app.entities.AppPost;

import facebook.app.entities.User;
import java.util.List;

public interface PostServiceDAO {
    AppPost getLatestPost(User user);

     List<AppPost> getAllPostsFromUser(User user);

    List<AppPost> getLatestPostsFromUser(User user, int posts);

    void createPost(AppPost appPost);


}
