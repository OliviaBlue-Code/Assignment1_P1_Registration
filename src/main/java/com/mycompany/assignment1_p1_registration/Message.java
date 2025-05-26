


package com.mycompany.assignment1_p1_registration;

import javax.swing.JOptionPane;
import java.util.Random;

public class Message {

    String messageID;
    int numMessageSent;
    String recipientCell;
    String message;
    public String messageHash;

    public String getMessageID() {
        return "getMessageID: " + messageID;
    }

    public String setMessageID(String messageID) {
        if (messageID.length() == 10) {
            this.messageID = messageID;
            return "setMessageID: Message ID set successfully";
        } else {
            return "setMessageID: Invalid message ID";
        }
    }

    public String getNumMessageSent() {
        return "getNumMessageSent: " + numMessageSent;
    }

    public String setNumMessageSent(int numMessageSent) {
        if (numMessageSent > 0) {
            this.numMessageSent = numMessageSent;
            return "setNumMessageSent: Number of messages sent set successfully";
        } else {
            return "setNumMessageSent: Invalid number of messages sent";
        }
    }

    public String getRecipientCell() {
        return "getRecipientCell: " + recipientCell;
    }

    public String setRecipientCell(String recipientCell) {
        if (recipientCell.startsWith("+27") && recipientCell.length() <= 13) {
            this.recipientCell = recipientCell;
            return "setRecipientCell: Recipient cell set successfully";
        } else {
            return "setRecipientCell: Invalid recipient cell";
        }
    }

    public String getMessage() {
        return "getMessage: " + message;
    }

    public String setMessage(String message) {
        if (message.length() <= 250) {
            this.message = message;
            return "setMessage: Message set successfully";
        } else {
            return "setMessage: Message too long";
        }
    }

    public String getMessageHash() {
        return "getMessageHash: " + messageHash;
    }

    public String setMessageHash(String messageHash) {
        this.messageHash = messageHash;
        return "setMessageHash: Message hash set successfully";
    }

    public Message(int numMessageSent, String recipientCell, String message) {
        this.numMessageSent = numMessageSent;
        this.recipientCell = recipientCell;
        this.message = message;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash().toUpperCase();
    }

    private String generateMessageID() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }

    public String checkMessageID() {
        if (messageID.length() == 10) {
            return "checkMessageID: Message ID is valid";
        } else {
            return "checkMessageID: Message ID is invalid";
        }
    }

    public String checkRecipientCell() {
        if (recipientCell.startsWith("+27") && recipientCell.length() <= 13) {
            return "checkRecipientCell: Recipient cell is valid";
        } else {
            return "checkRecipientCell: Recipient cell is invalid";
        }
    }

    public String createMessageHash() {
        String[] words = message.split("\\s+");
        return (messageID.substring(0, 2) + ":" + numMessageSent + ":" + words[0].toUpperCase() + " " + words[words.length - 1].toUpperCase());
    }

    public String sentMessage() {
        int choice = JOptionPane.showOptionDialog(null, "What would you like to do with the message?", "Message Options", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Send", "Store", "Disregard"}, "Send");
        switch (choice) {
            case 0:
                return "sentMessage: Message sent";
            case 1:
                return "sentMessage: Message stored";
            case 2:
                return "sentMessage: Message disregarded";
            default:
                return "sentMessage: Invalid choice";
        }
    }

    public String printMessage() {
        return "printMessage: Message ID: " + messageID + "\nNum Message Sent: " + numMessageSent + "\nRecipient: " + recipientCell + "\nMessage: " + message + "\nMessage Hash: " + messageHash.toUpperCase();
    }

    public String returnTotalMessages() {
        return "returnTotalMessages: Total messages sent: " + numMessageSent;
    }
}
