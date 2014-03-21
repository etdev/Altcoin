package com.ericturnerdev.CryptsyTicker;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by ericturner on 2/5/14.  Get string from URL
 */
public class URLFetch {

    private  final String TAG = "URLFetch";

    public String getURL(String urlIn) throws IOException {


        //Log.i(TAG, "In URLFetch");
        URL url = new URL(urlIn);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        InputStream in = new BufferedInputStream(urlConnection.getInputStream());
        BufferedReader r = new BufferedReader(new InputStreamReader(in));
        StringBuilder total = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null){

            total.append(line);
            // Log.i(TAG, "aaa line: " + line);

        }

        return total.toString();
    }


    public String postURL(String urlIn, ArrayList<String> params) throws IOException {


        //Log.i(TAG, "In URLFetch");
        URL url = new URL(urlIn);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setDoInput(true);
        urlConnection.setDoOutput(true);
        urlConnection.setInstanceFollowRedirects(false);
        urlConnection.setRequestMethod("POST");
        urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        urlConnection.setRequestProperty("charset", "utf-8");
        //content-length?
        urlConnection.setUseCaches(false);

        DataOutputStream out = new DataOutputStream(urlConnection.getOutputStream());
        for(String s: params){ out.writeBytes(s); }
        out.flush();
        out.close();

        InputStream in = new BufferedInputStream(urlConnection.getInputStream());
        BufferedReader r = new BufferedReader(new InputStreamReader(in));
        StringBuilder total = new StringBuilder();
        String line;

        while ((line = r.readLine()) != null){

            total.append(line);
            Log.i(TAG, "aaa line: " + line);

        }

        urlConnection.disconnect();
        return total.toString();
    }


}
