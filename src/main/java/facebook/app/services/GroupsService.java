package facebook.app.services;

import facebook.app.dao.GroupsDAO;
import facebook.app.entities.Groups;

import java.util.List;
import java.util.Optional;

public class GroupsService {
    private final GroupsDAO groupsDAO = new GroupsDAO();

    public List<Groups> getAllGroups() {
        return groupsDAO.getAllGroups();
    }

    public int generateUniqueGroupId() {
        List<Groups> groupsList = groupsDAO.getAllGroups();
        if (groupsList.isEmpty()) {
            return 1; // Start from 1 if no groups are present
        } else {
            // Find the maximum group ID and increment it
            return groupsList.stream()
                    .mapToInt(Groups::getGroupId)
                    .max()
                    .getAsInt() + 1;
        }
    }

    public Optional<Groups> getGroupById(int groupId) {
        return groupsDAO.getGroupById(groupId);
    }

    public void createGroup(Groups groups) {
        groupsDAO.addGroup(groups);
    }

    public void deleteGroup(int groupId) {
    }

    public void getGroupDetails(int groupId) {
    }

    public void addMemberToGroup(int groupId, String friendName) {
    }

    public void removeMemberFromGroup(int groupId, String friendName) {
    }
}
