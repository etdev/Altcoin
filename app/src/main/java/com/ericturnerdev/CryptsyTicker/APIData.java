package com.ericturnerdev.CryptsyTicker;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ericturner on 3/21/14.  Asynchronous code for getting data from an API
 */

public class APIData extends AsyncTask<String, Void, Double> {

    public Context mContext;
    public String TAG = "APIData";
    public String API_URL;
    public boolean isPost;
    public ArrayList mParams;

    public APIData(Context context, String api_url, boolean p, ArrayList params) {

        mContext = context;
        API_URL = api_url;
        isPost = p;
        mParams = params;

    }

    //Get raw data from URL
    protected String getData() {

        String rawData = null;
        String fullURL;
        int i = 0;
        boolean apiSuccess = false;

        //Get the data from the API
        while (!apiSuccess && i < 20) {
            apiSuccess = true;
            try {
                //If GET
                if (!isPost) {
                    fullURL = API_URL;
                    fullURL = API_URL + "?" + mParams.get(0);
                    for (int j = 1; j < mParams.size(); j++) {
                        fullURL = fullURL + "&" + mParams.get(j);
                        Log.i(TAG, "GET fullURL: " + fullURL);
                    }
                    rawData = new URLFetch().getURL(fullURL);
                    Log.i(TAG, "GET rawData: " + rawData);
                }
                //if post
                else {
                    rawData = new URLFetch().postURL(API_URL, mParams);
                    Log.i(TAG, "POST rawData: " + rawData);

                }

            } catch (IOException e) {
                Log.e(TAG, "aaa Couldn't load data from api.  i is: " + i);
                i++;
                apiSuccess = false;
            }
        }

        return rawData;
    }

    @Override
    protected Double doInBackground(String... params) {


        getData();
        return null;

    }

    protected void onPostExecute(Double d) {

        //CONTINUE WORKING HERE!

        //populateListView();
        Log.i(TAG, "from onPostExecute");
    }

}
