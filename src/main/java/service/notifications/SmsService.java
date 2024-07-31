package service.notifications;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


public class SmsService {
    private static final String SID = System.getenv("SID");
    private static final String TOKEN = System.getenv("TOKEN");
    private static final String PHONE_NUMBER = System.getenv("TwilioNumber");
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
