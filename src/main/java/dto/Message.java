package dto;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {

    private long messageId;
    private long senderId;
    private long receiverId;
    private String title;
    private String messageContent;
    private java.sql.Timestamp timestamp;
    private String senderName;
    private Boolean isRead;

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
}
