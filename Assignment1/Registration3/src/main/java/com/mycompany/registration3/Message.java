/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registration3;

/**
 *
 * @author RC_Student_lab
 */
import javax.swing.JOptionPane;
import java.util.UUID;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONObject;

public class Message {

    // Fields
    private String[] messageID;
    private String[] recipientCell;
    private String[] messageHash;
    private String[] message;

    private String[] sentMessages;
    private String[] storedMessages;
    private String[] disregardedMessages;

    private String[] recipientCells;
    private String[] senderNames;
    private String[] messageContents;
    private String[] senderCell;

    private static int numMessage = 0;
    static int totalMessages = 0;
    private static int numSent = 0;
    private static int numStored = 0;
    private static int numDisregarded = 0;

    //Getters 
    public String getMessageID(int index) {
        return messageID[index];
    }

    public String getRecipientCell(int index) {
        return recipientCell[index];
    }

    public String getMessageHash(int index) {
        return messageHash[index];
    }

    public String getMessage(int index) {
        return message[index];
    }

    public static int getTotalMessages() {
        return totalMessages;
    }

    //Setters 
    public void setMessageID(String messageID, int index) {
        this.messageID[index] = messageID;
    }

    public void setRecipientCell(String recipientCell, int index) {
        this.recipientCell[index] = recipientCell;
    }

    public void setMessageHash(String messageHash, int index) {
        this.messageHash[index] = messageHash;
    }

    public void setMessage(String messages, String messageID, int index) {
        this.message[index] = messages;
    }

    // Constructor 
    public Message(int size) {
        this.messageID = new String[size];
        this.recipientCell = new String[size];
        this.messageHash = new String[size];
        this.message = new String[size];

        this.sentMessages = new String[size];
        this.storedMessages = new String[size];
        this.disregardedMessages = new String[size];

        numMessage = 0;
        totalMessages = 0;
    }

    // Auto-generates a 10-digit message ID
    public String generateMessageID(int index) {

        //Generate using UUID to make sure messageID is unique with 10 charcters if generate again
        do {
            messageID[index] = UUID.randomUUID().toString().replace("-", "").substring(0, 10);
        } while (!checkMessage(messageID[index]));

        System.out.println("Generated messageID: " + messageID);
        return messageID[index];
    }

    //Checks if messageID is 10 charcters or less
    public boolean checkMessage(String messageID) {
        return messageID.length() <= 10;
    }

    // Validates recipient cell number
    public int checkRecipientCell(int index) {
        return (recipientCell[index].length() <= 10 && recipientCell[index].startsWith("0")) ? 1 : 0;
    }

    // Creates a message hash from message ID and capitalized first & last words
    //Becomes an option at last dialog
    public String createMessageHash(String messageID, String message, int index) {
        String idMessage = messageID.substring(0, 2);
        String[] words = message.trim().split("\\s+");
        String firstWord = words[0].toUpperCase();
        String lastWord = words[words.length - 1].toUpperCase();

        String createdMessageHash = idMessage + ":" + totalMessages + ":" + firstWord + lastWord;
        this.messageHash[index] = createdMessageHash;

        System.out.println("Generated messageHash: " + messageHash);
        return messageHash[index];

    }

    public void promptForMessage(int index) {
        String messageContent = "";

        while (true) {
            messageContent = JOptionPane.showInputDialog(null, "Enter your message:");

            if (messageContent == null) {
                // User cancelled
                JOptionPane.showMessageDialog(null, "Message entry cancelled.");
                break;
            }

            messageContent = messageContent.trim();

            if (messageContent.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Message cannot be empty. Please enter a message.");
            } else if (messageContent.length() > 250) {
                JOptionPane.showMessageDialog(null, "Message is too long (max 250 characters). Please enter a shorter message.");
            } else {
                JOptionPane.showMessageDialog(null, "Message sent.");
                this.setMessage(messageContent, this.getMessageID(index), index); // or however you want to store it
                break;
            }
        }
    }

    // Headings for sending/storing/disregarding a message
    public String sendMessage(int index) {
        String[] options = {"Send", "Store", "Disregard"};
        int choice = JOptionPane.showOptionDialog(null,
                "Choose what to do with the message:",
                "Message Options",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);

        switch (choice) {
            case 0: // Send
                numSent++;
                return "Message sent successfully.\n"
                        + "Message Hash: " + messageHash[index] + "\n"
                        + "Message ID: " + messageID[index] + "\n"
                        + "Recipient Number: " + recipientCell[index] + "\n"
                        + "Message: " + message[index] + "\n"
                        + "Total messages sent: " + numSent;

            case 1: // Store
                numStored++;
                return "Message stored successfully.\n"
                        + "Message Hash: " + messageHash[index] + "\n"
                        + "Message ID: " + messageID[index] + "\n"
                        + "Recipient Number: " + recipientCell[index] + "\n"
                        + "Message: " + message[index] + "\n"
                        + "Total messages stored: " + numStored;

            case 2: // Disregard
                numDisregarded++;
                return "Message disregarded.\nTotal disregarded: " + numDisregarded;

            default:
                return "Invalid choice";
        }
    }

    // Display message details in a dialog
    public void printMessage() {
        String output = "Message Details:\n"
                + "Message ID: " + messageID + "\n"
                + "Recipient Cell: " + recipientCell + "\n"
                + "Message: " + message + "\n"
                + "Message Hash: " + messageHash;

        JOptionPane.showMessageDialog(null, output);
    }

    // Returns total number of sent messages
    public static int returnTotalMessages() {
        return totalMessages;
    }

    public void storeMessage() {
        try {
            JSONObject json = new JSONObject();
            json.put("messageID", this.messageID);
            json.put("recipientCell", this.recipientCell);
            json.put("messageHash", this.messageHash);
            json.put("message", this.message);

            // Append the JSON string to a file called messages.json
            try (FileWriter file = new FileWriter("messages.json", true)) { // 'true' for append mode
                file.write(json.toString() + System.lineSeparator());
            }

            System.out.println("Message stored successfully.");
        } catch (IOException e) {
            System.err.println("Error storing message: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public String displayAllMessages() {
        StringBuilder output = new StringBuilder("All sent messages:\n");
        boolean foundMessage = false;

        for (int i = 0; i < message.length; i++) {
            if (message[i] != null && recipientCell[i] != null) {
                foundMessage = true;
                output.append("Message ").append(i + 1).append(":\n");
                output.append("To: ").append(recipientCell[i]).append("\n");
                output.append("Content: ").append(message[i]).append("\n\n");
            }
        }

        return foundMessage ? output.toString() : "No messages have been sent yet.";
    }

    public String getLongestMessage() {
        if (message == null || message.length == 0) {
            return "No messages were sent.";
        }

        String longest = "";
        int longestIndex = -1;

        for (int i = 0; i < message.length; i++) {
            if (message[i] != null && message[i].length() > longest.length()) {
                longest = message[i];
                longestIndex = i;
            }
        }

        if (longestIndex != -1) {
            return "Longest Message:\n"
                    + "Recipient: " + recipientCell[longestIndex] + "\n"
                    + "Message: " + longest;
        } else {
            return "No valid messages found.";
        }
    }

    public String searchMessageByID(String searchID) {
        for (int i = 0; i < messageID.length; i++) {
            if (messageID[i] != null && messageID[i].equals(searchID)) {
                String recipient = (recipientCell[i] != null) ? recipientCell[i] : "Unknown recipient";
                String msg = (message[i] != null) ? message[i] : "No message content";
                return "Message ID: " + searchID + "\nRecipient: " + recipient + "\nMessage: " + msg;
            }
        }
        return "Message ID " + searchID + " not found.";
    }

    public String searchMessagesByRecipient(String searchCell) {
        StringBuilder output = new StringBuilder("Messages sent to " + searchCell + ":\n");
        boolean found = false;

        for (int i = 0; i < recipientCell.length; i++) {
            if (recipientCell[i] != null && recipientCell[i].equals(searchCell)) {
                output.append("Message ID: ").append(messageID[i]).append("\n");
                output.append("Message: ").append(message[i]).append("\n\n");
                found = true;
            }
        }

        return found ? output.toString() : "No messages were sent to " + searchCell;
    }

    public String deleteMessageByHash(String searchHash) {
        for (int i = 0; i < messageHash.length; i++) {
            if (messageHash[i] != null && messageHash[i].equals(searchHash)) {
                // Found the message, delete by nullifying
                messageID[i] = null;
                recipientCell[i] = null;
                messageHash[i] = null;
                message[i] = null;
                return "Message with hash " + searchHash + " deleted successfully.";
            }
        }
        return "Message hash " + searchHash + " not found.";
    }

    public String generateSentMessagesReport() {
        StringBuilder report = new StringBuilder("Sent Messages Report:\n\n");
        boolean foundSentMessages = false;

        for (int i = 0; i < message.length; i++) {
            if (message[i] != null && recipientCell[i] != null && messageHash[i] != null && messageID[i] != null) {
                report.append("Message ").append(i + 1).append(":\n");
                report.append("Message ID: ").append(messageID[i]).append("\n");
                report.append("Recipient Cell: ").append(recipientCell[i]).append("\n");
                report.append("Message Hash: ").append(messageHash[i]).append("\n");
                report.append("Message: ").append(message[i]).append("\n\n");
                foundSentMessages = true;
            }
        }

        return foundSentMessages ? report.toString() : "No sent messages to report.";
    }

}
