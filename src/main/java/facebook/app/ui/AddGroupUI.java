package facebook.app.ui;

import facebook.app.controller.GroupController;
import facebook.app.entities.Groups;
import facebook.app.exceptions.UserIOException;
import facebook.app.exceptions.UserNotFoundException;
import facebook.app.services.GroupsService;

import java.util.List;
import java.util.Scanner;

public class AddGroupUI {
    private final GroupController groupController;

    GroupController groupController = new GroupController();

    private final GroupsService groupsService = new GroupsService();

    private final Scanner scanner;

    public AddGroupUI (GroupController groupController, Scanner scanner) {
        this.groupController =groupController;
        this.scanner = scanner;
    }

    public void startGroupsManagement() throws UserNotFoundException, UserIOException {
        int choice;
        do {
            System.out.println("    Groups management, please use one of the following options:");
            System.out.println("1. Add a grpup");
            System.out.println("2. Leave the group");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addGroup();
                    break;
                case 2:
                    leaveGroup();
                case 0:
                    System.out.println("Returning to Main Menu");
                    break;
                default:
                    System.out.println("Invalid choice. PLease try again.");
            }
        } while (choice != 0);
    }

    private void  addGroup() throws UserIOException, UserIOException {
        int groupId = (int) groupsService.getAllGroups();
        List<Groups> groupsList = groupController.getGroupMembers(groupId);

        if (groupsList.isEmpty()) {
            System.out.println("No groups to display.");
        } else {
            System.out.println(" Groups list:");
            for (Groups groups : groupsList) {
                System.out.println("Group Name: " + groups.getGroupName() + "| Description: " + groups.getGroupDescription());
            }
            System.out.println("-------------------------------------------");
        }
    }

    private void leaveGroup() throws UserNotFoundException, UserIOException {
        System.out.println("Enter the group id you want to leave:");
        int groupId =(groupId) groupsService.leaveGroup();
        scanner.nextLine();

        groupController.leaveGroup(groupId);
        System.out.println(groupController.getGroupMembers(groupId).getName().toUpperCase()  + "successfully left group");
    }
}
