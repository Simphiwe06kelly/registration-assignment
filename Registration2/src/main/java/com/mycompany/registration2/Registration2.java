/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.registration2;

import java.util.Scanner;

/**
 *
 * @author RC_Student_lab
 */
public class Registration2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // User input with validation for username
        String username;
        boolean checkUser;

        // Keep prompting the user until the username is valid
        do {
            System.out.print("Enter username: ");
            username = scanner.nextLine();

            // Check if the username contains an underscore and is 5 characters or less
            checkUser = username.contains("_") && username.length() <= 5;

            if (checkUser) {
                System.out.println("Username successfully captured.");
            } else {
                System.out.println("Username is not correctly formatted please ensure that your username contains '_' and is 5 characters in length.");
            }
        } while (!checkUser);  // Loop continues until the username is valid

        // Password validation
        String password;
        boolean checkPassword;
        do {
            System.out.print("Enter password: ");
            password = scanner.nextLine();

            // Check for password complexity
            checkPassword = password.length() >= 8
                    && password.matches(".*[A-Z].*")
                    && password.matches(".*[a-z].*")
                    && password.matches(".*[0-9].*")
                    && password.matches(".*[!@#$%^&*()].*");

            if (checkPassword) {
                System.out.println("Password successfully captured.");
            } else {
                System.out.println("Password is not correctly formatted,please ensure that your password contains at least 8 characters long, an uppercase letter, a lowercase letter, a number, and a special character.");
            }
        } while (!checkPassword);  // Loop continues until the password is valid

        // Cell number validation
        String cellNumber;
        boolean checkCellNumber;
        do {
            System.out.print("Enter cell number (e.g. +27123456789): ");
            cellNumber = scanner.nextLine();

            // Check if cell number starts with +27 and is 10 digits long
            checkCellNumber = cellNumber.startsWith("+27") && cellNumber.length() == 13; // "+27" + 10 digits = 13 characters

            if (checkCellNumber) {
                System.out.println("Cell phone number successfully captured.");
            } else {
                System.out.println("Cell phone number is not correctly formatted or does not contain international code.");
            }
        } while (!checkCellNumber);  // Loop continues until the cell number is valid

        // First name validation
        String firstName;
        boolean checkFirstName;
        do {
            System.out.print("Enter first name: ");
            firstName = scanner.nextLine();

            // Check if first name is not empty
            checkFirstName = !firstName.isEmpty();

            if (checkFirstName) {
                System.out.println("First name is valid.");
            } else {
                System.out.println("First name cannot be empty.");
            }
        } while (!checkFirstName);  // Loop continues until the first name is valid

        // Last name validation
        String lastName;
        boolean checkLastName;
        do {
            System.out.print("Enter last name: ");
            lastName = scanner.nextLine();

            // Check if last name is not empty
            checkLastName = !lastName.isEmpty();

            if (checkLastName) {
                System.out.println("Last name is valid.");
            } else {
                System.out.println("Last name cannot be empty.");
            }
        } while (!checkLastName);  // Loop continues until the last name is valid

        //Output the details entered by the user
        System.out.println("\nUser Registration Complete:");
        System.out.println("Username: " + username);
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Cell Number: " + cellNumber);

        Login user = new Login(username, password, cellNumber, firstName, lastName);

/* Register user
        System.out.println(user.registerUser()); */

/* Then allow them to log in
        System.out.print("Enter username to ,log in: ");
        String enteredUsername = scanner.nextLine();

        System.out.print("Enter password: ");
        String enteredPassword = scanner.nextLine();

        boolean isSuccessful = user.loginUser(enteredUsername, enteredPassword);

// Show login status
        System.out.println(user.returnLoginStatus(isSuccessful)); */
        
        
        
        


System.out.println("\n--- Login ---");

String enteredUsername = "";
String enteredPassword = "";
boolean correctUsername = false;
boolean correctPassword = false;

//  Loop for correct username
while (!correctUsername) {
    System.out.print("Enter username: ");
    enteredUsername = scanner.nextLine();

    if (enteredUsername.equals(user.getUsername())) {
        correctUsername = true;
    } else {
        System.out.println("Incorrect username. Please try again.");
    }
}

//  Loop for correct password
while (!correctPassword) {
    System.out.print("Enter password: ");
    enteredPassword = scanner.nextLine();

    if (user.loginUser(enteredUsername, enteredPassword)) {
        correctPassword = true;
        System.out.println("Welcome, " + user.getFirstName() + " " + user.getLastName() + ", it's great to see you again.");
    } else {
        System.out.println("Username or password incorrect please try again.");
    }
}



        // Create Login object
        /* Login newUser = new Login(username, password, cellNumber, firstName, lastName);

        // Check and display specific validation messages
        boolean valid = true;

        if (!newUser.checkUsername(username)) {
            System.out.println("The username is incorrectly formatted. It should contain an underscore and be no more than 5 characters long.");
            valid = false;
        }

        if (!Login.isPasswordComplex(password)) {
            System.out.println("The password does not meet complexity requirements.\nIt must be at least 8 characters long and include:\n- an uppercase letter\n- a lowercase letter\n- a number\n- a special character (!@#$%^&*())");
            valid = false;
        }

        if (!newUser.checkCellNumber()) {
            System.out.println("The cell number is incorrectly formatted. It should start with +27 and be no more than 10 digits long (excluding +27).");
            valid = false;
        }

        // If all fields are valid, continue with registration
        if (valid) {
            System.out.println("\nThe above conditions have been met, and the user has been registered successfully.");

            // Proceed to login
            System.out.println("\nPlease log in:");

            System.out.print("Enter username: ");
            String loginUsername = scanner.nextLine();

            System.out.print("Enter password: ");
            String loginPassword = scanner.nextLine();

            boolean isLoggedIn = newUser.loginUser(loginUsername, loginPassword);
            String loginMessage = newUser.returnLoginStatus(isLoggedIn);
            System.out.println(loginMessage);

            if (isLoggedIn) {
                System.out.print("Welcome, ");
                newUser.displayFullName();
            }
        } else {
            System.out.println("\nRegistration failed. Please fix the errors above and try again.");
        }

        scanner.close();*/
    }
}
