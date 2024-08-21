package service.email;

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
/**
 * The {@code EmailService} class provides functionality for sending emails using the SendGrid API.
 *
 */
public class EmailService {
    private static final Logger logger = LogManager.getLogger(EmailService.class);

    /** The Sendgrid API Key, retrieved from an environment variable.*/
    public static final String API_KEY = System.getenv("SendGridApi");

    /** The Email for the sender (Email associated with the SendGrid account), retrieved from an environment variable. */
    public static final Email from = new Email(System.getenv("SendGridEmail"));

    /**
     * Sends an email using the SendGrid API.
     *
     * @param to      the recipient's email address.
     * @param subject the subject of the email.
     * @param body    the body content of the email.
     */
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
