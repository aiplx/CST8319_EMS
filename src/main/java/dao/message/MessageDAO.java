package dao.message;

import dto.Message;

import java.util.List;

public interface MessageDAO {
    void saveMessage(Message message);
    List<Message> getAllUserMessages(long id);
    long getSenderID(Long messageID);
    long getReceiverID(long receiverID);
    String getEmployeeFullName(long employeeID);
    int getUnreadMessages(long id);
    void markAsRead(long id);

}
