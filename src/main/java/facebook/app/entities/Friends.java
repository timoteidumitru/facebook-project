package facebook.app.entities;

public class Friends {
    private int userId;
    private int friendId;
    private String friendNameID;

    public Friends(int userId, int friendId, String friendNameID) {
        this.userId = userId;
        this.friendId = friendId;
        this.friendNameID = friendNameID;
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    public String getFriendNameID() {
        return friendNameID;
    }

    public void setFriendNameID(String friendNameID) {
        this.friendNameID = friendNameID;
    }

    // Optionally override toString(), equals() and hashCode() methods
}
