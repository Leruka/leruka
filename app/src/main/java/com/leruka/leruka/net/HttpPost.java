package com.leruka.leruka.net;

import android.os.AsyncTask;

import com.leruka.leruka.helper.Message;
import com.leruka.leruka.user.Register;
import com.leruka.protobuf.User;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import de.leifb.objectJson.Json;

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

            return this.CreateResponseObject(in);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    abstract protected T CreateResponseObject(InputStream in);
}
