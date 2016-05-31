package com.leruka.leruka.net;

import android.os.AsyncTask;
import android.util.Log;

import com.leruka.leruka.main.Central;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by leif on 11.11.15.
 */
public abstract class HttpPost<T> extends AsyncTask<PostObject, Integer, T> {

    private static String USER_AGENT;

    static {
        USER_AGENT = "LerukaApp/0.1"; //TODO put version somewhere else
    }

    protected T doInBackground(PostObject... params) {
        //TODO Check connection state

        // Only use one PostObject
        if (params == null || params.length < 1) {
            return null;
        }

        // Get Params
        URL url = params[0].getUrl();
        byte[] content = params[0].getContent();

        // Start HTTP stuff
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "application/x-protobuf");
            conn.setRequestProperty("Content-Type", params[0].getContentType());
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("User-Agent",USER_AGENT);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.connect();
            conn.getOutputStream().write(content);

            // Get input stream and status code
            InputStream in;
            int statusCode = conn.getResponseCode();
            if (statusCode != 200) {
                in = conn.getErrorStream();
            }
            else {
                in = conn.getInputStream();
            }

            return this.createResponseObject(in);
        } catch (IOException e) {
            Log.i(Central.LOG_TAG_MAIN, e.getMessage(), e);
            return null;
        }
    }

    abstract protected T createResponseObject(InputStream in);
}
