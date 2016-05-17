package com.leruka.leruka.user;

import com.leruka.protobuf.ErrorCodes;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by kfrank on 17.05.2016.
 */
public class LoginResultTest {

    LoginResult loginResult;

    @Before
    public void setUp() throws Exception {
        this.loginResult = new LoginResult(true, "Any funny login message");
    }


    @Test
    public void testIsSuccess() {
        assertTrue(loginResult.isSuccess());
        loginResult.setSuccess(false);
        assertFalse(loginResult.isSuccess());
    }

    @Test
    public void testGetMessage() {
        String message = loginResult.getMessage();
        assertEquals(message, "Any funny login message");
        loginResult.setMessage("Another funny message");
        message = loginResult.getMessage();
        assertEquals(message, "Another funny message");
    }

    @Test
    public void testGetErrorCodes() {
        List<ErrorCodes.ErrorCode> list = loginResult.getErrorCodes();
        assertEquals(list.size(), 0);
    }

}