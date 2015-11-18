package com.leruka.leruka.net;

/**
 * Created by leif on 18.11.15.
 */
public enum ContentType {
    json("application/json");

    private String string;

    private ContentType(String s) {
        this.string = s;
    }

    protected String getString() {
        return this.string;
    }
}
