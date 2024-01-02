package facebook.app.ui.homefeedUI;

import facebook.app.controller.UserPostsController;
import facebook.app.entities.User;
import facebook.app.exceptions.UserNotFoundException;

import java.util.Scanner;

public class HomeFeedUI {

   UserPostsController postsController;
   User user;
    public void postsSection() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Choose which posts would you like to see next");
        int post = keyboard.nextInt();
        switch(post) {
            case 1:
                System.out.println("All  posts from user");
                try {
                    postsController.getAllPostsFromUser(user);
                } catch (UserNotFoundException e) {
                    throw new RuntimeException(e);
                }


            case 2:
                System.out.println("Latest post from user");
                postsController.getLatestPost(user);

            case 3:
                System.out.println("Recent posts from user:");
                postsController.getLatestPostsFromUser(user, 3);

        }

        }


    }

