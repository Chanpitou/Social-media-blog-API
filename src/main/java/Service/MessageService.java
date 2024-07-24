package Service;

import java.util.List;

import DAO.MessageDAO;
import Model.Message;

public class MessageService {

    private MessageDAO messageDAO;

    // contructors
    public MessageService(){
        this.messageDAO = new MessageDAO();
    }
    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }

    // get all messages
    public List<Message> getAllMessages(){
        return messageDAO.getAllMessages();
    }

    // get a message by message_id
    public Message getMessageById(int message_id){
        return messageDAO.getMessageByMessageId(message_id);
    }

    // create a new message
    public Message createNewMessage(Message message) {
        // conditions for create new messages
        final boolean MESSAGE_BLANK = message.getMessage_text().length() == 0;
        final boolean MESSAGE_LENGTH_EXCEEDED = message.getMessage_text().length() >= 255;

        if (MESSAGE_BLANK || MESSAGE_LENGTH_EXCEEDED) {
            return null;
        } else {
            return messageDAO.createMessage(message);
        }
    }

    // update a message
    public Message updateExistingMessage(int message_id, Message message){
        // conditions for updating a message
        final boolean MESSAGE_BLANK = message.getMessage_text().length() == 0;
        final boolean MESSAGE_LENGTH_EXCEEDED = message.getMessage_text().length() >= 255;
        final boolean MESSAGE_EXIST = messageDAO.getMessageByMessageId(message_id) != null;

        if (MESSAGE_BLANK || MESSAGE_LENGTH_EXCEEDED) {
            return null;
        }
        if (MESSAGE_EXIST) {
            messageDAO.UpdateMessage(message_id, message);
            return messageDAO.getMessageByMessageId(message_id);
        } else {
            return null;
        }
    }

    // delete a message
    public Message deleteExistingMessage(int message_id){
        Message message = messageDAO.getMessageByMessageId(message_id);
        // condition for deleting a message
        final boolean MESSAGE_EXIST = messageDAO.getMessageByMessageId(message_id) != null;

        if (MESSAGE_EXIST) {
            messageDAO.DeleteMessage(message_id);
            return message;
        } else {
            return null;
        }
    }

    // get all messages of a user by account id
    public List<Message> getAllUserMessages(int account_id){
        return messageDAO.getAllMessagesByAccountId(account_id);
    }
}
