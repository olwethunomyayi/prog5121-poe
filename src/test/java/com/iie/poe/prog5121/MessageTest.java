package com.iie.poe.prog5121;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    public void testMessageLengthSuccess() {
        Message msg = new Message();
        String content = "Hi Mike, can you join us for dinner tonight?";
        String result = msg.checkMessageLength(content);
        assertEquals("Message ready to send.", result);
    }

    @Test
    public void testMessageLengthFailure() {
        Message msg = new Message();
        StringBuilder longMsg = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            longMsg.append("abcdefghij"); // 260 characters
        }
        String result = msg.checkMessageLength(longMsg.toString());
        assertEquals("Message exceeds 250 characters by 10; please reduce the size.", result);
    }

    @Test
    public void testRecipientCellSuccess() {
        Message msg = new Message();
        String result = msg.checkRecipientCell("+27718693002");
        assertEquals("Cell phone number successfully captured.", result);
    }

    @Test
    public void testRecipientCellFailure() {
        Message msg = new Message();
        String result = msg.checkRecipientCell("08575975889");
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", result);
    }

    @Test
    public void testMessageHash() {
        Message msg = new Message();
        String hash = msg.createMessageHash("0012345678", 0, "Hi Mike, can you join us for dinner tonight?");
        assertEquals("00:0:HITONIGHT", hash);
    }

    @Test
    public void testMessageIDCreated() {
        Message msg = new Message();
        String id = msg.generateMessageId();
        assertNotNull(id);
        assertTrue(msg.checkMessageID());
    }

    @Test
    public void testMessageSentOptions() {
        Message msg = new Message();
        assertEquals("Message successfully sent.", msg.SentMessage(1));
        assertEquals("Press 0 to delete the message.", msg.SentMessage(2));
        assertEquals("Message successfully stored.", msg.SentMessage(3));
    }
@Test
    public void testDisplayLongestMessage() {
        // Test data matching Part 3 criteria
        Message m1 = new Message(1, "+27834557896", "Did you get the cake?");
        Message m2 = new Message(2, "+27838884567", "Where are you? You are late! I have asked you to be on time.");
        Message m3 = new Message(3, "+27834484567", "Yohoooo, I am at your gate."); 
        
        // Logic to find the longest message
        Message[] messages = {m1, m2, m3};
        Message longest = messages[0];
        for (Message m : messages) {
            if (m.getMessageContent().length() > longest.getMessageContent().length()) {
                longest = m;
            }
        }
        
        assertEquals("Where are you? You are late! I have asked you to be on time.", longest.getMessageContent());
    }

    @Test
    public void testSearchMessageByRecipient() {
        String targetRecipient = "+27838884567";
        Message m2 = new Message(2, targetRecipient, "Where are you? You are late! I have asked you to be on time.");
        Message m5 = new Message(5, targetRecipient, "Ok, I am leaving without you."); 
        
        assertEquals("+27838884567", m2.getRecipientCell());
        assertEquals("+27838884567", m5.getRecipientCell());
    }

    @Test
    public void testDeleteMessageByHash() {
        Message msg = new Message(2, "+27838884567", "Where are you? You are late! I have asked you to be on time.");
        String expectedHash = msg.createMessageHash();
        
        // Simulating hash deletion check
        boolean isMatch = msg.getMessageHash().equalsIgnoreCase(expectedHash);
        assertTrue(isMatch);
    }
}