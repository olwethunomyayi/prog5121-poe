package com.iie.poe.prog5121;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Message> storedMessages = new ArrayList<>();

        // 1. Ask for personal details first
        System.out.println("=== REGISTER AN ACCOUNT ===");
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter username: ");
        String regUsername = scanner.nextLine();

        System.out.print("Enter password: ");
        String regPassword = scanner.nextLine();

        System.out.print("Enter cell phone number: ");
        String regCell = scanner.nextLine();

        // Initialize login object with your first and last name
        Login login = new Login(firstName, lastName);

        // Register user
        String registrationMessage = login.registerUser(regUsername, regPassword, regCell);
        System.out.println(registrationMessage);

        // 2. Login Phase
        System.out.println("\n=== PLEASE LOGIN ===");
        boolean isLoggedIn = false;

        while (!isLoggedIn) {
            System.out.print("Enter username: ");
            String loginUser = scanner.nextLine();

            System.out.print("Enter password: ");
            String loginPass = scanner.nextLine();

            boolean success = login.loginUser(loginUser, loginPass);
            System.out.println(login.returnLoginStatus(success));

            if (success) {
                isLoggedIn = true;
            } else {
                System.out.println("Please try again.\n");
            }
        }

        // 3. QuickChat Menu (Runs after successful login)
        System.out.println("\nWelcome to QuickChat.");

        boolean running = true;
        while (running) {
            System.out.println("\nPlease choose an option:");
            System.out.println("1. Send Messages");
            System.out.println("2. Show recently sent messages");
            System.out.println("3. Quit");
            System.out.println("4. View Stored Messages Menu");
            System.out.print("Enter choice (1-4): ");

            String choiceInput = scanner.nextLine();

            if (choiceInput.equals("1")) {
                System.out.print("How many messages would you like to enter? ");
                int numMessages = 0;
                try {
                    numMessages = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    continue;
                }

                for (int i = 0; i < numMessages; i++) {
                    System.out.println("\n--- Entering Message " + (i + 1) + " of " + numMessages + " ---");

                    System.out.print("Enter recipient cell number (e.g. +27718693002): ");
                    String cellNumber = scanner.nextLine();

                    System.out.print("Enter message content: ");
                    String content = scanner.nextLine();

                    Message newMessage = new Message(i + 1, cellNumber, content);
                    storedMessages.add(newMessage);

                    System.out.println("Message successfully stored and sent!");
                }
            } 
            else if (choiceInput.equals("2")) {
                System.out.println("Coming Soon.");
            } 
            else if (choiceInput.equals("3") || choiceInput.equalsIgnoreCase("quit")) {
                System.out.println("Exiting application. Goodbye!");
                running = false;
                break;
            } 
            else if (choiceInput.equals("4")) {
                boolean subRunning = true;
                while (subRunning) {
                    System.out.println("\n--- Stored Messages Menu ---");
                    System.out.println("a. Display sender and recipient of all stored messages");
                    System.out.println("b. Display the longest stored message");
                    System.out.println("c. Search for a message ID");
                    System.out.println("d. Search all messages for a particular recipient");
                    System.out.println("e. Delete a message using the message hash");
                    System.out.println("f. Display a report listing full details of stored messages");
                    System.out.println("g. Return to Main Menu");
                    System.out.print("Choose an option (a-g): ");

                    String subChoice = scanner.nextLine().toLowerCase();
                    switch (subChoice) {
                        case "a":
                            System.out.println("\n--- Sender & Recipient of Stored Messages ---");
                            for (Message m : storedMessages) {
                                System.out.println("Recipient: " + m.getRecipientCell());
                            }
                            break;

                        case "b":
                            // b. Display the longest stored message
                            System.out.println("\n--- Longest Stored Message ---");
                            if (storedMessages.isEmpty()) {
                                System.out.println("No messages stored currently.");
                            } else {
                                Message longest = storedMessages.get(0);
                                for (Message m : storedMessages) {
                                    if (m.getMessageContent().length() > longest.getMessageContent().length()) {
                                        longest = m;
                                    }
                                }
                                System.out.println("Recipient: " + longest.getRecipientCell());
                                System.out.println("Message: " + longest.getMessageContent());
                                System.out.println("Length: " + longest.getMessageContent().length() + " characters");
                            }
                            break;

                        case "c":
                            // c. Search for a message ID / Hash
                            System.out.print("Enter Message Hash to search: ");
                            String searchId = scanner.nextLine();
                            boolean foundId = false;
                            for (Message m : storedMessages) {
                                if (m.getMessageHash().equalsIgnoreCase(searchId)) {
                                    System.out.println("\nMessage Found!");
                                    System.out.println("Hash: " + m.getMessageHash());
                                    System.out.println("Recipient: " + m.getRecipientCell());
                                    System.out.println("Message: " + m.getMessageContent());
                                    foundId = true;
                                    break;
                                }
                            }
                            if (!foundId) {
                                System.out.println("No message found with that hash ID.");
                            }
                            break;

                        case "d":
                            // d. Search all messages for a particular recipient
                            System.out.print("Enter recipient cell number to search: ");
                            String searchRecipient = scanner.nextLine();
                            System.out.println("\n--- Messages for " + searchRecipient + " ---");
                            boolean matchFound = false;
                            for (Message m : storedMessages) {
                                if (m.getRecipientCell().equalsIgnoreCase(searchRecipient)) {
                                    System.out.println("Hash: " + m.getMessageHash() + " | Message: " + m.getMessageContent());
                                    matchFound = true;
                                }
                            }
                            if (!matchFound) {
                                System.out.println("No messages found for this recipient.");
                            }
                            break;

                        case "e":
                            System.out.print("Enter Message Hash to delete: ");
                            String searchHash = scanner.nextLine();
                            boolean removed = storedMessages.removeIf(m -> m.getMessageHash().equalsIgnoreCase(searchHash));
                            if (removed) {
                                System.out.println("Message successfully deleted.");
                            } else {
                                System.out.println("No matching message hash found.");
                            }
                            break;

                        case "f":
                            System.out.println("\n--- Stored Messages Report ---");
                            for (Message m : storedMessages) {
                                System.out.println("Hash: " + m.getMessageHash() + " | Recipient: " + m.getRecipientCell() + " | Message: " + m.getMessageContent());
                            }
                            break;

                        case "g":
                            subRunning = false;
                            break;

                        default:
                            System.out.println("Invalid option. Please choose between a and g.");
                            break;
                    }
                }
            }
        }
    }
}