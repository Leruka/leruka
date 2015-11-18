package com.leruka.leruka.net;

import java.net.URL;

/**
 * Created by leif on 11.11.15.
 */
public class PostObject {

    private URL url;
    private byte[] content;
    private ContentType contentType;

    public PostObject(URL url, ContentType contentType, byte[] content) {
        this.url = url;
        this.content = content;
        this.contentType = contentType;
    }

    public URL getUrl() {
        return url;
    }

    public byte[] getContent() {
        return content;
    }

    public String getContentType() {
        return this.contentType.getString();
    }
}
