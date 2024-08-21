package service.notification;


import dao.employee.EmployeeDAOImpl;
import dao.message.MessageDAOImpl;
import dto.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.email.EmailService;
import service.sms.SmsService;
import java.sql.Timestamp;
import java.util.List;
/**
 * The {@code NotificationService} class provides functionality for getting messages, and sending emails and text messages.
 *  It creates messages, retrieves the number of unread messages, and marks messages as read.
 *  This service also handles notification related information, such as the number of unread messages a user has.
 *  This service utilizes {@link EmailService} and {@link SmsService} to send both email and sms messages respectively.
 *
 */
public class NotificationService {
    private static final Logger logger = LogManager.getLogger(NotificationService.class);

    private final EmailService emailService;
    private final SmsService smsService;
    private final MessageDAOImpl messageDAO;
    private final EmployeeDAOImpl employeeDAO;


    /**
     * Constructs a new {@code NotificationService} instance with initialized
     * Services and DAO instances.
     */
    public NotificationService() {
        this.emailService = new EmailService();
        this.smsService = new SmsService();
        this.messageDAO = new MessageDAOImpl();
        this.employeeDAO = new EmployeeDAOImpl();
    }

    /** Retrieves a list of messages for the employee based on their employee ID.
     * @param employeeID the ID of the employee.
     * @return a list of {@link Message} objects representing the user's messages.
     */
    public List<Message> getAllUserMessages(int employeeID) {
        return messageDAO.getAllUserMessages(employeeID);
    }

    /** Retrieves the number of unread messages for an employee identified by their employee ID.
     * @param employeeID  the employee ID of the employee.
     * @return the number of unread messages for employee.
     */
    public int numOfUnreadMessages(int employeeID) {
        return messageDAO.getUnreadMessages(employeeID);
    }

    /** Marks a specified message as read.
     * @param messageID the message ID of the message to be marked as read.
     */
    public void markAsRead(int messageID) {
        messageDAO.markAsRead(messageID);
    }

    /** Creates a new message, saves it to the database and sends the message as an email and sms message.
     * @param senderId the ID of the person sending the message.
     * @param receiverId the ID of the person receiving the message.
     * @param title the title of the message.
     * @param content the body content of the message.
     */
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



        try {
            // concatenate the information of the message to make the information easier to read for recipients
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
