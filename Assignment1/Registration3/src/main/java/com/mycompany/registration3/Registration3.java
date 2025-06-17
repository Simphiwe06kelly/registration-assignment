/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.registration3;

/**
 *
 * @author RC_Student_lab
 */
import javax.swing.JOptionPane;
import java.util.Arrays;
import java.util.regex.*;

public class Registration3 {

    public static void main(String[] args) {
        // Username input and validation
        String username;
        boolean checkUser;
        do {
            username = JOptionPane.showInputDialog(null, "Enter username:");
            if (username == null) {
                System.exit(0);
            }

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
            if (password == null) {
                System.exit(0);
            }

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
            if (cellNumber == null) {
                System.exit(0);
            }

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
            if (firstName == null) {
                System.exit(0);
            }

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
            if (lastName == null) {
                System.exit(0);
            }

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

        //Login section
        JOptionPane.showMessageDialog(null, "--- Login ---");
        String enteredUsername;
        String enteredPassword;
        boolean correctUsername = false;
        boolean correctPassword = false;

        do {
            enteredUsername = JOptionPane.showInputDialog(null, "Enter username:");
            if (enteredUsername == null) {
                System.exit(0);
            }
            correctUsername = enteredUsername.equals(user.getUsername());
            if (!correctUsername) {
                JOptionPane.showMessageDialog(null, "Incorrect username. Please try again.");
            }
        } while (!correctUsername);

        do {
            enteredPassword = JOptionPane.showInputDialog(null, "Enter password:");
            if (enteredPassword == null) {
                System.exit(0);
            }
            if (user.loginUser(enteredUsername, enteredPassword)) {
                correctPassword = true;
                JOptionPane.showMessageDialog(null, "Welcome, " + user.getFirstName() + " " + user.getLastName() + ", it's great to see you again.");
            } else {
                JOptionPane.showMessageDialog(null, "Username or password incorrect. Please try again.");
            }
        } while (!correctPassword);

        //Messaging section 
        Message message = new Message(100);
        JOptionPane.showMessageDialog(null, "--- Welcome to QuickChat ---");

        int currentIndex = 0;

        while (true) {
            String menu = "Choose an option:\n"
                    + "1. Send a Message\n"
                    + "2. View All Messages\n"
                    + "3. View Longest Message\n"
                    + "4. Search Message by ID\n"
                    + "5. Search Messages by Recipient Cell\n"
                    + "6. Delete Message by Hash\n"
                    + "7. Generate Sent Messages Report\n"
                    + "8. Exit";

            String input = JOptionPane.showInputDialog(menu);

            if (input == null) {
                break; // Cancel pressed
            }
            int choice = -1;
            try {
                choice = Integer.parseInt(input) - 1;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number.");
                continue;
            }
            switch (choice) {
                case 0: // Send a Message
                    String recipient = JOptionPane.showInputDialog("Enter recipient cell number:");
                    message.setRecipientCell(recipient, currentIndex);
                    String generatedID = message.generateMessageID(currentIndex);
                    message.promptForMessage(currentIndex);

                    if (message.getMessage(currentIndex) != null) {
                        message.createMessageHash(generatedID, message.getMessage(currentIndex), currentIndex);
                        String status = message.sendMessage(currentIndex);
                        JOptionPane.showMessageDialog(null, status);

                        if (status.contains("sent") || status.contains("stored")) {
                            currentIndex++;            // increment total message count
                            Message.totalMessages++;

                        }
                    }
                    break;

                case 1: // View All Messages
                    JOptionPane.showMessageDialog(null, message.displayAllMessages());
                    break;

                case 2: // View Longest Message
                    JOptionPane.showMessageDialog(null, message.getLongestMessage());
                    break;

                case 3: // Search by Message ID
                    String searchID = JOptionPane.showInputDialog("Enter Message ID to search:");
                    JOptionPane.showMessageDialog(null, message.searchMessageByID(searchID));
                    break;

                case 4: // Search by Recipient Cell
                    String recipientCell = JOptionPane.showInputDialog("Enter recipient cell number:");
                    JOptionPane.showMessageDialog(null, message.searchMessagesByRecipient(recipientCell));
                    break;

                case 5: // Delete by Hash
                    String hash = JOptionPane.showInputDialog("Enter Message Hash to delete:");
                    JOptionPane.showMessageDialog(null, message.deleteMessageByHash(hash));
                    break;

                case 6: // Generate Report
                    JOptionPane.showMessageDialog(null, message.generateSentMessagesReport());
                    break;

                case 7: // Exit
                    JOptionPane.showMessageDialog(null, "Thank you for using the system.");
                    System.exit(0);
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Invalid option.");
            }
        }
    }
}
