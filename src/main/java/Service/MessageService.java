package Service;

import java.util.List;

import DAO.AccountDAO;
import DAO.MessageDAO;
import Model.Account;
import Model.Message;

public class MessageService {

    private MessageDAO messageDAO;
    private AccountDAO accountDAO;
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
        if (message.getMessage_text().length() == 0 || message.getMessage_text().length() >= 255) {
            return null;
        } else {
            return messageDAO.createMessage(message);
        }
    }

        

    // update a message
    public Message updateExistingMessage(int message_id, Message message){
        if (message.getMessage_text().length() == 0 || message.getMessage_text().length() >= 255) {
            return null;
        }
        if (messageDAO.getMessageByMessageId(message_id) != null) {
            messageDAO.UpdateMessage(message_id, message);
            return messageDAO.getMessageByMessageId(message_id);
        } else {
            return null;
        }
        
    }

    // delete a message
    public Message deleteExistingMessage(int message_id){
        Message message = messageDAO.getMessageByMessageId(message_id);
        if (messageDAO.getMessageByMessageId(message_id) != null) {
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
