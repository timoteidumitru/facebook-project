package facebook.app.entities;

import java.util.ArrayList;
import java.util.List;

public class Groups {

    private final int groupId;

    private int userId;

    private String groupName;

    private String groupDescription;

    public Groups(int groupId, int userId, String groupName, String groupDescription) {
        this.groupId = groupId;
        this.userId = userId;
        this.groupName = groupName;
        this.groupDescription = groupDescription;
    }
    //Getters and Setters


    public int getGroupId() {
        return groupId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }
}
