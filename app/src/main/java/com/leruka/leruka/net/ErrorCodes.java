package com.leruka.leruka.net;

/**
 * This is a copy of the file from the lerukaServer. Theses codes are returned in case of an error.
 */
public class ErrorCodes {

    // Request
    public final static int REQUEST_CONTENT_TYPE_NOT_JSON = 101;

    // User
    public static final int USER_NAME_USED = 201;
    public static final int USER_NAME_INVALID = 202;
    public static final int USER_PASS_INVALID = 203;

    // Database
    public static final int DB_UNKNOWN_ERROR = 301;

    public static String getReadableError(com.leruka.protobuf.ErrorCodes.ErrorCode code) {
        switch (code) {
            case LOGIN_NAME_UNKNOWN:
                return "Der Nickmane ist nicht vergeben";
            case LOGIN_PASS_WRONG:
                return "Das Passwort ist falsch";
            case USER_NAME_INVALID:
                return "Der Nickname ist ungültig";
            case USER_PASS_INVALID:
                return "Das Passwort ist ungültig";
            case REGISTER_NAME_USED:
                return "Der Nickname wird bereits verwendet";
            case REQUEST_WRONG_CONTENT_TYPE:
                return "Es ist ein Fehler bei der Kommunikation aufgetreten (" + code.name() + ")";
            case DB_UNKNOWN_ERROR:
                return "Es ist ein Serverfehler aufgetreten";
            default:
                return "Es ist ein unbekannter Fehler aufgetreten (" + code.name() + ")";
        }
    }

}
