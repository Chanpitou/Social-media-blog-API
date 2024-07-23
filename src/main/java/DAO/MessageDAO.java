package DAO;

import java.sql.*;
import java.util.*;

import Model.Message;
import Util.ConnectionUtil;

public class MessageDAO {
    
    // get all messages
    public List<Message> getAllMessages() {

        // initialize an empty list that should contain message objects
        List<Message> messages = new ArrayList<Message>();
    
        try(Connection connection = ConnectionUtil.getConnection()) {

            // pre-compile the sql statement
            String sql = "SELECT * FROM message;";
            PreparedStatement ps = connection.prepareStatement(sql);

            // executing the pre-compile sql, and add all messages into the list
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Message message = new Message(
                    rs.getInt("message_id"),
                    rs.getInt("posted_by"),
                    rs.getString("message_text"),
                    rs.getInt("time_posted_epoch")
                );
                messages.add(message);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return messages;
    }

    // get a message by message id
    public Message getMessageByMessageId(int message_id) {
        try(Connection connection = ConnectionUtil.getConnection()) {

            // pre-compile the sql statement with placeholder '?' as parameters
            String sql = "SELECT * FROM message WHERE message_id = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, message_id);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Message message = new Message(
                    rs.getInt("message_id"),
                    rs.getInt("posted_by"),
                    rs.getString("message_text"),
                    rs.getInt("time_posted_epoch")
                );
                return message;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    // create a new message
    public Message createMessage(Message message) {
        return null;
    }

    // update a message
    public Message UpdateMessage(Message message) {
        return null;
    }
    
    // delete a message
    public Message DeleteMessage(Message message) {
        return null;
    }

    // get all messages of a user by account_id
    public Message getAllMessagesByAccountId(Message message) {
        return null;
    }
}
