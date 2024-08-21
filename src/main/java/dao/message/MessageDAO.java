package dao.message;

import dto.Message;

import java.util.List;

/**
 * The {@code MessageDAO} interface defines the contract for data access operations related to messages.
 * Implementations of this interface provide methods for saving, retrieval, and changing the status
 * of {@link Message} objects from the database.
 */
public interface MessageDAO {
    /**
     * Saves a message to the database.
     * @param message The message to be saved.
     */
    void saveMessage(Message message);

    /**
     * Retrieves all messages for a user identified by their ID.
     * @param id The ID of the user for which messages will be retrieved.
     * @return A list of {@link Message} objects representing the user's messages.
     */
    List<Message> getAllUserMessages(long id);

    /**
     * Retrieves the number of unread messages a user identified by their ID.
     * @param id The ID of the user for which to return the number of unread messages.
     * @return An integer value for the number of unread messages.
     */
    int getUnreadMessages(long id);

    /**
     * Marks a message as read.
     * @param id The ID of the message to be marked as read.
     */
    void markAsRead(long id);

}