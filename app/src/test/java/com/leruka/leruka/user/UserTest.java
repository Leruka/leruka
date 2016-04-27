package com.leruka.leruka.user;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    private LUser user;
    private static String HASH_TEST = "9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08";

    @Before
    public void setUp() throws Exception {
        this.user = new LUser(
                "test",
                HASH_TEST
        );
    }

    @Test
    public void testCreation() {
        this.user = new LUser("test2", HASH_TEST);
        assertEquals("test2", this.user.getUserName());
        assertEquals(HASH_TEST, this.user.getPasswordHash());
    }

    @Test
    public void testSessionID() {
        LUser.setSessionID("test");
        assertEquals("test", LUser.getSessionID());
    }

    @Test
    public void testSetCurrentUser() {
        LUser user2 = new LUser("Testuser", HASH_TEST);
        LUser.setCurrentUser(user2);
        assertEquals(LUser.getCurrentUser(), user2);
    }

    @Test
    public void testUpdateValid() {
        boolean valid = true;
        LUser.updateValid(valid);
        assertTrue(LUser.hasValidUser());
    }

    @Test
    public void testIsValid() {
        assertTrue(LUser.isValid(user));
        assertTrue(LUser.isValid());
        LUser user2 = null;
        assertFalse(LUser.isValid(user2));
        LUser user3 = new LUser("hallodudadudadudadu", HASH_TEST);
        assertFalse(LUser.isValid(user3));
        LUser user4 = new LUser("test4", "test");
        assertFalse(LUser.isValid(user4));
    }

    @Test
    public void testSha256() {
        String input = null;
        //assertEquals("e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855", user.sha256(input));

        String input2 = "abc";
        assertEquals("ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad", user.sha256(input2));
    }

}