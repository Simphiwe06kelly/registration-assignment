/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.registration2;
/**
 *
 * @author RC_Student_lab
 */
import javax.swing.JOptionPane;

public class Registration2 {
    public static void main(String[] args) {
        // Username input and validation
        String username;
        boolean checkUser;
        do {
            username = JOptionPane.showInputDialog(null, "Enter username:");
            if (username == null) System.exit(0);

            checkUser = username.contains("_") && username.length() <= 5;

            if (checkUser) {
                JOptionPane.showMessageDialog(null, "Username successfully captured.");
            } else {
                JOptionPane.showMessageDialog(null, "Username is not correctly formatted. Please ensure that your username contains '_' and is 5 characters or less.");
            }
        } while (!checkUser);

        // Password input and validation
        String password;
        boolean checkPassword;
        do {
            password = JOptionPane.showInputDialog(null, "Enter password:");
            if (password == null) System.exit(0);

            checkPassword = password.length() >= 8
                    && password.matches(".*[A-Z].*")
                    && password.matches(".*[a-z].*")
                    && password.matches(".*[0-9].*")
                    && password.matches(".*[!@#$%^&*()].*");

            if (checkPassword) {
                JOptionPane.showMessageDialog(null, "Password successfully captured.");
            } else {
                JOptionPane.showMessageDialog(null, "Password is not correctly formatted. Please ensure your password contains at least 8 characters, an uppercase letter, a lowercase letter, a number, and a special character.");
            }
        } while (!checkPassword);

        // Cell number input and validation
        String cellNumber;
        boolean checkCellNumber;
        do {
            cellNumber = JOptionPane.showInputDialog(null, "Enter cell number (e.g. +27123456789):");
            if (cellNumber == null) System.exit(0);

            checkCellNumber = cellNumber.startsWith("+27") && cellNumber.length() == 13;

            if (checkCellNumber) {
                JOptionPane.showMessageDialog(null, "Cell phone number successfully captured.");
            } else {
                JOptionPane.showMessageDialog(null, "Cell phone number is not correctly formatted or does not contain international code.");
            }
        } while (!checkCellNumber);

        // First name input and validation
        String firstName;
        boolean checkFirstName;
        do {
            firstName = JOptionPane.showInputDialog(null, "Enter first name:");
            if (firstName == null) System.exit(0);

            checkFirstName = !firstName.trim().isEmpty();

            if (checkFirstName) {
                JOptionPane.showMessageDialog(null, "First name is valid.");
            } else {
                JOptionPane.showMessageDialog(null, "First name cannot be empty.");
            }
        } while (!checkFirstName);

        // Last name input and validation
        String lastName;
        boolean checkLastName;
        do {
            lastName = JOptionPane.showInputDialog(null, "Enter last name:");
            if (lastName == null) System.exit(0);

            checkLastName = !lastName.trim().isEmpty();

            if (checkLastName) {
                JOptionPane.showMessageDialog(null, "Last name is valid.");
            } else {
                JOptionPane.showMessageDialog(null, "Last name cannot be empty.");
            }
        } while (!checkLastName);

        // Output summary
        String summary = "User Registration Complete:\n"
                + "Username: " + username + "\n"
                + "First Name: " + firstName + "\n"
                + "Last Name: " + lastName + "\n"
                + "Cell Number: " + cellNumber;
        JOptionPane.showMessageDialog(null, summary);

        // Create user object
        Login user = new Login(username, password, cellNumber, firstName, lastName);

        // --- Login section ---
        JOptionPane.showMessageDialog(null, "--- Login ---");
        String enteredUsername;
        String enteredPassword;
        boolean correctUsername = false;
        boolean correctPassword = false;

         do {
            enteredUsername = JOptionPane.showInputDialog(null, "Enter username:");
            if (enteredUsername == null) System.exit(0);
            correctUsername = enteredUsername.equals(user.getUsername());
            if (!correctUsername) {
                JOptionPane.showMessageDialog(null, "Incorrect username. Please try again.");
            }
        } while (!correctUsername);

        do {
            enteredPassword = JOptionPane.showInputDialog(null, "Enter password:");
            if (enteredPassword == null) System.exit(0);
            if (user.loginUser(enteredUsername, enteredPassword)) {
                correctPassword = true;
                JOptionPane.showMessageDialog(null, "Welcome, " + user.getFirstName() + " " + user.getLastName() + ", it's great to see you again.");
            } else {
                JOptionPane.showMessageDialog(null, "Username or password incorrect. Please try again.");
            }
        } while (!correctPassword);

        //Messaging section 
        JOptionPane.showMessageDialog(null, "--- Welcome to QuickChat ---");
        String sendMsgAnswer = JOptionPane.showInputDialog(null, "Do you want to send a message? (yes/no):");

        if (sendMsgAnswer != null && sendMsgAnswer.equalsIgnoreCase("yes")) {
          
            String messageID = ""; 
          
            String recipientCell = "";
            boolean validRecipient = false;

            // Get valid recipient number
            while (!validRecipient) {
                recipientCell = JOptionPane.showInputDialog(null, "Enter recipient's cell number (starts with 0, max 10 digits):");

                Message tempMessage = new Message(messageID, recipientCell);
                if (tempMessage.checkRecipientCell() == 1) {
                    validRecipient = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid recipient cell number. Please try again.");
                }
            }

            // Get message content
           String messageContent = "";

            // Final message object and send
            Message createdMessage = new Message("", recipientCell);
            createdMessage.promptForMessage();
            String generatedID = createdMessage.generateMessageID(); 
            createdMessage.setMessage(messageContent, generatedID);
            createdMessage.createMessageHash(generatedID, messageContent); 

            JOptionPane.showMessageDialog(null, createdMessage.sendMessage());
        } else {
            JOptionPane.showMessageDialog(null, "No message will be sent.");
        }
    }
}
