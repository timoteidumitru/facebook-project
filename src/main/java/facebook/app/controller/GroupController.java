package facebook.app.controller;

import facebook.app.entities.Friends;
import facebook.app.entities.Groups;
import facebook.app.services.GroupsService;

import java.util.List;
import java.util.stream.Collectors;

public class GroupController {
    private final GroupsService groupsService;

    public GroupController(GroupsService groupsService) {
        this.groupsService = groupsService;
    }

    public List<Groups> getGroupMembers(int userId) {
        List<Groups> allGroups = groupsService.getAllGroups();
        return allGroups.stream()
                .filter(groups -> groups.getUserId() == userId)
                .collect(Collectors.toList());
    }

    public void leaveGroup (int groupId, int userId) { groupsService.leaveGroup(groupId, userId);}

}
