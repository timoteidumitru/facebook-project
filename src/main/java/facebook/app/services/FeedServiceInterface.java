package facebook.app.services;

import facebook.app.entities.Posts;
import facebook.app.entities.User;

public interface FeedServiceInterface {
     void addPost(User user, Posts post) ;
     void showLatestPost() ;
    // void messageToUser(User user, String message) ;

}
