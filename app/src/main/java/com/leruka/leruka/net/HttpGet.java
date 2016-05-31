package com.leruka.leruka.net;

import android.os.AsyncTask;
import android.util.Log;

import com.leruka.leruka.main.Central;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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

            return this.createResponseObject(conn.getInputStream());
        } catch (IOException e) {
            Log.i(Central.LOG_TAG_MAIN, e.getMessage(), e);
            return null;
        }
    }

    abstract protected T createResponseObject(InputStream in);

}
