package facebook.app.ui;

import facebook.app.controller.UserPostsController;
import facebook.app.entities.User;
import facebook.app.exceptions.UserNotFoundException;
import facebook.app.services.UserPostsService;
import facebook.app.services.UserService;

import java.util.Scanner;

public class HomeFeedUI {

    private UserPostsController postsController = new UserPostsController();
    User user;


    public void postsSection() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println(" Feed management, please chose one of the following options:");
        System.out.println("1. See all posts from current user");
        System.out.println("2. See latest post from current user");
        System.out.println("3. See recent posts from current user");
        System.out.println("4. Create new post");
        System.out.println("5. See recent posts from a different user");
        int post = keyboard.nextInt();
        switch (post) {
            case 1:
                System.out.println("All  posts from user");
                try {
                    System.out.println(postsController.getAllPostsFromCurrentUser());
                } catch (UserNotFoundException e) {
                    System.out.println("there was an error while retrieving this user");
                }
                break;

            case 2:
                System.out.println("Latest post from user");
                postsController.getLatestPost();
                break;

            case 3:
                System.out.println("Recent posts from user:");
                postsController.getRecentPostsFromUser(3);
                break;
            case 4:
                System.out.println("Create a new post:");
                System.out.println("ENter the content of the post:");
                postsController.createPost(keyboard.next());
                break;

        }

    }


}

