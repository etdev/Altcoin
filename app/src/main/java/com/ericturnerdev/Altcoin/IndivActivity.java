package com.ericturnerdev.Altcoin;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Code for Individual Trade Pair Activity
 */
public class IndivActivity extends Activity {


    public String CRYPTSY_API = "http://pubapi.cryptsy.com/api.php?method=singlemarketdata&marketid=";
    public static int marketId;
    public static double volume;


    //public String TAG = "IndivActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        try{
        marketId = getIntent().getExtras().getInt("marketId");
        }catch (NullPointerException e){ }
        volume = getIntent().getExtras().getDouble("volume");

        new Cryptsy(marketId).execute();

        setContentView(R.layout.indiv_activity);
        setTitle("" + Pairs.getMarket(marketId).getPrimaryname() + "/" + Pairs.getMarket(marketId).getPrimarycode());

        //Get marketId
        /*
        Log.i(TAG, "marketId is: " + marketId);
        Log.i(TAG, "ddd Volume: " + volume);
        setContentView(R.layout.indiv_activity);
        setTitle("" + Pairs.getMarket(marketId).getPrimaryname() + "/" + Pairs.getMarket(marketId).getPrimarycode());
        FragmentManager fm = getFragmentManager();
        IndivFragmentChart ifc = new IndivFragmentChart();
        IndivFragmentBot ifb = new IndivFragmentBot();

        ifc.marketId = marketId;

        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.indivChart, ifc);
        ft.add(R.id.indivBot, ifb);
        ft.commit();
        */

    }

    public class Cryptsy extends AsyncTask<String, Void, Double> {

        public int marketId;
        String API_URL;

        public Cryptsy(int marketId) {

            this.marketId = marketId;
            API_URL = CRYPTSY_API;

        }

        public void getData() {

            String rawData = null;
            String fullURL = API_URL + "" + marketId;
            //Log.i(TAG, "fullURL: " + fullURL);
            int i = 0;
            boolean apiSuccess = false;

            while (!apiSuccess && i < 20) {
                apiSuccess = true;
                try {
                    rawData = new URLFetch().getURL(fullURL);
                    //Log.i(TAG, "GET rawData: " + rawData);

                } catch (IOException e) {
                    //Log.e(TAG, "Couldn't load data from api.  i is: " + i);
                    i++;
                    apiSuccess = false;
                }
            }

            if (apiSuccess){

                try {

                    //Log.i(TAG, "rawData: " + rawData);
                    Gson gson = new GsonBuilder().serializeNulls().create();
                    JSONObject returnJ = new JSONObject(rawData).getJSONObject("return");
                    //Log.i(TAG, "returnJaaa: " + returnJ);
                    JSONObject marketsJ = returnJ.getJSONObject("markets");
                    //Log.i(TAG, "marketsJ: " + marketsJ);
                    JSONObject singleMarketJ = marketsJ.getJSONObject(Pairs.getMarket(marketId).getSecondarycode().toUpperCase());
                    //Log.i(TAG, "singleMarketJaaa : " + singleMarketJ);

                    Market currentMarket = gson.fromJson(singleMarketJ.toString(), Market.class);

                    //Log.i(TAG, "currentMarket lastTradePrice: " + currentMarket.getLasttradeprice());
                    // Log.i(TAG, "currentMarket primarycode: " + currentMarket.getPrimarycode());
                    //Log.i(TAG, "currentMarket secondarycode: " + currentMarket.getSecondarycode());
                    Pairs.getMarket(currentMarket.getSecondarycode(), currentMarket.getPrimarycode()).setBuyorders(currentMarket.getBuyorders());
                    Pairs.getMarket(currentMarket.getSecondarycode(), currentMarket.getPrimarycode()).setSellorders(currentMarket.getSellorders());

                } catch (JSONException e) {
                    //Log.e(TAG, "JSON Exception! i is: " + i);
                    e.printStackTrace();
                    //Log.i(TAG, "Primarycode: " + Pairs.getMarket(_marketId).getPrimarycode());
                }

            }


        }

        protected Double doInBackground(String... params) {

            getData();
            return null;
        }

        protected void onPostExecute(Double d) {

            FragmentManager fm = getFragmentManager();
            IndivFragmentChart ifc = new IndivFragmentChart();
            IndivFragmentBot ifb = new IndivFragmentBot();

            ifc.marketId = marketId;

            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.indivChart, ifc);
            ft.add(R.id.indivBot, ifb);
            ft.commit();

        }

    }

}
