package com.leruka.leruka.net;

import de.leifb.objectJson.Json;

/**
 * Created by leif on 23.11.15.
 */
public class ResponseObject {

    private int statusCode;
    private int errorCode;
    private boolean success;
    private Json responseJson;

    protected ResponseObject() {
        this(200, null);
    }

    protected ResponseObject(int statusCode, Json responseJson) {
        this(statusCode, 0, true, responseJson);
    }

    protected ResponseObject(int statusCode, int errorCode, boolean success, Json responseJson) {
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.success = success;
        this.responseJson = responseJson;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Json getResponseJson() {
        return responseJson;
    }

    public void setResponseJson(Json responseJson) {
        this.responseJson = responseJson;
    }
}
