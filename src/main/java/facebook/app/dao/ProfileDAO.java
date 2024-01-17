package facebook.app.dao;

import facebook.app.entities.Profile;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProfileDAO {
    private final List<Profile> profileList = new ArrayList<>();
    private static final String FILE_NAME = "profiles.txt";

    public List<Profile> readProfile() {
        try (InputStream inputStream = readFromFile();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(";");
                int id = Integer.parseInt(userData[0].trim());
                String name = userData[1].trim();
                String email = userData[2].trim();
                int age = Integer.parseInt(userData[3].trim());
                String location = userData[4].trim();

                Profile profile = new Profile(id, name, email, age, location);
                profileList.add(profile);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return profileList;
    }

    public void writeProfile(Profile profile) {
        try {
            File file = getFileFromResources();
            // Use Files.newBufferedWriter for appending text to an existing file
            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(file.toURI()), StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
                writer.write(profile.getId() + ";" + profile.getName() + ";" + profile.getEmail() + ";" + profile.getAge() + ";" + profile.getLocation());
                writer.newLine();
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void editProfile(Profile updatedProfile) {
        List<Profile> profiles = readProfile();
        boolean profileUpdated = false;
        try {
            File file = getFileFromResources();
            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(file.toURI()), StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING)) {
                for (Profile profile : profiles) {
                    if (profile.getId() == updatedProfile.getId()) {
                        // Write the updated profile details
                        writer.write(formatProfileForWriting(updatedProfile));
                        profileUpdated = true;
                    } else {
                        // Write the original profile details
                        writer.write(formatProfileForWriting(profile));
                    }
                    writer.newLine();
                }
                if (!profileUpdated) {
                    // If the profile with the given ID was not found, add it to the file
                    writer.write(formatProfileForWriting(updatedProfile));
                    writer.newLine();
                }
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private String formatProfileForWriting(Profile profile) {
        return profile.getId() + ";" + profile.getName() + ";" + profile.getEmail() + ";" + profile.getAge() + ";" + profile.getLocation();
    }

    private File getFileFromResources() throws URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(Objects.requireNonNull(classLoader.getResource(FILE_NAME)).toURI());
    }

    private InputStream readFromFile() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(ProfileDAO.FILE_NAME);
        if (inputStream == null) {
            throw new IllegalArgumentException("File not found: " + ProfileDAO.FILE_NAME);
        }
        return inputStream;
    }
}
