/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registration2;

/**
 *
 * @author RC_Student_lab
 */
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.util.UUID;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONObject;


public class Message {
    Scanner scanner = new Scanner(System.in);

    // Fields
    private String messageID;
    private String recipientCell;
    private String messageHash;
    private String message;
    private static int numMessage = 0; 
    private static int totalMessages = 0;

    //Getters 
    public String getMessageID() {
        return messageID;
    }

    public String getRecipientCell() {
        return recipientCell;
    }

    public String getMessageHash() {
        return messageHash;
    }

    public String getMessage() {
        return message;
    }

    public static int getTotalMessages() {
        return totalMessages;
    }

    //Setters 
    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public void setRecipientCell(String recipientCell) {
        this.recipientCell = recipientCell;
    }

    public void setMessageHash(String messageHash) {
        this.messageHash = messageHash;
    }

    public void setMessage(String message, String messageID) {
        this.message = message;
    }

    // Constructor 
    public Message(String messageID, String recipientCell) {
        this.messageID = messageID;
        this.recipientCell = recipientCell;
        
        numMessage++; 
        totalMessages = numMessage; 
    }

    // Auto-generates a 10-digit message ID
    public String generateMessageID() {
      
        //Generate using UUID to make sure messageID is unique with 10 charcters if generate again
        do {
             messageID = UUID.randomUUID().toString().replace("-", "").substring(0,10);
        } while(!checkMessage(messageID));
        
        System.out.println("Generated messageID: " + messageID);
        return messageID;
    }

    //Checks if messageID is 10 charcters or less
    public boolean checkMessage(String messageID){
        return messageID.length()<= 10; 
    }
    
    // Validates recipient cell number
    public int checkRecipientCell() {
        return (recipientCell.length() <= 10 && recipientCell.startsWith("0")) ? 1 : 0;
    }

    // Creates a message hash from message ID and capitalized first & last words
  public String createMessageHash(String messageID, String message) {
    String idMessage = messageID.substring(0, 2); 
    String [] words = message.trim().split("\\s+"); 
    String firstWord = words[0].toUpperCase(); 
    String lastWord = words[words.length - 1].toUpperCase(); 
    
    String createdMessageHash = idMessage + ":" + totalMessages + ":" + firstWord + lastWord; 
    this.messageHash = createdMessageHash; 
    
    System.out.println("Generated messageHash: " + messageHash);
    return messageHash; 
    
}
  
  public void promptForMessage() {
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
            this.setMessage(messageContent, this.getMessageID()); // or however you want to store it
            break;
        }
    }
}


    // Headings for sending/storing/disregarding a message
    public String sendMessage() {
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
               
                return "Num sent messages:"+ numMessage + "\nMessage Hash: " + messageHash + "\nMessage ID: " + messageID + "\nRecipient Number: " + recipientCell + 
                            "Message sent:"+ message + "\nTotal number sent sent:"+ totalMessages;
            case 1: // Store (simulated)
                return "Num sent messages:"+ numMessage + "\nMessage Hash: " + messageHash + "\nMessage ID: " + messageID + "\nRecipient Number: " + recipientCell + 
                            "Message sent:"+ message + "\nTotal number sent sent:"+ totalMessages;
            case 2:
                return "Message disregarded";
            default:
                return "Invalid choice";
        }
    }

    // Displays message details in a dialog
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
    
    
    public void storeMessage(){
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
    
}
