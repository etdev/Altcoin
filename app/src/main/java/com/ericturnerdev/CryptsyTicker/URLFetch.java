package com.ericturnerdev.CryptsyTicker;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
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


        Log.i(TAG, "urlIn: " + urlIn);
        URL url = new URL(urlIn);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        InputStream in = new BufferedInputStream(urlConnection.getInputStream());
        BufferedReader r = new BufferedReader(new InputStreamReader(in));
        StringBuilder total = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null){

            total.append(line);
            Log.i(TAG, "aaa line: " + line);

        }

        return total.toString();
    }


    public String postURL(String urlIn, ArrayList<String> params) throws IOException {


        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(urlIn);
        post.setEntity(new StringEntity(params.get(0)));
        HttpResponse response = client.execute(post);
        return EntityUtils.toString(response.getEntity());
    }


}
