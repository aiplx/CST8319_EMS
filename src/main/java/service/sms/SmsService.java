package service.sms;


import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 * The {@code SmsService} class provides functionality for sending text messages using the Twilio API.
 *
 */
public class SmsService {
    /**
     * The SID of the Twilio Account, retrieved from an environment variable.
     */
    private static final String SID = System.getenv("SID");
    /**
     *  The Twilio API key associated with the account, retrieved from an environment variable.
     */
    private static final String TOKEN = System.getenv("TOKEN");
    /**
     * The phone number registered to the Twilio account, retrieved from an environment variable.
     */
    private static final String PHONE_NUMBER = System.getenv("TwilioNumber");

    /**
     * Sends a text message using the Twilio API.
     * @param phoneNumber the phone number for the recipient of the text message.
     * @param textContent the content of the text message.
     */
    public void sendSms(String phoneNumber, String textContent) {
        Twilio.init(SID, TOKEN);
        Message message = Message.creator(
                new PhoneNumber(phoneNumber),
                new PhoneNumber(PHONE_NUMBER),
                textContent
        ).create();
        System.out.println(message.getBody());

    }
}
