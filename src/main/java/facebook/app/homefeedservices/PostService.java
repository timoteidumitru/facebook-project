package facebook.app.homefeedservices;

import facebook.app.entitites.AppPost;
import facebook.app.model.user.User;

import java.util.List;

public interface PostService {
    AppPost getLatestPost(User user);

     List<AppPost> getAllPostsFromUser(User user);

    List<AppPost> getLatestPostsFromUser(User user, int posts);


}
