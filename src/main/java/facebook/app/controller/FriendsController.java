package facebook.app.controller;

import facebook.app.entites.Friends;
import facebook.app.services.FriendsService;

import java.util.List;

public class FriendsController {
    private final FriendsService friendsService = new FriendsService();

    public FriendsController(FriendsService friendsService) {
    }

    public void sendFriendRequest(int userId, int friendId) {
        friendsService.sendFriendRequest(userId, friendId);
    }

    public List<Friends> getFriendsOfUser(int userId) {
        return friendsService.getFriendsOfUser(userId);
    }

    public void removeFriend(int userId, int friendId) {
        friendsService.removeFriend(userId, friendId);
    }

}
