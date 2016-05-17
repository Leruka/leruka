package com.leruka.leruka.user;

import com.leruka.leruka.helper.Message;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by leif on 09.11.15.
 */
public class LUser {

    // Static
    // Attributes
    private static LUser currentUser;
    private static String sessionID;
    private static boolean isCurrentValid;

    private static final int USERNAME_MIN_LENGTH = 1;
    private static final int USERNAME_MAX_LENGTH = 16;
    private static final int PASS_LENGHTH = 64;

    // Methods
    public static LUser getCurrentUser() {
        return currentUser;
    }

    public static String getSessionID() {
        return sessionID;
    }

    public static void setSessionID(String sessionID) {
        LUser.sessionID = sessionID;
    }

    public static void setCurrentUser(LUser currentUser) {
        LUser.currentUser = currentUser;
    }

    public static boolean hasValidUser() {
        return isCurrentValid;
    }

    protected static void updateValid(boolean valid) {
        isCurrentValid = valid;
    }

    /**
     * Tests, if the given credentials are syntactical correct. Does not check, if the password is
     * actual the correct one.
     * @param user The user to perform the test on
     * @return true, if its valid, else false
     */
    public static boolean isValid(LUser user) {
        // Check for null
        if (user == null) return false;
        String name = user.getUserName();
        String pass = user.getPasswordHash();
        // Check length
        if (name.length() < USERNAME_MIN_LENGTH || name.length() > USERNAME_MAX_LENGTH)
            return false;
        if (pass.length() != 64) return false;


        return true;
    }

    public static boolean isValid() {
        return isValid(currentUser);
    }

    // Instance
    // Attributes
    private String userName;
    private String passwordHash;

    public LUser(String userName, String passwordHash) {
        this.userName = (userName == null) ? "" : userName;
        this.passwordHash = (passwordHash == null) ? "" : passwordHash;
    }

    public String getUserName() {
        return this.userName;
    }

    protected String getPasswordHash() {
        return this.passwordHash;
    }

    public static String sha256(String input) {
        if (input == null) input = "";
        try {
            MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
            byte[] result = mDigest.digest(input.getBytes()); // may throw null pointer
            StringBuilder sb = new StringBuilder();
            for (byte aResult : result) {
                sb.append(Integer.toString((aResult & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            Message.showErrorMessage("Failed hashing your Password. Cannot log in");
            // won't happen
        } catch (NullPointerException e) {
            Message.showErrorMessage("Failed hashing your Password. Cannot log in");
        }
        return null;
    }

    public static void logout() {
        currentUser = null;
        sessionID = null;
        updateValid(false);
    }

}

