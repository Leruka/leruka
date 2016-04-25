package com.leruka.leruka.user;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kfrank on 25.04.2016.
 */
public class LoginTest {

    Login login;

    @Before
    public void setUp() throws Exception {
        this.login = new Login();
    }

    @Test
    public void testLogin() {
        String name = "hugo";
        String pw = "lustig";
        User user = new User(name, pw);

        login.login(name, pw);
        assertEquals(user, user.getCurrentUser());

    }

}