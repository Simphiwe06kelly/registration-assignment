/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.registration3;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author RC_Student_lab
 */
public class MessageTest {

    private Message messageObj;
    private final int testSize = 5;

    @BeforeAll
    public void setUp() {
        messageObj = new Message(testSize);

        // Pre-fill test data for index 0
        messageObj.setRecipientCell("0834567890", 0);
        String generatedID = messageObj.generateMessageID(0);
        messageObj.setMessage("Hello world, test message!", generatedID, 0);
        messageObj.createMessageHash(generatedID, "Hello world, test message!", 0);
    }

    
    @Test
    public void testGenerateMessageIDLengthAndUniqueness() {
        String id = messageObj.getMessageID(0);
        assertNotNull(id);
        assertTrue(id.length() <= 10);
    }

    @Test
    public void testCheckRecipientCellInvalid() {
        messageObj.setRecipientCell("123456789012", 1); // Invalid: too long and not starting with 0
        int result = messageObj.checkRecipientCell(1);        
        assertEquals(0, result);
    } 

    @Test
    public void testMessageHashFormat() {
        String hash = messageObj.getMessageHash(0);
        assertNotNull(hash);
        assertTrue(hash.contains(":"));
        assertTrue(hash.matches("^[a-zA-Z0-9]{2}:\\d+:HELLO.*$"));
    }

    @Test
    public void testSearchMessageByIDFound() {
        String id = messageObj.getMessageID(0);
        String result = messageObj.searchMessageByID(id);
        assertTrue(result.contains("Message ID: " + id));
    }

    @Test
    public void testSearchMessageByIDNotFound() {
        String result = messageObj.searchMessageByID("nonexistentID");
        assertEquals("Message ID nonexistentID not found.", result);
    }

    @Test
    public void testSearchMessagesByRecipientFound() {
        String result = messageObj.searchMessagesByRecipient("0834567890");
        assertTrue(result.contains("Messages sent to 0834567890:"));
    }

    @Test
    public void testSearchMessagesByRecipientNotFound() {
        String result = messageObj.searchMessagesByRecipient("0000000000");
        assertEquals("No messages were sent to 0000000000", result);
    }

    @Test
    public void testGetLongestMessage() {
        String result = messageObj.getLongestMessage();
        assertTrue(result.contains("Longest Message"));
        assertTrue(result.contains("Hello world"));
    }

    @Test
    public void testDeleteMessageByHashFound() {
        String hash = messageObj.getMessageHash(0);
        String result = messageObj.deleteMessageByHash(hash);
        assertEquals("Message with hash " + hash + " deleted successfully.", result);
    }

    @Test
    public void testDeleteMessageByHashNotFound() {
        String result = messageObj.deleteMessageByHash("invalidHash");
        assertEquals("Message hash invalidHash not found.", result);
    }

    @Test
    public void testDisplayAllMessages() {
        // Only index 0 should have data
        String output = messageObj.displayAllMessages();
        assertTrue(output.contains("To: 0834567890"));
        assertTrue(output.contains("Content: Hello world, test message!"));
    }

    @Test
    public void testGenerateSentMessagesReport() {
        String report = messageObj.generateSentMessagesReport();
        assertTrue(report.contains("Message ID:"));
        assertTrue(report.contains("Message: Hello world, test message!"));
    }

    /**
     * Test of getMessageID method, of class Message.
     */
    @Test
    public void testGetMessageID() {
        System.out.println("getMessageID");
        int index = 0;
        Message instance = null;
        String expResult = "";
        String result = instance.getMessageID(index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRecipientCell method, of class Message.
     */
    @Test
    public void testGetRecipientCell() {
        System.out.println("getRecipientCell");
        int index = 0;
        Message instance = null;
        String expResult = "";
        String result = instance.getRecipientCell(index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMessageHash method, of class Message.
     */
    @Test
    public void testGetMessageHash() {
        System.out.println("getMessageHash");
        int index = 0;
        Message instance = null;
        String expResult = "";
        String result = instance.getMessageHash(index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMessage method, of class Message.
     */
    @Test
    public void testGetMessage() {
        System.out.println("getMessage");
        int index = 0;
        Message instance = null;
        String expResult = "";
        String result = instance.getMessage(index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotalMessages method, of class Message.
     */
    @Test
    public void testGetTotalMessages() {
        System.out.println("getTotalMessages");
        int expResult = 0;
        int result = Message.getTotalMessages();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMessageID method, of class Message.
     */
    @Test
    public void testSetMessageID() {
        System.out.println("setMessageID");
        String messageID = "";
        int index = 0;
        Message instance = null;
        instance.setMessageID(messageID, index);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRecipientCell method, of class Message.
     */
    @Test
    public void testSetRecipientCell() {
        System.out.println("setRecipientCell");
        String recipientCell = "";
        int index = 0;
        Message instance = null;
        instance.setRecipientCell(recipientCell, index);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMessageHash method, of class Message.
     */
    @Test
    public void testSetMessageHash() {
        System.out.println("setMessageHash");
        String messageHash = "";
        int index = 0;
        Message instance = null;
        instance.setMessageHash(messageHash, index);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMessage method, of class Message.
     */
    @Test
    public void testSetMessage() {
        System.out.println("setMessage");
        String messages = "";
        String messageID = "";
        int index = 0;
        Message instance = null;
        instance.setMessage(messages, messageID, index);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateMessageID method, of class Message.
     */
    @Test
    public void testGenerateMessageID() {
        System.out.println("generateMessageID");
        int index = 0;
        Message instance = null;
        String expResult = "";
        String result = instance.generateMessageID(index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkMessage method, of class Message.
     */
    @Test
    public void testCheckMessage() {
        System.out.println("checkMessage");
        String messageID = "";
        Message instance = null;
        boolean expResult = false;
        boolean result = instance.checkMessage(messageID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkRecipientCell method, of class Message.
     */
    @Test
    public void testCheckRecipientCell() {
        System.out.println("checkRecipientCell");
        int index = 0;
        Message instance = null;
        int expResult = 0;
        int result = instance.checkRecipientCell(index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createMessageHash method, of class Message.
     */
    @Test
    public void testCreateMessageHash() {
        System.out.println("createMessageHash");
        String messageID = "";
        String message = "";
        int index = 0;
        Message instance = null;
        String expResult = "";
        String result = instance.createMessageHash(messageID, message, index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of promptForMessage method, of class Message.
     */
    @Test
    public void testPromptForMessage() {
        System.out.println("promptForMessage");
        int index = 0;
        Message instance = null;
        instance.promptForMessage(index);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sendMessage method, of class Message.
     */
    @Test
    public void testSendMessage() {
        System.out.println("sendMessage");
        int index = 0;
        Message instance = null;
        String expResult = "";
        String result = instance.sendMessage(index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printMessage method, of class Message.
     */
    @Test
    public void testPrintMessage() {
        System.out.println("printMessage");
        Message instance = null;
        instance.printMessage();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of returnTotalMessages method, of class Message.
     */
    @Test
    public void testReturnTotalMessages() {
        System.out.println("returnTotalMessages");
        int expResult = 0;
        int result = Message.returnTotalMessages();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of storeMessage method, of class Message.
     */
    @Test
    public void testStoreMessage() {
        System.out.println("storeMessage");
        Message instance = null;
        instance.storeMessage();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchMessageByID method, of class Message.
     */
    @Test
    public void testSearchMessageByID() {
        System.out.println("searchMessageByID");
        String searchID = "";
        Message instance = null;
        String expResult = "";
        String result = instance.searchMessageByID(searchID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchMessagesByRecipient method, of class Message.
     */
    @Test
    public void testSearchMessagesByRecipient() {
        System.out.println("searchMessagesByRecipient");
        String searchCell = "";
        Message instance = null;
        String expResult = "";
        String result = instance.searchMessagesByRecipient(searchCell);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteMessageByHash method, of class Message.
     */
    @Test
    public void testDeleteMessageByHash() {
        System.out.println("deleteMessageByHash");
        String searchHash = "";
        Message instance = null;
        String expResult = "";
        String result = instance.deleteMessageByHash(searchHash);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
