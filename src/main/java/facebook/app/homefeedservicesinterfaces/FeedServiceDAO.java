package facebook.app.homefeedservicesinterfaces;

import facebook.app.entities.AppPost;
import facebook.app.entities.User;

public interface FeedServiceDAO {
     void addPost(User user, AppPost post) ;
     void showLatestPost() ;
    // void messageToUser(User user, String message) ;

}
