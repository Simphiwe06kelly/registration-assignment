/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.registration2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    private Message message1;
    private Message message2;

    @BeforeEach
    public void setUp() {
        // Initialize your Message instances with example data
        message1 = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight");
        message2 = new Message("08575975889", "Hi Keegan, did you recieve the payment?");
    }

    @Test
    public void testGetMessageID() {
        String id1 = message1.getMessageID();
        assertNotNull(id1, "Message ID should not be null");
        assertTrue(id1.startsWith("MSG"), "Message ID should start with 'MSG'");
    }

    @Test
    public void testGetRecipientCell() {
        assertEquals("+27718693002", message1.getRecipientCell());
        assertEquals("08575975889", message2.getRecipientCell());
    }

    @Test
    public void testGetMessageHash() {
        String hash1 = message1.getMessageHash();
        assertNotNull(hash1);
        // Optionally check expected format if known
    }

    @Test
    public void testGetMessage() {
        assertEquals("Hi Mike, can you join us for dinner tonight", message1.getMessage());
        assertEquals("Hi Keegan, did you recieve the payment?", message2.getMessage());
    }

   @Test
public void testSendMessage() {
    String sendResult = message1.sendMessage();
    assertEquals("Message successfully sent.", sendResult);
}

// If storeMessage returns void, test by checking side effects or just call it:
@Test
public void testStoreMessage() {
    // If storeMessage is void, just call it and maybe verify other state changes
    message1.storeMessage();
    // no assert if void, or add assertions if state changes
}


    @Test
    public void testTotalMessages() {
        int total = Message.getTotalMessages();
        assertTrue(total >= 2, "Total messages sent should be at least 2");
    }

    // Add more tests for setters, createMessageHash, etc. similarly

}
