package com.leruka.leruka.net;

import android.os.AsyncTask;

import com.leruka.leruka.helper.Message;
import com.leruka.leruka.user.Register;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by leif on 11.11.15.
 */
public class HttpPost extends AsyncTask<PostObject, Integer, String> {


    @Override
    protected String doInBackground(PostObject... params) {
        //TODO Check connection state

        // Only use one PostObject
        if (params == null || params.length < 1) {
            // MÖÖP
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
            conn.setRequestProperty("Content-Type", params[0].getContentType());
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.connect();
            conn.getOutputStream().write(content);

            // read the response
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                Message.showErrorMessage("Bad response Code: " + responseCode);
                return null;
            }

            InputStream in = conn.getInputStream();
            StringBuilder sb = new StringBuilder();
            byte[] b = new byte[1024];
            while ( in.read(b) != -1)
                sb.append(new String(b));
            conn.disconnect();
            return sb.toString();
        } catch (IOException e) {
            return null;
        }
    }

}
