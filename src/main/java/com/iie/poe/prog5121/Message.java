package com.iie.poe.prog5121;

import java.util.Random;

public class Message {

    private String messageId;
    private String recipientCell;
    private String messageContent;
    private String messageHash;
    private int messageNumber;

    // Standard constructor
    public Message(int messageNumber, String recipientCell, String messageContent) {
        this.messageNumber = messageNumber;
        this.recipientCell = recipientCell;
        this.messageContent = messageContent;
        this.messageId = generateMessageId();
        this.messageHash = createMessageHash();
    }

    // Default no-argument constructor (Required for unit testing!)
    public Message() {
    }

    // Generate 10-digit random Message ID
    public String generateMessageId() {
        Random random = new Random();
        long idNumber = 1000000000L + (long) (random.nextDouble() * 9000000000L);
        this.messageId = String.valueOf(idNumber);
        return this.messageId;
    }

    public boolean checkMessageID() {
        return this.messageId != null && this.messageId.length() <= 10;
    }

    // Check Recipient Cell using stored field
    public String checkRecipientCell() {
        return checkRecipientCell(this.recipientCell);
    }

    // Overloaded Check Recipient Cell (Required for unit test!)
    public String checkRecipientCell(String cellNumber) {
        if (cellNumber == null || cellNumber.isEmpty()) {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
        if (cellNumber.startsWith("+") || (cellNumber.length() > 10 && !cellNumber.startsWith("0"))) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    // Check Message Length using stored field
    public String checkMessageLength() {
        return checkMessageLength(this.messageContent);
    }

    // Overloaded Check Message Length (Required for unit test!)
    public String checkMessageLength(String message) {
        if (message == null) {
            return "Message ready to send.";
        }
        if (message.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = message.length() - 250;
            return "Message exceeds 250 characters by " + excess + "; please reduce the size.";
        }
    }

    // Create Message Hash using stored fields
    public String createMessageHash() {
        return createMessageHash(this.messageId, this.messageNumber, this.messageContent);
    }

    // Overloaded Create Message Hash (Required for unit test!)
    public String createMessageHash(String id, int msgNum, String content) {
        if (id == null || content == null || content.trim().isEmpty()) {
            return "";
        }
        String firstTwoId = id.substring(0, Math.min(2, id.length()));
        String[] words = content.trim().split("\\s+");
        String firstWord = words[0].replaceAll("[^a-zA-Z0-9]", "").toUpperCase();
        String lastWord = words[words.length - 1].replaceAll("[^a-zA-Z0-9]", "").toUpperCase();

        this.messageHash = firstTwoId + ":" + msgNum + ":" + firstWord + lastWord;
        return this.messageHash;
    }

    // SentMessage method
    public String SentMessage(int choice) {
        switch (choice) {
            case 1:
                return "Message successfully sent.";
            case 2:
                return "Press 0 to delete the message.";
            case 3:
                return "Message successfully stored.";
            default:
                return "Invalid choice.";
        }
    }

    // Print message details
    public String printMessages() {
        return "Message ID: " + this.messageId + "\n" +
               "Message Hash: " + this.messageHash + "\n" +
               "Recipient: " + this.recipientCell + "\n" +
               "Message: " + this.messageContent;
    }

    // Getters and Setters
    public String getMessageId() { return messageId; }
    public void setMessageId(String messageId) { this.messageId = messageId; }

    public String getRecipientCell() { return recipientCell; }
    public void setRecipientCell(String recipientCell) { this.recipientCell = recipientCell; }

    public String getMessageContent() { return messageContent; }
    public void setMessageContent(String messageContent) { this.messageContent = messageContent; }

    public String getMessageHash() { return messageHash; }

    public int getMessageNumber() { return messageNumber; }
    public void setMessageNumber(int messageNumber) { this.messageNumber = messageNumber; }
}