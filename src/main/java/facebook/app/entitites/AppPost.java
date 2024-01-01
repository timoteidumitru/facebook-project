package facebook.app.entitites;

import facebook.app.entites.User;
public class AppPost {
    private Long id;

    private User user;

    private Long timePosted;
    private String content;

    public AppPost(User user, String content, Long timePosted) {
        this.user = user;
        this.content = content;
        this.timePosted = timePosted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "AppPost{" +
                "id=" + id +
                ", user=" + user +
                ", timePosted=" + timePosted +
                ", content='" + content + '\'' +
                '}';
    }
}
