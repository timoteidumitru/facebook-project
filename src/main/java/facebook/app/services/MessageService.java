package facebook.app.services;
import facebook.app.model.messages.Message;

public class MessageService {

    public void processMessage(Message message) {
        validateSenderAndRecipient(message);
        validateMessageLength(message);
    }

    private void validateSenderAndRecipient(Message message) {
        int senderId = message.getFrom_user_id();
        int recipientId = message.getTo_user_id();

        if (senderId <= 0 || recipientId <= 0) {
            throw new IllegalArgumentException("Invalid sender or recipient ID");
        }
    }

    private void validateMessageLength(Message message) {
        String messageContent = message.getMessage();

        if (messageContent.length() > 250) {
            throw new IllegalArgumentException("Message length exceeds the maximum allowed limit");
        }

    }
}
