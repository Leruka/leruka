package com.leruka.leruka.helper;

import com.leruka.leruka.net.ErrorCodes;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kfrank on 30.05.2016.
 */
public class MessageTest {

    Message msg;

    @Before
    public void setUp() {
        this.msg = new Message();
    }

    @Test
    public void testShowErrorMessageUserNameUsed() {
        msg.showErrorMessage(ErrorCodes.USER_NAME_USED);
    }

    @Test
    public void testShowErrorMessageUserNameInvalid() {
        msg.showErrorMessage(ErrorCodes.USER_NAME_INVALID);
    }

    @Test
    public void testShowErrorMessageUserPassInvalid() {
        msg.showErrorMessage(ErrorCodes.USER_PASS_INVALID);
    }

    @Test
    public void testShowErrorMessageDbUnknownError() {
        msg.showErrorMessage(ErrorCodes.DB_UNKNOWN_ERROR);
    }

    @Test
    public void testShowErrorMessageRequestContentTypeNotJson() {
        msg.showErrorMessage(ErrorCodes.REQUEST_CONTENT_TYPE_NOT_JSON);
    }

    @Test
    public void testShowMessage() {
        msg.showMessage("Message wird angezeigt");
    }


}