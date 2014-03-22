package com.ericturnerdev.CryptsyTicker;

import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ericturner on 2/5/14.  Get string from URL
 */
public class URLFetch {

    private final String TAG = "URLFetch";

    public String getURL(String urlIn) throws IOException {


        //Log.i(TAG, "urlIn: " + urlIn);
        URL url = new URL(urlIn);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        InputStream in = new BufferedInputStream(urlConnection.getInputStream());
        BufferedReader r = new BufferedReader(new InputStreamReader(in));
        StringBuilder total = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) {

            total.append(line);
            //Log.i(TAG, "aaa line: " + line);

        }

        return total.toString();
    }


    public String postURL(String urlIn, BasicNameValuePair n) throws IOException {

        URL url = new URL(urlIn);
        String stringParam = "";
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");

        stringParam += "" + n.getName() + "=" + n.getValue();

        //stringParam = stringParam.substring(0, stringParam.length()-1);

        con.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        out.writeBytes(stringParam);
        out.flush();
        out.close();

        int responseCode = con.getResponseCode();

        //Log.i(TAG, "POST request to URL: " + urlIn);
        //Log.i(TAG, "Post parameters: " + stringParam);
        //Log.i(TAG, "Response Code: " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream())
        );

        String line;
        StringBuffer response = new StringBuffer();
        while ((line = in.readLine()) != null) {

            response.append(line);

        }

        in.close();

        // Log.i(TAG, "rawData: " + response.toString());
        return response.toString();


    }


}
