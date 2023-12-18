package facebook.app.homefeedservices;

import facebook.app.entitites.AppPost;
import facebook.app.model.user.User;

public interface FeedService {
     void addPost(User user, AppPost post) ;
     void showLatestPost() ;
     void messageToUser(User user, String message) ;

}
