package com.leruka.leruka.net;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Ruth Weber on 30.05.2016.
 */
public class ErrorCodesTest {

    ErrorCodes errorCodes;

    @Before
    public void setUp() {
        errorCodes = new ErrorCodes();
    }

    @Test
    public void testShowErrorMessageUserNameUnknown() {
        String res = ErrorCodes.getReadableError(com.leruka.protobuf.ErrorCodes.ErrorCode.LOGIN_NAME_UNKNOWN);
        assertEquals("Der Nickmane ist nicht vergeben", res);
    }

    @Test
    public void testShowErrorMessagePasswordWrong() {
        String res = ErrorCodes.getReadableError(com.leruka.protobuf.ErrorCodes.ErrorCode.LOGIN_PASS_WRONG);
        assertEquals("Das Passwort ist falsch", res);
    }

    @Test
    public void testShowErrorMessageUserNameInvalid() {
        String res = ErrorCodes.getReadableError(com.leruka.protobuf.ErrorCodes.ErrorCode.USER_NAME_INVALID);
        assertEquals("Der Nickname ist ungültig", res);
    }

    @Test
    public void testShowErrorMessagePasswordInvalid() {
        String res = ErrorCodes.getReadableError(com.leruka.protobuf.ErrorCodes.ErrorCode.USER_PASS_INVALID);
        assertEquals("Das Passwort ist ungültig", res);
    }

    @Test
    public void testShowErrorMessageUserNameUsed() {
        String res = ErrorCodes.getReadableError(com.leruka.protobuf.ErrorCodes.ErrorCode.REGISTER_NAME_USED);
        assertEquals("Der Nickname wird bereits verwendet", res);
    }


    @Test
    public void testShowErrorMessageRequestWrongContentType() {
        com.leruka.protobuf.ErrorCodes.ErrorCode code = com.leruka.protobuf.ErrorCodes.ErrorCode.REQUEST_WRONG_CONTENT_TYPE;
        String res = ErrorCodes.getReadableError(com.leruka.protobuf.ErrorCodes.ErrorCode.REQUEST_WRONG_CONTENT_TYPE);
        assertEquals("Es ist ein Fehler bei der Kommunikation aufgetreten (" + code.name() + ")", res);
    }

    @Test
    public void testShowErrorMessageDBError() {
        String res = ErrorCodes.getReadableError(com.leruka.protobuf.ErrorCodes.ErrorCode.DB_UNKNOWN_ERROR);
        assertEquals("Es ist ein Serverfehler aufgetreten", res);
    }
}