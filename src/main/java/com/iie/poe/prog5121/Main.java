package com.iie.poe.prog5121;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
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
            System.out.print("Enter choice (1-3): "); // Make sure this is System.out.print
            
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
                    
                    Message tempMsg = new Message();
                    String cellResult = tempMsg.checkRecipientCell(cellNumber);
                    System.out.println(cellResult);
                    
                    if (!cellResult.contains("successfully captured")) {
                        System.out.println("Please try again.");
                        i--; 
                        continue;
                    }

                    System.out.print("Enter message content (max 250 characters): ");
                    String content = scanner.nextLine();
                    
                    String lengthResult = tempMsg.checkMessageLength(content);
                    System.out.println(lengthResult);
                    
                    if (!lengthResult.contains("Message ready to send")) {
                        i--; 
                        continue;
                    }

                    Message message = new Message(i, cellNumber, content);
                    
                    System.out.println("Message ID generated: " + message.getMessageId());
                    System.out.println("Message Hash: " + message.getMessageHash());

                    System.out.println("\nSelect an option for this message:");
                    System.out.println("1. Send Message");
                    System.out.println("2. Disregard Message");
                    System.out.println("3. Store Message");
                    System.out.print("Choice (1-3): ");
                    
                    int sendChoice = 1;
                    try {
                        sendChoice = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        sendChoice = 1;
                    }
                    
                    String sendFeedback = message.SentMessage(sendChoice);
                    System.out.println(sendFeedback);
                    
                    if (sendChoice == 1 || sendChoice == 3) {
                        System.out.println("\nFull Message Details:");
                        System.out.println(message.printMessages());
                    }
                }
                
            } else if (choiceInput.equals("2")) {
                System.out.println("Coming Soon.");
                
            } else if (choiceInput.equals("3") || choiceInput.equalsIgnoreCase("quit")) {
                System.out.println("Exiting application. Goodbye!");
                running = false;
                
            } else {
                System.out.println("Invalid choice. Please select 1, 2, or 3.");
            }
        }
        scanner.close();
    }
}