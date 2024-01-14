package facebook.app.ui;

import facebook.app.controller.GroupController;
import facebook.app.controller.PostsController;
import facebook.app.entities.Groups;
import facebook.app.entities.Posts;
import facebook.app.exceptions.UserIOException;
import facebook.app.services.UserService;

import java.util.List;
import java.util.Scanner;

public class GroupsUI {
    private final GroupController groupController = new GroupController();
    private final PostsController postsController = new PostsController();
    private final UserService userService = new UserService();
    private final Scanner keyboard = new Scanner(System.in);

    public GroupsUI() {
    }

    public void groupsSection() throws UserIOException {
        boolean keepRunning = true;
        while (keepRunning) {
            System.out.println("\n--- Groups Management ---");
            System.out.println("1. Create Group");
            System.out.println("2. Add Members to Group");
            System.out.println("3. Remove Members from Group");
            System.out.println("4. Create Post within Group");
            System.out.println("5. Back to Main Menu");
            System.out.print("Please choose an option: ");

            int choice = keyboard.nextInt();

            switch (choice) {
                case 1:
                    createGroup();
                    break;
                case 2:
                    addMembersToGroup();
                    break;
                case 3:
                    removeMembersFromGroup();
                    break;
                case 4:
                    createPostWithinGroup();
                    break;
                case 5:
                    keepRunning = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void createGroup() {
        keyboard.nextLine(); // Consume the leftover newline
        System.out.print("Enter the group name: ");
        String groupName = keyboard.nextLine();
        System.out.print("Enter the group description: ");
        String groupDescription = keyboard.nextLine();
        groupController.createGroup(groupName, groupDescription);
        System.out.println("Group created successfully!");
    }

    private void addMembersToGroup() {
        System.out.print("Enter the group ID to add members: ");
        int groupId = keyboard.nextInt();
        keyboard.nextLine(); // Consume the leftover newline
        System.out.print("Enter the friend's name to add to the group: ");
        String friendName = keyboard.nextLine();
        groupController.addMemberToGroup(groupId, friendName);
        System.out.println("Friend added to the group successfully!");
    }

    private void removeMembersFromGroup() {
        System.out.print("Enter the group ID to remove members: ");
        int groupId = keyboard.nextInt();
        keyboard.nextLine(); // Consume the leftover newline
        System.out.print("Enter the friend's name to remove from the group: ");
        String friendName = keyboard.nextLine();
        groupController.removeMemberFromGroup(groupId, friendName);
        System.out.println("Friend removed from the group successfully!");
    }

    private void createPostWithinGroup() throws UserIOException {
        System.out.print("Enter the group ID to create a post: ");
        int groupId = keyboard.nextInt();
        keyboard.nextLine(); // Consume the leftover newline
        System.out.print("Enter the content of the post: ");
        String content = keyboard.nextLine();
        postsController.createGroupPost(content, groupId);
        System.out.println("Post created within the group successfully!");
    }
}
