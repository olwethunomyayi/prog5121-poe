import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTest {

    private Login login;

    @BeforeEach
    void setUp() {
        login = new Login("Kyle", "Smith");
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
    }

    // ---------- assertEquals tests ----------

    @Test
    void usernameCorrectlyFormatted_message() {
        assertEquals("Username successfully captured.",
                login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976").split("\n")[0]);
    }

    @Test
    void usernameIncorrectlyFormatted_message() {
        String expected = "Username is not correctly formatted; please ensure that your username "
                + "contains an underscore and is no more than five characters in length.";
        assertEquals(expected,
                login.registerUser("kyle!!!!!!!", "Ch&&sec@ke99!", "+27838968976").split("\n")[0]);
    }

    @Test
    void passwordMeetsComplexity_message() {
        assertEquals("Password successfully captured.",
                login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976").split("\n")[1]);
    }

    @Test
    void passwordDoesNotMeetComplexity_message() {
        String expected = "Password is not correctly formatted; please ensure that the password "
                + "contains at least eight characters, a capital letter, a number, and a special character.";
        assertEquals(expected,
                login.registerUser("kyl_1", "password", "+27838968976").split("\n")[1]);
    }

    @Test
    void cellPhoneCorrectlyFormatted_message() {
        assertEquals("Cell number successfully captured.",
                login.returnCellPhoneMessage("+27838968976"));
    }

    @Test
    void cellPhoneIncorrectlyFormatted_message() {
        assertEquals("Cell number is incorrectly formatted or does not contain an international code; "
                        + "please correct the number and try again.",
                login.returnCellPhoneMessage("08966553"));
    }

    @Test
    void loginSuccessfulMessage() {
        assertEquals("Welcome Kyle, Smith it is great to see you again.",
                login.returnLoginStatus(true));
    }

    @Test
    void loginFailedMessage() {
        assertEquals("Username or password incorrect, please try again.",
                login.returnLoginStatus(false));
    }

    // ---------- assertTrue / assertFalse tests ----------

    @Test
    void loginSuccessful() {
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    void loginFailed() {
        assertFalse(login.loginUser("kyl_1", "wrongPassword1!"));
    }

    @Test
    void usernameCorrectlyFormatted() {
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    void usernameIncorrectlyFormatted() {
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }

    @Test
    void passwordMeetsComplexity() {
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    void passwordDoesNotMeetComplexity() {
        assertFalse(login.checkPasswordComplexity("password"));
    }

    @Test
    void cellPhoneCorrectlyFormatted() {
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    void cellPhoneIncorrectlyFormatted() {
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }
}
