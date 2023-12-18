package facebook.app.model.user;

import facebook.app.entitites.AppPost;

public class User {
    private long user_id;
    private String email;
    private String password;

    // Constructor
    public User(long user_id, String email, String password) {
        this.user_id = user_id;
        this.email = email;
        this.password = password;
    }

    // Getter and Setter for user_id
    public long getUserId() {
        return user_id;
    }

    public void setUserId(long user_id) {
        this.user_id = (int) user_id;
    }

    // Getter and Setter for email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter and Setter for password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addPost(AppPost post) {
    }

    public void sendMessage(User user, String message) {

    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", email='" + email + '\'' +
                ", password=obfusated' " +
                '}';
    }
}

