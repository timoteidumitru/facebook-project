package facebook.app.dao;

import facebook.app.entitites.Message;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MessageDAO {
    private static final String FILE_NAME = "messages.txt";

    public void saveMessage(Message message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getResourceFile().getAbsolutePath(), true))) {
            // Append the message details to the file with a semicolon separator
            writer.write(String.format("%d`%d`%s`%s;%n",
                    message.getFrom_user_id(), message.getTo_user_id(),
                    message.getDate(), message.getMessage()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    public List<Message> readMessages() {
        List<Message> messageList = new ArrayList<>();

        try (InputStream inputStream = getResourceAsStream()) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    String[] messageData = line.split(";");
                    for (String messagePart : messageData) {
                        String[] fields = messagePart.split("`");
                        if (fields.length == 4) {
                            int fromUserId = Integer.parseInt(fields[0].trim());
                            int toUserId = Integer.parseInt(fields[1].trim());
                            String date = fields[2].trim();
                            String messageText = fields[3].trim();
                            Message message = new Message(fromUserId, toUserId, date, messageText);
                            messageList.add(message);
                        }
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return messageList;
    }

    private InputStream getResourceAsStream() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(MessageDAO.FILE_NAME);
        if (inputStream == null) {
            throw new IllegalArgumentException("File not found: " + MessageDAO.FILE_NAME);
        }
        return inputStream;
    }

    private File getResourceFile() throws URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(Objects.requireNonNull(classLoader.getResource(MessageDAO.FILE_NAME)).toURI());
    }
}
