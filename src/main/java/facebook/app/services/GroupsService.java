package facebook.app.services;

import facebook.app.dao.GroupsDAO;
import facebook.app.entities.Groups;

import java.util.List;

public class GroupsService {
    private final GroupsDAO groupsDAO = new GroupsDAO();

    public List<Groups> getAllGroups() { return groupsDAO.getAllGroups(); }

    public void createGroup (Groups groups) { groupsDAO.addGroup(groups);}

    public void leaveGroup (int groupId, int friendId) { groupsDAO.leaveGroup(groupId, friendId);}

}
