package facebook.app.ui;

import facebook.app.controller.FriendsController;
import facebook.app.controller.UserController;
import facebook.app.entites.Friends;
import facebook.app.entites.User;
import facebook.app.services.UserService;

import java.util.List;
import java.util.Scanner;

public class FriendsUI {
    private final FriendsController friendsController;
    private final UserController userController = new UserController();
    private final UserService userService = new UserService();
    private final Scanner scanner;

    public FriendsUI(FriendsController friendsController, Scanner scanner) {
        this.friendsController = friendsController;
        this.scanner = scanner;
    }

    public void startFriendsManagement() {
        int choice;
        do {
            System.out.println("    Friends Management:");
            System.out.println("1. View Friends");
            System.out.println("2. Add a Friend");
            System.out.println("3. Remove a Friend");
            System.out.println("0. Back to Main Menu");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    // View friends
                    viewFriends();
                    break;
                case 2:
                    // Add a friend
                    addFriend();
                    break;
                case 3:
                    // Remove a friend
                    removeFriend();
                    break;
                case 0:
                    System.out.println("Returning to Main Menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    private void viewFriends() {
        int userId = (int) userService.getCurrentUserId();
        List<Friends> friendsList = friendsController.getFriendsOfUser(userId);

        if (friendsList.isEmpty()) {
            System.out.println("No friends to display.");
        } else {
            System.out.println("Friends List:");
            for (Friends friend : friendsList) {
                User friendDetails = userController.getUserByID(friend.getFriendId());
                System.out.println("Name: " + friendDetails.getName());
                System.out.println("------------------------------");
            }
        }
    }

    private void addFriend() {
        System.out.println("Enter the User ID of the user you want to add as a friend:");
        List<User> users = userController.getAllUsers();
        int fromUserId = (int) userService.getCurrentUserId();
        for (User user : users) {
            if (user.getUserId() == fromUserId) {
                continue; // Skip current logged-in user
            }
            System.out.println("User ID: " + user.getUserId() + ", Name: " + user.getEmail().split("@")[0]);
        }

        int friendId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        friendsController.sendFriendRequest(fromUserId, friendId);
        System.out.println("Friend request sent successfully!");
    }

    private void removeFriend() {
        System.out.println("Enter the User ID of the friend you want to remove:");
        int friendId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        int userId = (int) userService.getCurrentUserId();
        friendsController.removeFriend(userId, friendId);
        System.out.println("Friend removed successfully!");
    }
}
