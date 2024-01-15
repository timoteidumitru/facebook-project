package facebook.app.dao;

import facebook.app.controller.GroupController;
import facebook.app.entities.Friends;
import facebook.app.entities.Groups;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import javax.imageio.IIOException;
import javax.swing.*;
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getResourceFile(), true))){
            writer.write(groups.getGroupId() + ";" + groups.getUserId() + ";" +groups.getGroupName() + ";" + groups.getGroupDescription());
            writer.newLine();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void leaveGroup(int groupId, int userId) {
        List<Groups> groupsList = getAllGroups();
        List<Groups> updateGroupMembers = groupsList.stream()
                .filter(groups -> (!((groups.getGroupId()) == groupId)) && (groups.getUserId() == userId))
                .collect(Collectors.toList());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getResourceFile(), false))){
            for (Groups groups : updateGroupMembers) {
                writer.write(groups.getGroupId() + ";" + groups.getUserId() + ";" + groups.getGroupName() + ";" + groups.getGroupDescription());
                writer.newLine();
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

    }

    private File getResourceFile() throws URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(Objects.requireNonNull(classLoader.getResource(FILE_NAME)).toURI());
    }
}
