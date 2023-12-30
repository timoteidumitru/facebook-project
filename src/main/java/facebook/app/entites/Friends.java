package facebook.app.entites;

public class Friends {
    private final int id;
    private final int userId;
    private final int friendId;

    public Friends(int id, int userId, int friendId) {
        this.id = id;
        this.userId = userId;
        this.friendId = friendId;
    }


    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getFriendId() {
        return friendId;
    }

}
