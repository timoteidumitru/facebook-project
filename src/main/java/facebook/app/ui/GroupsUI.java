package facebook.app.ui;

import facebook.app.controller.GroupController;
import facebook.app.exceptions.UserIOException;
import facebook.app.services.GroupsService;

import java.util.Scanner;

public class GroupsUI {
    private final GroupController groupController = new GroupController();
    private final GroupsService groupServices = new GroupsService();
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
        System.out.print("Enter the group ID to see details: ");
        int groupId = keyboard.nextInt();
        groupServices.getGroupDetails(groupId);
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
    private void addMembersToGroup() {
        System.out.print("Enter the group ID to add members: ");
        int groupId = keyboard.nextInt();
        keyboard.nextLine();
        System.out.print("Enter the friend's name to add to the group: ");
        String friendName = keyboard.nextLine();
        groupServices.addMemberToGroup(groupId, friendName);
        System.out.println("Friend added to the group successfully!");
    }
    private void removeMembersFromGroup() {
        System.out.print("Enter the group ID to remove members: ");
        int groupId = keyboard.nextInt();
        keyboard.nextLine();
        System.out.print("Enter the friend's name to remove from the group: ");
        String friendName = keyboard.nextLine();
        groupServices.removeMemberFromGroup(groupId, friendName);
        System.out.println("Friend removed from the group successfully!");
    }
}
