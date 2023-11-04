package com.fptugroup6.phoneshop.model;
import java.util.Date;

public class Messages {
    private int messageID;
    private String senderUsername;
    private String receiverUsername;
    private String messageText;
    private Date messageDateTime;

    public Messages() {
    }

    public Messages(int messageID, String senderUsername, String receiverUsername, String messageText, Date messageDateTime) {
        this.messageID = messageID;
        this.senderUsername = senderUsername;
        this.receiverUsername = receiverUsername;
        this.messageText = messageText;
        this.messageDateTime = messageDateTime;
    }

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Date getMessageDateTime() {
        return messageDateTime;
    }

    public void setMessageDateTime(Date messageDateTime) {
        this.messageDateTime = messageDateTime;
    }
}
