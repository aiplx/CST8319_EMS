package service.notifications;


import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class EmailService {
    private static final Logger logger = LogManager.getLogger(EmailService.class);

    public static final String API_KEY = System.getenv("SendGridApi");
    public static final Email from = new Email(System.getenv("SendGripEmail"));

    public void sendEmail(String to, String subject, String body) {
        Email toEmail = new Email(to);
        Content content = new Content("text/plain", body);
        Mail mail = new Mail(from, subject, toEmail, content);

        SendGrid sg = new SendGrid(API_KEY);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
            logger.info("Email sent successfully: From: {} To: {}   Subject:{}  Content: {}", from, to, subject, response.getBody());
        } catch (IOException e) {
            logger.error("Error sending email: {}", e.getMessage());
        }
    }

}
