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
}