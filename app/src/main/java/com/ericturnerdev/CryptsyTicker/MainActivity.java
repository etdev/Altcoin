package com.ericturnerdev.CryptsyTicker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


public class MainActivity extends Activity {

    String TAG = "MainActivity";

    Context mContext;

    //I want to do all this via SQLite:
    //public ArrayList<TradePair> pairs;

    //Exchange API URLs:
    public final String CRYPTSY_API = "http://pubapi.cryptsy.com/api.php?method=marketdatav2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;

        setContentView(R.layout.fragment_main2);
        this.setTitle("Main");

        //**** Change this stuff
        new Cryptsy(this).execute(CRYPTSY_API);

    } //End onCreate()

    //Place items on the action bar
    public boolean onCreateOptionsMenu(Menu menu){
        //inflate the menu items
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){

        super.onSaveInstanceState(savedInstanceState);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        //Handle presses on the action bar items
        switch (item.getItemId()){

            case R.id.action_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);

        }

        return true;
    }


    /*Cryptsy Class*/
    public class Cryptsy extends AsyncTask<String, Void, Double> {


        //Basically this fetches data from the API and puts it in the SQLite Database
        public Cryptsy (Context context){

            mContext = context;

        }

        @Override
        protected Double doInBackground(String... params){

            String rawData = ""; //String representation of raw JSON Data
            JSONObject rawJ;
            JSONObject marketsJ;
            ArrayList<Market> tradePairs = new ArrayList<Market>();
            Gson gson = new GsonBuilder().serializeNulls().create();

            int i = 0;
            boolean apiSuccess = false;

            //Get the data from Cryptsy API
            while (!apiSuccess && i<10){
                try{
                apiSuccess=true;

                //rawData = string response from the API
                rawData = new URLFetch().getURL(CRYPTSY_API);

                } catch (IOException e){ Log.e(TAG, "aaa Couldn't load data from api.  i is: " + i);
                    i++;
                    apiSuccess = false;}
            }

            //Store rawData in JSONObject
            try{

                rawJ = new JSONObject(rawData);
                marketsJ = rawJ.getJSONObject("return").getJSONObject("markets");
                //DEBUG
               //Log.i(TAG, "rawJ: " + rawJ);
               Log.i(TAG, "marketsJ: " + marketsJ);
               //Log.i(TAG, "marketsJ length: " + marketsJ.length());

                //Make an ArrayList of Market Objects
                Iterator<String> iter = marketsJ.keys();
                while (iter.hasNext()){

                    try{
                        Market currentMarket = gson.fromJson(marketsJ.getJSONObject(iter.next()).toString(), Market.class);
                        tradePairs.add(currentMarket);
                    } catch(RuntimeException e){ Log.i(TAG, "RuntimeException: "); e.printStackTrace(); }
                    //Log.i(TAG, "currentMarket: " + currentMarket);

                }
                 //DEBUG
                //Log.i(TAG, "tradePairs: " + tradePairs);

            } catch (JSONException e){ Log.i(TAG, "JSON Error: "); e.printStackTrace(); }

            //Add your markets to SQLite:
            DatabaseHandler db = new DatabaseHandler(mContext);

            for (Market m : tradePairs){

                Log.i(TAG, "Adding pairs to SQLite");
                db.addPair(m);
                for (Market.TradeItem ti : m.getRecenttrades()){

                    db.addTrade(m, ti);

                }
                for (Market.OrderItem oi : m.getSellorders()){

                    db.addSell(m, oi);

                }
                for (Market.OrderItem oi : m.getBuyorders()){

                    db.addBuy(m, oi);

                }
                //db.addVis(m, 0); //Set to invisible initially

            }
            return null;

        }

        protected void onPostExecute(Double d ){

            DatabaseHandler db = new DatabaseHandler(mContext);
            String tempPairs = db.printPairs();
            Log.i(TAG, "In onPostExecute.  Pairs: " + tempPairs);
            //Log.i(TAG, "tradePairs: " + tradePairs);
            Log.i(TAG, "d is " + d);

        }

    }

    private void populateListView(){

        /* OLD CODE
        ArrayAdapter<TradePair> adapter = new MyListAdapter();
        ListView list = (ListView)findViewById(R.id.fragment_list_view);
        list.setAdapter(adapter);
        */

    }


}


