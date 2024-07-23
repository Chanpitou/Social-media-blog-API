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
        Connection connection = ConnectionUtil.getConnection();

        try {
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
                    rs.getLong("time_posted_epoch")
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

        Connection connection = ConnectionUtil.getConnection();
        try {
            // pre-compile the sql statement with placeholder '?' as parameters
            String sql = "SELECT * FROM message WHERE message_id = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);

            // Setting the parameter using provided message_id
            ps.setInt(1, message_id);
            
            // execute, get result set, and return the message
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Message message = new Message(
                    rs.getInt("message_id"),
                    rs.getInt("posted_by"),
                    rs.getString("message_text"),
                    rs.getLong("time_posted_epoch")
                );
                return message;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    // create a new message
    public boolean createMessage(Message message) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            // pre-compile the sql statement with placeholder '?' as parameters
            String sql = "INSERT INTO message(posted_by, message_text, time_posted_epoch) VALUES(?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(sql);

            // Setting the parameters using the provided message object
            ps.setInt(1, message.getPosted_by());
            ps.setString(2, message.getMessage_text());
            ps.setLong(3, message.getTime_posted_epoch());

            // executing the pre-compile sql, if successful return true, else false
            boolean rs = ps.execute();
            return rs;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    // update a message
    public boolean UpdateMessage(int message_id, String message_text) {
        Connection connection = ConnectionUtil.getConnection();

        try {
            // pre-compile the sql statement with placeholder '?' as parameters
            String sql = "Update message SET message_text = ? WHERE message_id = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);

            // Setting the parameters using the provided message object
            ps.setString(1, message_text);
            ps.setInt(2, message_id);
            
            // executing the pre-compile sql, if successful return true, else false
            boolean rs = ps.execute();
            return rs;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    // delete a message
    public boolean DeleteMessage(int message_id) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "DELETE FROM message WHERE message_id = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);

            // Setting the parameters using the provided message_id
            ps.setInt(1, message_id);
            
            // executing the pre-compile sql, if successful return true, else false
            boolean rs = ps.execute();
            return rs;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    // get all messages of a user by account_id
    public List<Message> getAllMessagesByAccountId(int account_id) {
        // initialize an empty list that should contain message objects
        List<Message> user_messages = new ArrayList<Message>();
        Connection connection = ConnectionUtil.getConnection();
        try {
            // pre-compile the sql statement
            String sql = "SELECT * FROM message WHERE posted_by = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, account_id);

            // executing the pre-compile sql, and add all user messages into the list
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Message message = new Message(
                    rs.getInt("message_id"),
                    rs.getInt("posted_by"),
                    rs.getString("message_text"),
                    rs.getLong("time_posted_epoch")
                );
                user_messages.add(message);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return user_messages;
    }
}
