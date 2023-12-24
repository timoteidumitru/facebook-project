package facebook.app.services;

import facebook.app.dao.FriendsDAO;
import facebook.app.entites.Friends;

import java.util.List;
import java.util.stream.Collectors;

public class FriendsService {
    private final FriendsDAO friendsDAO = new FriendsDAO();

    public FriendsService() {
    }

    public void sendFriendRequest(int userId, int friendId) {
        Friends newFriend = new Friends(getNextId(), userId, friendId);
        friendsDAO.addFriend(newFriend);
    }

    public void removeFriend(int userId, int friendId) {
        // Removing a friend involves deleting the friendship from the list
        friendsDAO.removeFriend(userId, friendId);
    }

    public List<Friends> getFriendsOfUser(int userId) {
        // Retrieving a user's friends involves filtering the list for ACCEPTED status
        return friendsDAO.getAllFriends().stream()
                .filter(friend -> (friend.getUserId() == userId || friend.getFriendId() == userId))
                .collect(Collectors.toList());
    }

    private int getNextId() {
        // This method assumes that the ID is simply the next available integer.
        List<Friends> allFriends = friendsDAO.getAllFriends();
        return allFriends.stream().mapToInt(Friends::getId).max().orElse(0) + 1;
    }
}
