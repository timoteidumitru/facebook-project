package facebook.app.controller;

import facebook.app.model.messages.Message;
import facebook.app.services.MessageService;
import facebook.app.dao.MessageDAO;
import facebook.app.ui.message.MessageUI;

import java.util.List;

public class MessageController {
    private final MessageService messageService;
    private final MessageDAO messageDAO;
    private MessageUI messageUI;

    public MessageController(MessageService messageService, MessageDAO messageDAO) {
        this.messageService = messageService;
        this.messageDAO = messageDAO;
    }

    public void setMessageUI(MessageUI messageUI) {
        this.messageUI = messageUI;
    }

    // Example method to handle message creation and persistence
    public void sendMessage(int fromUserId, int toUserId, String date, String messageText) {
        // Create a Message object
        Message message = new Message(fromUserId, toUserId, date, messageText);

        // Perform business logic using the service
        messageService.processMessage(message);

        // Persist the message using the DAO
        messageDAO.saveMessage(message);
    }

    // Example method to start messaging UI
    public void startMessaging() {
        messageUI.startMessaging();
    }

    // Retrieve messages from the DAO
    public List<Message> getMessages() {
        return messageDAO.readMessages();
    }
}
