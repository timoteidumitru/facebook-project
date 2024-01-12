package facebook.app.dao;

import facebook.app.entities.AppPost;
import facebook.app.homefeedservicesinterfaces.PostServiceDAO;
import facebook.app.entities.User;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class UserPostsDAO implements PostServiceDAO {
    private static final String DATABASE_FILE_PATH = "posts.txt";

    public UserPostsDAO() {
    }

    @Override
    public AppPost getLatestPost(User user) {
        List<AppPost> latestPosts = getRecentPostsFromUser(user, 1);
        if (latestPosts.isEmpty()) {
            return null;
        } else {
            return latestPosts.get(0);
        }
    }

    @Override
    public List<AppPost> getAllPostsFromCurrentUser(User user) {
        List<AppPost> postsFromUser = new ArrayList<>();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(DATABASE_FILE_PATH);
             InputStreamReader streamReader = new InputStreamReader(inputStream);
             BufferedReader reader = new BufferedReader(streamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] postData = line.split(",");
                String userIdString = postData[0].trim();
                if (userIdString.equals(String.valueOf(user.getUserId()))) {
                    long timestamp = 0;
                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("\"dd/MM/yyyy HH:mm:ss\"");
                        Date date = dateFormat.parse(postData[1].trim());
                        timestamp = date.getTime();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    AppPost appPost = new AppPost(user, postData[2], timestamp);
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
        List<AppPost> latestPostsFromUser = new ArrayList<>();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(DATABASE_FILE_PATH);
             InputStreamReader streamReader = new InputStreamReader(inputStream);
             BufferedReader reader = new BufferedReader(streamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] postData = line.split(",");
                if (String.valueOf(user.getUserId()).equals(postData[0])) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("\"dd/MM/yyyy HH:mm:ss\"");
                    LocalDateTime dateTime = LocalDateTime.parse(postData[1], formatter);
                    AppPost appPost = new AppPost(user, postData[2], dateTime.toInstant(ZoneOffset.UTC).toEpochMilli());
                    latestPostsFromUser.add(appPost);
                }
            }
            Collections.sort(latestPostsFromUser, Comparator.comparing(AppPost::getTimePosted).reversed());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return latestPostsFromUser.stream().limit((long) posts).collect(Collectors.toList());
    }

    @Override
    public void createPost(AppPost appPost) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getResourceFile().getAbsolutePath(), true))) {

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

    private File getResourceFile() {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(Objects.requireNonNull(classLoader.getResource(DATABASE_FILE_PATH)).getFile());
    }
}
