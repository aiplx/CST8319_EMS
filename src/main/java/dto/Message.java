package dto;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class represents the {@code Message} entity, used to transfer data within layers of the application.
 *  It contains information such as the sender's ID, receiver's ID, title, content, read status, and timestamp of the messages.
 */
public class Message {


    private long messageId;
    private long senderId;
    private long receiverId;
    private String title;
    private String messageContent;
    private java.sql.Timestamp timestamp;
    private String senderName;
    private String recieverName;
    private Boolean isRead;

    /**
     * Formats the Date of the message into a readable form.
     * @return A String format of the date in Day of Week, Month, Number of the Date, at Hour:Minute AM/PM.
     * For Example, if the timestamp is {@code 2023-08-19 14:30:12} after formatting it will be {@code Saturday August 19, 2023 at 2:30 PM }
     */
    public String getFormattedTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE MMMM d',' yyyy 'at' h:mm a");
        Date date = new Date(timestamp.getTime());
        return formatter.format(date);
    }



    public String getSenderName() {
        return senderName;
    }


    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }


    public long getSenderId() {
        return senderId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }


    public long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(long receiverId) {
        this.receiverId = receiverId;
    }


    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }


    public java.sql.Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(java.sql.Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getRead() {
        return isRead;
    }

    public Boolean isRead(){
        return isRead != null && isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public String getRecieverName() {
        return recieverName;
    }

    public void setRecieverName(String recieverName) {
        this.recieverName = recieverName;
    }
}

