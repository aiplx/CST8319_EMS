package dto;


public class Message {

    private long messageId;
    private long senderId;
    private long receiverId;
    private String title;
    private String messageContent;
    private java.sql.Timestamp timestamp;

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    private String senderName;



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
}
