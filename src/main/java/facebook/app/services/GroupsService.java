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
