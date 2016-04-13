package com.leruka.leruka.net;

import android.os.AsyncTask;

import com.leruka.leruka.helper.Message;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by leif on 13.04.16.
 */
public abstract class HttpGet<T> extends AsyncTask<String, Integer, T> {


    protected T doInBackground(String... params) {
        //TODO Check connection state

        // Only use one PostObject
        if (params == null || params.length < 1) {
            return null;
        }

        // Get Params
        String url = params[0];

        // Start HTTP stuff
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();

            conn.setRequestProperty("Accept", "application/x-protobuf");
            conn.setRequestProperty("Content-Type", "application/x-protobuf");
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setDoInput(true);

            conn.connect();
            if (conn.getResponseCode() != 200) {
                Message.showErrorMessage("code " + conn.getResponseCode());
                return null;
            }

            return this.CreateResponseObject(conn.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    abstract protected T CreateResponseObject(InputStream in);

}
