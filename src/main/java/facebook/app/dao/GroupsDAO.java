package facebook.app.dao;

import facebook.app.entities.Groups;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class GroupsDAO {
    
    private static final String FILE_NAME = "groups.txt";

    public List<Groups> getAllGroups() {
        List<Groups> groupsList = new ArrayList<>();

        try (InputStream is = getClass().getClassLoader().getResourceAsStream(FILE_NAME)) {
            if (is == null) {
                throw new FileNotFoundException("Resource not found: " + FILE_NAME);
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                String line;
                reader.readLine();
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(";");
                    Groups groups = new Groups(
                            Integer.parseInt(parts[0].trim()),
                            Integer.parseInt(parts[1].trim()),
                            parts[2].trim(),
                            parts[3].trim());
                    groupsList.add(groups);
                }
            }
            } catch (IOException e) {
            e.printStackTrace();;
        }
        return groupsList;
        }

    public void addGroup (Groups groups) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(writeToFile(), true))){
            writer.write(groups.getGroupId() + ";" + groups.getUserId() + ";" +groups.getGroupName() + ";" + groups.getGroupDescription());
            writer.newLine();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public Optional<Groups> getGroupById(int groupId) {
        List<Groups> groupsList = getAllGroups();
        return groupsList.stream()
                .filter(group -> group.getGroupId() == groupId)
                .findFirst();
    }

    private InputStream readFromFile() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(GroupsDAO.FILE_NAME);
        if (inputStream == null) {
            throw new IllegalArgumentException("File not found: " + GroupsDAO.FILE_NAME);
        }
        return inputStream;
    }

    private File writeToFile() throws URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(Objects.requireNonNull(classLoader.getResource(GroupsDAO.FILE_NAME)).toURI());
    }
}
