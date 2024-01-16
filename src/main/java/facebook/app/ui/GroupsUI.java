package facebook.app.ui;

import facebook.app.controller.GroupController;
import facebook.app.entities.Groups;
import facebook.app.entities.User;
import facebook.app.exceptions.UserIOException;
import facebook.app.services.GroupsService;
import facebook.app.services.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class GroupsUI {
    private final GroupController groupController = new GroupController();
    private final GroupsService groupServices = new GroupsService();
    private final UserService userService = new UserService();
    private final Scanner keyboard = new Scanner(System.in);

    public void startGroup() throws UserIOException {
        boolean keepRunning = true;
        while (keepRunning) {
            System.out.println("\n         --- Groups Management ---");
            System.out.println("1. Create a Group         2. See Group Details");
            System.out.println("3. Add Members to Group   4. Remove Members from Group");
            System.out.println("             0. Return to Main Menu");
            System.out.println("            Please choose an option: ");
            int choice = keyboard.nextInt();

            switch (choice) {
                case 1:
                    createGroup();
                    break;
                case 2:
                    seeGroupDetails();
                    break;
                case 3:
                    addMembersToGroup();
                    break;
                case 4:
                    removeMembersFromGroup();
                    break;
                case 0:
                    keepRunning = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void seeGroupDetails() {
        // Display all available groups
        System.out.println("Available groups:");
        groupServices.getAllGroups().forEach(group -> System.out.println("ID: " + group.getGroupId() + ", Name: " + group.getGroupName()));

        // Ask the user to enter the group ID
        System.out.print("Enter the group ID to see details: ");
        int groupId = keyboard.nextInt();

        // Fetch the selected group
        Optional<Groups> groupOpt = groupServices.getGroupById(groupId);
        if (groupOpt.isPresent()) {
            Groups group = groupOpt.get();
            System.out.println("Group Name: " + group.getGroupName());
            System.out.println("Group Description: " + group.getGroupDescription());

            // Display all members of the chosen group
            System.out.println("Members of the group:");
            Arrays.asList(group.getUserId().split(",")).forEach(userId -> {
                try {
                    User user = userService.getUserByID(Integer.parseInt(userId));
                    if (user != null) {
                        System.out.println("ID: " + user.getUserId() + ", Name: " + user.getName());
                    }
                } catch (NumberFormatException | UserIOException e) {
                    System.err.println("Invalid format for user ID: " + userId);
                }
            });
        } else {
            System.out.println("Group not found with ID: " + groupId);
        }
    }

    private void createGroup() throws UserIOException {
        keyboard.nextLine();
        System.out.print("Enter the group name: ");
        String groupName = keyboard.nextLine();
        System.out.print("Enter the group description: ");
        String groupDescription = keyboard.nextLine();
        groupController.createGroup(groupName, groupDescription);
        System.out.println("Group created successfully!");
    }
    private void addMembersToGroup() throws UserIOException {
        // Display all available groups
        System.out.println("Available groups:");
        groupServices.getAllGroups().forEach(group -> System.out.println("ID: " + group.getGroupId() + ", Name: " + group.getGroupName()));

        // Ask the user to enter the group ID
        System.out.print("Enter the group ID to add members: ");
        int groupId = keyboard.nextInt();

        Optional<Groups> groupOpt = groupServices.getGroupById(groupId);
        if (groupOpt.isPresent()) {
            Groups group = groupOpt.get();
            List<String> currentMemberIds = Arrays.asList(group.getUserId().split(","));
            // Display users who are not already members of the group
            System.out.println("Available users to add:");
            userService.getAllUsers().forEach(user -> {
                if (!currentMemberIds.contains(String.valueOf(user.getUserId()))) {
                    System.out.println("ID: " + user.getUserId() + ", Name: " + user.getName());
                }
            });

            // Ask the user to enter the friend's user ID
            System.out.print("Enter the user ID of the friend to add to the group: ");
            int friendId = keyboard.nextInt();

            // Check if the selected user is not already in the group
            if (!currentMemberIds.contains(String.valueOf(friendId))) {
                // Call the service to add the member to the group
                groupServices.addMemberToGroup(groupId, friendId);
                System.out.println("Friend added to the group successfully!");
            } else {
                System.out.println("This user is already a member of the group.");
            }
        } else {
            System.out.println("Group not found with ID: " + groupId);
        }
    }

    private void removeMembersFromGroup() throws UserIOException {
        // Display all available groups
        System.out.println("Available groups:");
        groupServices.getAllGroups().forEach(group -> System.out.println("ID: " + group.getGroupId() + ", Name: " + group.getGroupName()));

        // Ask the user to enter the group ID
        System.out.print("Enter the group ID to remove members: ");
        int groupId = keyboard.nextInt();

        // Fetch the selected group
        Optional<Groups> groupOpt = groupServices.getGroupById(groupId);
        if (groupOpt.isPresent()) {
            Groups group = groupOpt.get();
            List<String> memberIds = Arrays.asList(group.getUserId().split(","));

            // Display all members of the chosen group
            System.out.println("Members of the group:");
            for (String userId : memberIds) {
                try {
                    User user = userService.getUserByID(Integer.parseInt(userId));
                    if (user != null) {
                        System.out.println("ID: " + user.getUserId() + ", Name: " + user.getName());
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Invalid format for user ID: " + userId);
                }
            }

            // Ask the user to enter the member's user ID to remove
            System.out.print("Enter the user ID of the member to remove from the group: ");
            int memberId = keyboard.nextInt();

            if (memberIds.contains(String.valueOf(memberId))) {
                groupServices.removeMemberFromGroup(groupId, memberId);
                System.out.println("Member removed from the group successfully!");
            } else {
                System.out.println("Member ID not found in the group.");
            }
        } else {
            System.out.println("Group not found with ID: " + groupId);
        }
    }
}
