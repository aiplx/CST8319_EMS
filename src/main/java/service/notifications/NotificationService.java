package service.notifications;


import dao.employee.EmployeeDAOImpl;
import dao.message.MessageDAOImpl;
import dto.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.util.List;

public class NotificationService {
    private static final Logger logger = LogManager.getLogger(NotificationService.class);

    private final EmailService emailService;
    private final SmsService smsService;
    private MessageDAOImpl messageDAO;
    private EmployeeDAOImpl employeeDAO;

    public NotificationService() {
        this.emailService = new EmailService();
        this.smsService = new SmsService();
        this.messageDAO = new MessageDAOImpl();
        this.employeeDAO = new EmployeeDAOImpl();
    }

    public List<Message> getAllUserMessages(int employeeID) {
        return messageDAO.getAllUserMessages(employeeID);
    }

    public int numOfUnreadMessages(int employeeID) {
        return messageDAO.getUnreadMessages(employeeID);
    }

    public void markAsRead(int messageID) {
        messageDAO.markAsRead(messageID);
    }

    public void createNotification(int senderId, int receiverId, String title, String content) {
        Message message = new Message();
        message.setSenderId(senderId);
        message.setReceiverId(receiverId);
        message.setTitle(title);
        message.setMessageContent(content);
        message.setTimestamp(new Timestamp(System.currentTimeMillis()));

        String phone = employeeDAO.getEmployeePhoneNumber(receiverId);
        String email = employeeDAO.getEmployeeEmail(receiverId);
        String senderName = employeeDAO.getEmployeeFullNameById(senderId);
        messageDAO.saveMessage(message);

        logger.info("Employee details Email: {} Phone: {}", email, phone);


        //Send email and sms
        try {
            String contentWithHeader = "Sent from: " + senderName + "\n" + "Title: " + title + "\n \n" + content;
            smsService.sendSms(phone, contentWithHeader);
            emailService.sendEmail(email, "Message From Group18-EMS", contentWithHeader);
            logger.info("Sent Email TO: {} , Content : {} ", email, contentWithHeader);
            logger.info("Sent text To: {} , Content: {}", phone, contentWithHeader);
        } catch (Exception e) {
            logger.error("Error sending email, or text ERROR: {}", e.getMessage());
        }

    }

}
