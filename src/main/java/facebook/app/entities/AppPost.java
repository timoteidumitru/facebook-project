package facebook.app.entities;

import facebook.app.entities.User;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class AppPost {
    private Long id;
    private User user;
    private Long timePosted;
    private String content;
    private String formattedDate;

    public AppPost(User user, String content, Long timePosted) {
        this.user = user;
        this.content = content;
        this.timePosted = timePosted;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        this.formattedDate = sdf.format(new Date(timePosted));
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

    @Override
    public String toString() {
        return "AppPost{" +
                "id=" + id +
                ", user=" + user +
                ", timePosted=" + timePosted +
                ", content='" + content + '\'' +
                '}';
    }

    public  Long getTimePosted() {
        return timePosted;
    }


}
