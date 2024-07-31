package dao.message;

import dto.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class MessageDAOImpl implements MessageDAO {
    private static final Logger logger = LogManager.getLogger(MessageDAOImpl.class);

    @Override
    public void saveMessage(Message message) {
        String query = "INSERT INTO message(sender_id, receiver_id, title, message_content, timestamp) VALUES(?,?,?,?,?)";
        try (Connection conn = DatabaseUtil.getInstance().getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, message.getSenderId());
            stmt.setLong(2, message.getReceiverId());
            stmt.setString(3, message.getTitle());
            stmt.setString(5, message.getMessageContent());
            stmt.setTimestamp(5, new Timestamp(message.getTimestamp().getTime()));
            stmt.executeUpdate();

            logger.info("Message saved to database: {}", message);

        } catch (Exception e) {
            logger.error("Error saving message : {}", e.getMessage());
        }

    }


    @Override
    public List<Message> getAllUserMessages(long id) {
        List<Message> messages = new ArrayList<>();
        String query = "SELECT * FROM message WHERE sender_id=? OR receiver_id=?";
        try (Connection conn = DatabaseUtil.getInstance().getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, id);
            stmt.setLong(2, id);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Message message = new Message();
                message.setSenderId(rs.getLong("sender_id"));
                message.setReceiverId(rs.getLong("receiver_id"));
                message.setTitle("title");
                message.setMessageContent(rs.getString("message_content"));
                message.setTimestamp(rs.getTimestamp("timestamp"));
                message.setSenderName(rs.getString("first_name" + " " + rs.getString("last_name")));
                messages.add(message);
            }
        } catch (Exception e) {
            logger.error("Error getting all messages : {}", e.getMessage());
        }
        return messages;
    }


    @Override
    public long getSenderID(Long messageID) {
        long senderID = 0;
        String query = "SELECT sender_id FROM message WHERE message_id=?";
        try (Connection conn = DatabaseUtil.getInstance().getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, messageID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                senderID = rs.getLong("sender_id");
            }
        } catch (Exception e) {
            logger.error("Error getting sender id : {}", e.getMessage());
        }
        return senderID;
    }

    @Override
    public long getReceiverID(long messageID) {
        long receiverID = 0;
        String query = "SELECT receiver_id FROM message WHERE message_id=?";
        try (Connection conn = DatabaseUtil.getInstance().getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, messageID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                receiverID = rs.getLong("receiver_id");
            }
        } catch (Exception e) {
            logger.error("Error getting receiver id : {}", e.getMessage());
        }

        return receiverID;
    }

    @Override
    public String getEmployeeFullName(long employeeID) {
        String fullName = "";
        String query = "SELECT first_name, last_name FROM employee WHERE employee_id=?";
        try (Connection conn = DatabaseUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, employeeID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                fullName = rs.getString("first_name") + " " + rs.getString("last_name");
            }
        } catch (Exception e) {
            logger.error("Error getting full name : {}", e.getMessage());
        }
        return fullName;
    }
}
