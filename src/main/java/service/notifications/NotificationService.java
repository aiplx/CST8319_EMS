package service.notifications;


import dao.EmployeeDAOImpl;
import dao.message.MessageDAOImpl;
import dto.Message;
import java.sql.Timestamp;
import java.util.List;

public class NotificationService {

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

    public List<Message> getAllUserMessages(int employeeID){
        return messageDAO.getAllUserMessages(employeeID);
    }

    public void createNotification(int senderId, int receiverId, String title, String content) {
        Message message = new Message();
        message.setSenderId(senderId);
        message.setReceiverId(receiverId);
        message.setTitle(title);
        message.setMessageContent(content);
        message.setTimestamp(new Timestamp(System.currentTimeMillis()));

        // Save the message
        messageDAO.saveMessage(message);

        //Send email and sms
        smsService.sendSms(employeeDAO.getEmployeePhoneNumber(receiverId),content );
        emailService.sendEmail(employeeDAO.getEmployeeEmail(receiverId),"Message From Group18-EMS",content);

    }

}
