package facebook.app.dao;

import facebook.app.entities.AppPost;
import facebook.app.homefeedservicesinterfaces.PostServiceDAO;
import facebook.app.entities.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class UserPostsDAO implements PostServiceDAO {


    public UserPostsDAO() {
    }

    @Override
    public AppPost getLatestPost(User user) {
        return null;
    }


    /**
     * Returns a list of AppPost
     *
     * @param user
     * @return a list of all AppPost associated to user
     */
    @Override
    public List<AppPost> getAllPostsFromCurrentUser(User user) {
        List<AppPost> postsFromUser = new ArrayList<>();
        try {
            List<String> allLines = Files.readAllLines(Paths.get("C:\\code\\project\\facebook-project\\src\\main\\resources\\posts.txt"));
            for (String line : allLines) {
                String[] postData = line.split(",");
                String userIdString = postData[0].trim();

                System.out.println("User ID from file: " + userIdString);
                System.out.println("User ID from object: " + user.getUserId());

                if (userIdString.equals(String.valueOf(user.getUserId()))) {
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
    public List<AppPost> getRecentPostsFromUser(User user, int posts) {
        Calendar rightNow = Calendar.getInstance();
        List<AppPost> latestPostsFromUser = new ArrayList<>();
        try {
            List<String> allLines = Files.readAllLines(Paths.get("C:\\code\\project\\facebook-project\\src\\main\\resources\\posts.txt"));
            for (String line : allLines) {
                String[] postData = line.split(",");
                if (String.valueOf(user.getUserId()).equals(postData[0])) {

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("\"dd/MM/yyyy HH:mm:ss\"");
                    LocalDateTime dateTime = LocalDateTime.parse(postData[1], formatter);

                    AppPost appPost = new AppPost(user, postData[2], dateTime.toInstant(ZoneOffset.UTC).toEpochMilli());
                    latestPostsFromUser.add(appPost);
                }
            }
            Collections.sort(latestPostsFromUser, new Comparator<AppPost>() {
                @Override
                public int compare(AppPost o1, AppPost o2) {
                    return o1.getTimePosted().compareTo(o2.getTimePosted());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return latestPostsFromUser.stream().limit((long) posts).collect(Collectors.toList());
    }

    private static final String DATABASE_FILE_PATH = "C:\\code\\project\\facebook-project\\src\\main\\resources\\posts.txt";

    @Override
    public void createPost(AppPost appPost) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATABASE_FILE_PATH, true))) {

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String formattedDate = sdf.format(new Date((Long) appPost.getTimePosted()));

            String postData = String.format("%d,\"%s\",\"%s\"",
                    appPost.getUser().getUserId(),
                    formattedDate,
                    appPost.getContent());

            writer.write(postData);
            writer.newLine(); // Add a newline to separate posts
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}

