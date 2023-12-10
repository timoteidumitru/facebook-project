package facebook.app.ui.message;
import facebook.app.controller.MessageController;
import facebook.app.model.messages.Message;

import java.util.List;
import java.util.Scanner;

public class MessageUI {
    private final MessageController messageController;
    private final Scanner scanner;

    public MessageUI(MessageController messageController, Scanner scanner) {
        this.messageController = messageController;
        this.scanner = scanner;
    }

    public void startMessaging() {
        int choice;
        do {
            System.out.println("    Messaging Options:");
            System.out.println("1. Send a Message");
            System.out.println("2. View Messages");
            System.out.println("0. Back to Main Menu");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    // Send a message
                    sendMessage();
                    break;
                case 2:
                    // View messages (implement as needed)
                    viewMessages();
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

        if (messages.isEmpty()) {
            System.out.println("No messages to display.");
        } else {
            System.out.println("Messages:");
            for (Message message : messages) {
                System.out.println("From: " + message.getFrom_user_id());
                System.out.println("To: " + message.getTo_user_id());
                System.out.println("Date: " + message.getDate());
                System.out.println("Message: " + message.getMessage());
                System.out.println("------------------------------");
            }
        }
    }

    private void sendMessage() {
        System.out.println("Enter sender's user ID:");
        int fromUserId = scanner.nextInt();

        System.out.println("Enter recipient's user ID:");
        int toUserId = scanner.nextInt();

        System.out.println("Enter message date:");
        String date = scanner.next();

        System.out.println("Enter your message:");
        scanner.nextLine(); // Consume the newline character
        String messageText = scanner.nextLine();

        // Call the controller to send the message
        messageController.sendMessage(fromUserId, toUserId, date, messageText);

        System.out.println("Message sent successfully!");
    }

}
