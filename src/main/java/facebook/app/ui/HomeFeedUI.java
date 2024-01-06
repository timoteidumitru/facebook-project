package facebook.app.ui;

import facebook.app.controller.UserPostsController;
import facebook.app.entities.User;
import facebook.app.exceptions.UserNotFoundException;
import facebook.app.services.UserPostsService;
import facebook.app.services.UserService;

import java.util.Scanner;

public class HomeFeedUI {

   private  UserPostsController postsController;
    User user;


    public void postsSection() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Choose which posts would you like to see next");
        int post = keyboard.nextInt();
        switch (post) {
            case 1:
                System.out.println("All  posts from user");
                try {
                    postsController.getAllPostsFromUser();
                } catch (UserNotFoundException e) {
                    System.out.println("there was an error while retrieving this user");
                }


            case 2:
                System.out.println("Latest post from user");
                postsController.getLatestPost(user);

            case 3:
                System.out.println("Recent posts from user:");
                postsController.getLatestPostsFromUser( 3);
            case 4:
                System.out.println("Create a new post:");
                System.out.println("ENter the content of the post:");
                postsController.createPost(keyboard.next());

        }

    }


}

