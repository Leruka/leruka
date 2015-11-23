package com.leruka.leruka.net;

import android.os.AsyncTask;

import com.leruka.leruka.helper.Message;
import com.leruka.leruka.user.Register;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import de.leifb.objectJson.Json;

/**
 * Created by leif on 11.11.15.
 */
public class HttpPost extends AsyncTask<PostObject, Integer, ResponseObject> {

    private static String USER_AGENT;

    static {
        USER_AGENT = "LerukaApp/0.1"; //TODO put version somewhere else
    }

    @Override
    protected ResponseObject doInBackground(PostObject... params) {
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
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-Type", params[0].getContentType());
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("User-Agent",USER_AGENT);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.connect();
            conn.getOutputStream().write(content);

            // read the response
            // Create Response
            ResponseObject responseObject = new ResponseObject();

            // Get input stream and status code
            InputStream in;
            int statusCode = conn.getResponseCode();
            responseObject.setStatusCode(statusCode);
            if (statusCode != 200) {
                in = conn.getErrorStream();
            }
            else {
                in = conn.getInputStream();
            }

            // Read the input stream
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder result = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null) {
                result.append(line);
            }
            // disconnect
            conn.disconnect();

            // Create the response Json
            responseObject.setResponseJson(new Json(result.toString()));

            // Set the error Code
            if (statusCode != 200) {
                responseObject.setErrorCode(
                        responseObject.getResponseJson().getInt("errorCode")
                );
            }

            // Set success
            responseObject.setSuccess(responseObject.getResponseJson().getBoolean("success"));
            return responseObject;
        } catch (IOException e) {
            return null;

        }
    }

}
