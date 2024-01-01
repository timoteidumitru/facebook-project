package facebook.app.services;

import facebook.app.entitites.AppPost;
import facebook.app.homefeedservices.PostService;
import facebook.app.entites.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class PostServiceImpl implements PostService {

    @Override
    public AppPost getLatestPost(User user) {

        long latest = 0L;
        String latestMessage = null;

        try {
            List<String> allLines = Files.readAllLines(Paths.get("C:\\code\\facebook-project\\src\\main\\resources\\posts.txt"));
            for (String line : allLines) {
                String[] postData = line.split(",");
                if (String.valueOf(user.getUserId()).equals(postData[0])) {
                    int postDate = Integer.parseInt(postData[1].trim());
                    if (postDate > latest) {
                        latest = postDate;
                        latestMessage = postData[2];

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (latestMessage == null) {
            return null;
        } else {
            AppPost latestPost = new AppPost(user, latestMessage, latest);
            return latestPost;
        }
    }

    /**
     * Returns a list of AppPost
     * @param user
     * @return a list of all AppPost associated to user
     */
    @Override
    public List<AppPost> getAllPostsFromUser(User user) {
        List<AppPost> postsFromUser = new ArrayList<>();
        try {
            List<String> allLines = Files.readAllLines(Paths.get("C:\\code\\facebook-project\\src\\main\\resources\\posts.txt"));
            for (String line : allLines) {
                String[] postData = line.split(",");
                if (String.valueOf(user.getUserId()).equals(postData[0])) {
                   AppPost appPost = new AppPost(user, postData[2], Long.parseLong(postData[1].trim()));
                   postsFromUser.add(appPost);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return postsFromUser;
    }


        @Override
        public List<AppPost> getLatestPostsFromUser (User user,int posts){
             Calendar rightNow = Calendar.getInstance();
            int hour = rightNow.get(Calendar.HOUR_OF_DAY);
            List<AppPost> latestPostsFromUser = new ArrayList<>();
            try {
                List<String> allLines = Files.readAllLines(Paths.get("C:\\code\\facebook-project\\src\\main\\resources\\posts.txt"));
                for (String line : allLines) {
                    String[] postData = line.split(",");
                    if (String.valueOf(user.getUserId()).equals(postData[0])) {
                        int hourValue = Integer.parseInt(postData[1]);
                        if(hourValue <= hour && hourValue >= hour-2)
                        {
                        AppPost appPost = new AppPost(user, postData[2], Long.parseLong(postData[1].trim()));
                        latestPostsFromUser.add(appPost);
                    }}
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        return latestPostsFromUser;
        }
    }

