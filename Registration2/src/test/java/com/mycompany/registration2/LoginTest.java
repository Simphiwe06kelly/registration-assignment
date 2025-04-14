package com.mycompany.registration2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    @Test
    public void testValidUsername() {
        Login user = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976", "John", "Doe");
        assertTrue(user.checkUsername(user.getUsername()));
        String expected = "Welcome John Doe, it's great to see you again.";
        String actual = "Welcome " + user.getFirstName() + " " + user.getLastName() + ", it's great to see you again.";
        assertEquals(expected, actual);
    }

    @Test
    public void testInvalidUsername() {
        Login user = new Login("kyle!!!!!!!", "Ch&&sec@ke99!", "+27838968976", "Jane", "Smith");
        assertFalse(user.checkUsername(user.getUsername()));
        String expected = "Username is not formatted correctly, please ensure that it contains an underscore and is no more than 5 characters long.";
        String actual = !user.checkUsername(user.getUsername()) ?
                "Username is not formatted correctly, please ensure that it contains an underscore and is no more than 5 characters long." : "Welcome " + user.getFirstName() + " " + user.getLastName() + ", it's great to see you again.";
        assertEquals(expected, actual);
    }

    @Test
    public void testValidPassword() {
        boolean isValid = Login.isPasswordComplex("Ch&&sec@ke99!");
        assertTrue(isValid);
        assertEquals(true, isValid, "Password successfully captured.");
    }

    @Test
    public void testInvalidPassword() {
        boolean isValid = Login.isPasswordComplex("password");
        assertFalse(isValid);
        assertEquals(false, isValid, "Password is not correctly formatted.");
    }

    @Test
    public void testValidCellNumber() {
        Login user = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Liam", "Moyo");
        boolean isValid = user.checkCellNumber();
        assertTrue(isValid);
        assertEquals(true, isValid, "Cell phone number successfully captured.");
    }

    @Test
    public void testInvalidCellNumber() {
        Login user = new Login("kyl_1", "Ch&&sec@ke99!", "08966553", "Noah", "Nkosi");
        boolean isValid = user.checkCellNumber();
        assertFalse(isValid);
        assertEquals(false, isValid, "Cell phone number is not correctly formatted or does not contain international code.");
    }
}