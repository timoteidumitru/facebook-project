package facebook.app.dao;

import facebook.app.entities.Profile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProfileDAO {
    private static final String FILE_NAME = "src/main/resources/profile.txt";

    public List<Profile> readProfile() {
        List<Profile> profileList = new ArrayList<>();
        // Use a File object to handle file path
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.err.println("File not found: " + FILE_NAME);
            return profileList;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(";");
                int id = Integer.parseInt(userData[0].trim());
                String name = userData[1].trim();
                String email = userData[2].trim();
                int age = Integer.parseInt(userData[3].trim());
                String location = userData[4].trim();
                //    boolean isLoggedIn = Boolean.parseBoolean(userData[5].trim());
                Profile profile = new Profile(id, name, email, age, location);
                profileList.add(profile);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return profileList;
    }

    public void writeProfile(List<Profile> profileList) {
        // Use a File object for writing
        File file = new File(FILE_NAME);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8))) {
            for (Profile profile : profileList) {
                writer.write(profile.getId() + ";" + profile.getName() + ";" + profile.getEmail() + ";" + profile.getAge() + ";" + profile.getLocation());
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


    public Profile getUserByEmail(String email) {
        List<Profile> profileList = readProfile();
        return profileList.stream()
                .filter(profile -> profile.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    public void writeProfile(int id, String name, String email, int age, String location) {
    }
}

