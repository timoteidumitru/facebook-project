package facebook.app.dao;
import facebook.app.model.User;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserDAO {

    private static final String FILE_NAME = "users.txt";

    public List<User> readUsers() {
        List<User> userList = new ArrayList<>();

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FILE_NAME)) {
            assert inputStream != null;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    String[] userData = line.split(";");
                    int userId = (int) Long.parseLong(userData[0].trim());
                    String email = userData[1].trim();
                    String password = userData[2].trim();
                    User user = new User(userId, email, password);
                    userList.add(user);
                }

            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void writeUsers(List<User> userList) {
        try (OutputStream outputStream = new FileOutputStream(getFilePath());
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8))) {

            for (User user : userList) {
                writer.write(user.getUserId() + ";" + user.getEmail() + ";" + user.getPassword());
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getFilePath() {
        try {
            return Paths.get(Objects.requireNonNull(getClass().getClassLoader().getResource(FILE_NAME)).toURI()).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public User getUserById(long userId) {
        List<User> userList = readUsers();
        return userList.stream()
                .filter(user -> user.getUserId() == userId)
                .findFirst()
                .orElse(null);
    }

    public User getUserByEmail(String email) {
        List<User> userList = readUsers();
        return userList.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }
}