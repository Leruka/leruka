package com.leruka.leruka.user;

import com.leruka.protobuf.ErrorCodes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leifb on 21.04.16.
 */
public class LoginResult {

    private boolean success;
    private String message;
    private List<ErrorCodes.ErrorCode> errorCodes;

    public LoginResult(boolean success, String message) {
        this.success = success;
        this.message = message;
        this.errorCodes = new ArrayList<>(0);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ErrorCodes.ErrorCode> getErrorCodes() {
        return errorCodes;
    }
}
