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

/**
 * The {@code MessageDAOImpl} class is an implementation of the {@link MessageDAO} interface.
 * It provides methods for saving, retrieving, and managing the status of {@link Message} objects
 * in the database.
 */
public class MessageDAOImpl implements MessageDAO {
    private static final Logger logger = LogManager.getLogger(MessageDAOImpl.class);
    private EmployeeDAOImpl employeeDAO;

    /**
     * Constructs a new {@code MessageDAOImpl} instance.
     * Initializes the {@code EmployeeDAOImpl} instance used to retrieve employee information.
     */
    public MessageDAOImpl() {
        this.employeeDAO = new EmployeeDAOImpl();
    }
    /**
     * Saves a {@link Message} object to the database.
     * @param message The {@link Message} object to be saved.
     */
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


    /**
     * Retrieves all messages for a user identified by their ID.
     * @param id The ID of the user for which messages will be retrieved.
     * @return A list of {@link Message} objects representing the user's messages.
     */
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


    /**
     * Retrieves the number of unread messages for a user identified by their ID.
     * @param id The ID of the user for which to return the number of unread messages.
     * @return An integer value for the number of unread messages.
     */
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

    /**
     * Marks a {@link Message} object as read, and saves the changes in the database.
     * @param id The ID of the message to be marked as read.
     */
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