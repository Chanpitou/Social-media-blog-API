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
    public List<Message> getAllMessages(Message message){
        return messageDAO.getAllMessages();
    }

    // get a message by message_id
    public Message getMessageById(int message_id){
        return messageDAO.getMessageByMessageId(message_id);
    }

    // create a new message
    public Message createNewMessage(Message message) {
        boolean created = messageDAO.createMessage(message);
        if (created){
            return messageDAO.getMessageByMessageId(message.getMessage_id());
        }
        return null;
    }

    // update a message
    public Message updateExistingMessage(int message_id, String message_text){
        boolean updated = messageDAO.UpdateMessage(message_id, message_text);
        if (updated) {
            return messageDAO.getMessageByMessageId(message_id);
        }
        return null;
    }

    // delete a message
    public Message deleteExistingMessage(int message_id){
        Message deleted_message = messageDAO.getMessageByMessageId(message_id);
        boolean deleted = messageDAO.DeleteMessage(message_id);
        if (deleted) {
            return deleted_message;
        }
        return null;
    }

    // get all messages of a user by account id
    public List<Message> getAllUserMessages(int account_id){
        return messageDAO.getAllMessagesByAccountId(account_id);
    }
}
