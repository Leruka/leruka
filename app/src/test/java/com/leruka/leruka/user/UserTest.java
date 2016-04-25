package com.leruka.leruka.user;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    private User user;
    private static String HASH_TEST = "9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08";

    @Before
    public void setUp() throws Exception {
        this.user = new User(
                "test",
                HASH_TEST
        );
    }

    @Test
    public void testCreation() {
        this.user = new User("test2", HASH_TEST);
        assertEquals("test2", this.user.getUserName());
        assertEquals(HASH_TEST, this.user.getPasswordHash());
    }

    @Test
    public void testSessionID() {
        User.setSessionID("test");
        assertEquals("test", User.getSessionID());
    }

    @Test
    public void testSetCurrentUser() {
        User user2 = new User("Testuser", HASH_TEST);
        user.setCurrentUser(user2);
        assertEquals(user.getCurrentUser(), user2);
    }

    @Test
    public void testUpdateValid() {
        boolean valid = true;
        user.updateValid(valid);
        assertTrue(user.hasValidUser());
    }

    @Test
    public void testIsValid() {
        assertTrue(user.isValid(user));
        assertTrue(user.isValid());
        User user2 = null;
        assertFalse(user2.isValid(user2));
        User user3 = new User("hallodudadudadudadu", HASH_TEST);
        assertFalse(user3.isValid(user3));
        User user4 = new User("test4", "test");
        assertFalse(user4.isValid(user4));
    }

    @Test
    public void testSha256() {
        String input = null;
        //assertEquals("e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855", user.sha256(input));

        String input2 = "abc";
        assertEquals("ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad", user.sha256(input2));
    }

}