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


}