package facebook.app.services;

import facebook.app.entitites.AppPost;
import facebook.app.homefeedservices.PostService;
import facebook.app.model.user.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PostServiceImpl implements PostService {

    @Override
    public AppPost getLatestPost(User user) {

        long latest = 0L;
        String latestMessage = null;

        try {
            List<String> allLines = Files.readAllLines(Paths.get("C:\\code\\facebook-project\\src\\main\\resources\\posts.txt"));
            for (String line : allLines) {
                String[] postData = line.split(",");
                        if(String.valueOf(user.getUserId()).equals(postData[0]))
                        {
                            int postDate = Integer.parseInt(postData[1].trim());
                            if(postDate > latest)
                            {
                                latest = postDate;
                                latestMessage = postData[2];

                            }
                        }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(latestMessage == null)
        {
            return null;
        }
        else
        {
            AppPost latestPost = new AppPost(user,latestMessage,latest);
            return latestPost;
        }
    }

    @Override
    public List<AppPost> getAllPostsFromUser(User user) {
        return null;
    }

    @Override
    public List<AppPost> getLatestPostsFromUser(User user, int posts) {
        return null;
    }
}
