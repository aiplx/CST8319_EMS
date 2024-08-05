package dao.message;

import dao.employee.EmployeeDAOImpl;
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
    private EmployeeDAOImpl employeeDAO;

    public MessageDAOImpl() {
        this.employeeDAO = new EmployeeDAOImpl();
    }

    @Override
    public void saveMessage(Message message) {
        String query = "INSERT INTO message(sender_id, receiver_id, title, message_content, timestamp) VALUES(?,?,?,?,?)";
        try (Connection conn = DatabaseUtil.getInstance().getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, message.getSenderId());
            stmt.setLong(2, message.getReceiverId());
            stmt.setString(3, message.getTitle());
            stmt.setString(4, message.getMessageContent());
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
                message.setMessageId(rs.getLong("message_id"));
                message.setSenderId(rs.getLong("sender_id"));
                message.setReceiverId(rs.getLong("receiver_id"));
                message.setTitle(rs.getString("title"));
                message.setMessageContent(rs.getString("message_content"));
                message.setTimestamp(rs.getTimestamp("timestamp"));
                int isReadValue = rs.getInt("is_read");
                message.setRead(isReadValue == 1);
                messages.add(message);
            }
            for (Message message : messages) {
                String senderName = employeeDAO.getEmployeeFullNameById((int) message.getSenderId());
                String recipientName = employeeDAO.getEmployeeFullNameById((int) message.getReceiverId());
                message.setSenderName(senderName);
                message.setRecieverName(recipientName);
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

    @Override
    public int getUnreadMessages(long id) {
        int numOfUnreadMessage = 0;
        String query = "SELECT COUNT(*) as count FROM message WHERE receiver_id=? AND is_read = 0";
        try (Connection conn = DatabaseUtil.getInstance().getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                numOfUnreadMessage = rs.getInt("count");
            }

        } catch (Exception e) {
            logger.error("Error getting unread messages : {}", e.getMessage());
        }
        return numOfUnreadMessage;
    }

    @Override
    public void markAsRead(long id) {
        String query = "UPDATE message SET is_read=1 WHERE message_id=?";
        try (Connection conn = DatabaseUtil.getInstance().getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
            logger.info("Message marked as read: {}", id);

        } catch (Exception e) {
            logger.error("Error marking message as read: MessageID: {}  Error: {}", id, e.getMessage());
        }
    }
}
