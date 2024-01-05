package facebook.app.ui;
import facebook.app.controller.MessageController;
import facebook.app.controller.UserController;
import facebook.app.entities.Message;
import facebook.app.entities.User;
import facebook.app.services.UserService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class MessageUI {
    private final MessageController messageController;
    private final UserController userController = new UserController();
    private final Scanner scanner;

    public MessageUI(MessageController messageController, Scanner scanner) {
        this.messageController = messageController;
        this.scanner = scanner;
    }

    public void startMessaging() {
        int choice;
        do {
            System.out.println("    Messaging management, please chose one of the following options:");
            System.out.println("1. View Messages");
            System.out.println("2. Send a Message");
            System.out.println("0. Back to Main Menu");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    // View messages (implement as needed)
                    viewMessages();
                    break;
                case 2:
                    // Send a message
                    sendMessage();
                    break;
                case 0:
                    System.out.println("Returning to Main Menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    private void viewMessages() {
        List<Message> messages = messageController.getMessages();
        UserController users = userController;

        if (messages.isEmpty()) {
            System.out.println("No messages to display.");
        } else {
            System.out.println("Messages:");
            for (Message message : messages) {
                System.out.println("From: " + users.getUserByID(message.getFrom_user_id()).getEmail().split("@")[0]);
                System.out.println("To: " + users.getUserByID(message.getTo_user_id()).getEmail().split("@")[0]);
                System.out.println("Date: " + message.getDate());
                System.out.println("Message: " + message.getMessage());
                System.out.println("------------------------------");
            }
        }
    }

    private void sendMessage() {
        UserController userController = new UserController();
        UserService userService = new UserService();
        int fromUserId = (int) userService.getCurrentUserId();

        // Display a list of users for the recipient selection
        List<User> users = userController.getAllUsers();
        System.out.println("Select a recipient you wanna send a message by User ID:");

        for (User user : users) {
            if (user.getUserId() == fromUserId){
                //skip current logged user!
                //System.out.println("User ID: " + user.getUserId() + ", Name: " + user.getEmail().split("@")[0]);
            }else {
                System.out.println("User ID: " + user.getUserId() + ", Name: " + user.getEmail().split("@")[0]);
            }
        }

        int recipientChoice = scanner.nextInt();
        scanner.nextLine();

        boolean recipientFound = false;
        int toUserId = -1;

        for (User user : users) {
            if (user.getUserId() == recipientChoice) {
                toUserId = (int) user.getUserId();
                recipientFound = true;
                break;
            }
        }

        if (!recipientFound) {
            System.out.println("Invalid choice. Message not sent.");
            return;
        }

        // Automatically set the current date
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy HH:mm", Locale.ENGLISH);
        String date = dateFormat.format(currentDate);

        System.out.println("Enter your message:");
        String messageText = scanner.nextLine();

        // Call the controller to send the message
        messageController.sendMessage(fromUserId, toUserId, date, messageText);
        System.out.println("Message sent successfully!");
    }
}
