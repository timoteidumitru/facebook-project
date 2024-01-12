package facebook.app.ui;

import facebook.app.controller.UserPostsController;
import facebook.app.entities.AppPost;
import facebook.app.entities.User;
import facebook.app.exceptions.UserNotFoundException;
import facebook.app.services.UserPostsService;
import facebook.app.services.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HomeFeedUI {
    private UserPostsController postsController = new UserPostsController();
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
                    List<AppPost> allPosts = postsController.getAllPostsFromCurrentUser();
                    allPosts.forEach(postCurrent -> System.out.println(postCurrent.getContent()));
                } catch (UserNotFoundException e) {
                    System.out.println("there was an error while retrieving this user");
                }
                break;

            case 2:
                System.out.println("Latest post from user");
                AppPost latestPost = postsController.getLatestPost();
                System.out.println(latestPost.getContent());
                break;

            case 3:
                System.out.println("Recent posts from user");
                System.out.println("Type the number of recent posts you want to see: ");
               List<AppPost> recentPosts = postsController.getRecentPostsFromUser(keyboard.nextInt());
                recentPosts.forEach(postCurrent -> System.out.println(postCurrent.getContent()));
                break;

            case 4:
                System.out.println("Create a new post");
                keyboard.nextLine();
                System.out.println("Enter the content of the post:");
                String content = keyboard.nextLine();
                postsController.createPost(content);
                break;
        }
    }
}

