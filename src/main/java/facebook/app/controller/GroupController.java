package facebook.app.controller;

import facebook.app.entities.Groups;
import facebook.app.services.GroupsService;

import java.util.List;
import java.util.stream.Collectors;

public class GroupController {
    private final GroupsService groupsService = new GroupsService();

    public GroupController() {

    }

    public List<Groups> getGroupMembers(int userId) {
        List<Groups> allGroups = groupsService.getAllGroups();
        return allGroups.stream()
                .filter(groups -> groups.getUserId() == userId)
                .collect(Collectors.toList());
    }

    public void leaveGroup (int groupId, int userId) { groupsService.leaveGroup(groupId, userId);}

    public void createGroup(String groupName, String groupDescription) {
    }

    public void addMemberToGroup(int groupId, String friendName) {
    }

    public void removeMemberFromGroup(int groupId, String friendName) {
    }
}
