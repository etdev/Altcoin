package com.ericturnerdev.CryptsyTicker;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;


public class MainActivity extends Activity {

    public TextView tv1;
    public TextView tv2;
    public TextView tv3;
    public TextView tv4;

    String TAG = "MainActivity";

    //Check for JSON failure
    boolean jsonE;

    public ArrayList<TradePair> Pairs;

    //Exchange API URLs:
    public final String CRYPTSY_API = "http://pubapi.cryptsy.com/api.php?method=marketdatav2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        //Initialize Pairs
        Pairs = new ArrayList<TradePair>();

        setContentView(R.layout.fragment_main2);
        this.setTitle("Main");

        //**** Change this stuff
        new Cryptsy().execute(CRYPTSY_API, "doge", "btc");

    } //End onCreate()

    //Place items on the action bar
    public boolean onCreateOptionsMenu(Menu menu){
        //inflate the menu items
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);


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

        String myMarketS; //EG "DOGE/BTC"
        String rawData; //store raw JSON data

        //For storing current data:
        int marketId, visible;
        Double lastTradePrice, currentBuy, currentSell, volume;
        String baseCoin, mainCoin, baseAPI;

        @Override
        protected Double doInBackground(String... params){

            //For tracking JSON Error
            jsonE = false;

            //baseAPI = API URL
            baseAPI = params[0];
            mainCoin = params[1];
            baseCoin = params[2];

            //the name of the market you want in Cryptsy's JSON style
            myMarketS = "" + mainCoin.toUpperCase() + "/" + baseCoin.toUpperCase();

            //First, connect to the api URL:
            try{

                /////***** CHANGE THIS!!! TESTING Only
                //rawData = new URLFetch().getURL("http://pubapi.cryptsy.com/api.php?method=singlemarketdata&marketid=120");
                rawData = new URLFetch().getURL(CRYPTSY_API);

            } catch(IOException e){
                Log.i(TAG, "aaa Couldn't load data from api");  }


            //Parse the JSON to get the values
            try{

                //A JSON Object containing the raw data
                JSONObject rawJ = new JSONObject(rawData);
                JSONObject returnJ = rawJ.getJSONObject("return"); //returned data
                JSONObject marketsJ = returnJ.getJSONObject("markets"); //markets

                //Run through the trade pairs
                for (int i=0; i<returnJ.length(); i++){

                    //Create iterator keys for whatever is in markets
                    Iterator<?> keys = marketsJ.keys();

                    //For all keys in markets:
                    while (keys.hasNext()){

                        //Get the current key
                        String currentKey = (String)keys.next();
                        //Log.i(TAG, "aaa key: " + currentKey);
                        //get the market JSONObject for that key
                        JSONObject myMarketJ = marketsJ.getJSONObject(currentKey);

                        //Get the data for the given market
                        //TradePair(int marketId, double lastTradePrice, double currentBuy, double currentSell, double volume, String baseCoin, String mainCoin, int visible)
                        marketId = myMarketJ.getInt("marketid");
                        lastTradePrice = myMarketJ.getDouble("lasttradeprice");

                        //Check if currentBuy is null
                        try{
                        currentBuy = myMarketJ.getJSONArray("buyorders").getJSONObject(0).getDouble("price");
                        } catch(JSONException e){ Log.i(TAG, "aaa currentBuy is null"); currentBuy=0.00; }

                        //Check if currentSell is null
                        try{
                            currentSell = myMarketJ.getJSONArray("sellorders").getJSONObject(0).getDouble("price");
                        } catch(JSONException e){ Log.i(TAG, "aaa currentBuy is null"); currentSell=0.00; }

                        volume = myMarketJ.getDouble("volume");
                        baseCoin = myMarketJ.getString("secondarycode");
                        mainCoin = myMarketJ.getString("primarycode");
                        visible = 0;

                        Log.i(TAG, "aaa markedId: " + marketId + ", " + mainCoin + "/" + baseCoin + " last: " + lastTradePrice + " buy: " + currentBuy + " sell: " + currentSell + " volume: " + volume);

                        //Put the data in a SQLite database
                        Pairs.add(new TradePair(marketId, lastTradePrice, currentBuy, currentSell, volume, baseCoin, mainCoin, visible));

                    }


                }

            } catch(JSONException e){ Log.i(TAG, "aaa JSON Exception: "); e.printStackTrace(); jsonE = true; }

            //AAAsAAAAAAAAPairs.add(new TradePair(1, mainCoin, baseCoin, lastTradePrice, currentBuyPrice, currentSellPrice, 500.0, 200.0, 150.0));
            return lastTradePrice;
        }

        protected void onPostExecute(Double d ){


            Log.i(TAG, "aaa We're in onPostExecute()");
            Log.i(TAG, "aaa jsonE is: " + jsonE);
            Collections.sort(Pairs, new Comparator<TradePair>() {

                public int compare(TradePair tp1, TradePair tp2) {

                    return tp1.getMainCoin().compareTo(tp2.getMainCoin());

                }

            }) ;

            //Here is where we want to store the data into MySQLite
            populateListView();

        }

    }

    private void populateListView(){

        ArrayAdapter<TradePair> adapter = new MyListAdapter();
        ListView list = (ListView)findViewById(R.id.fragment_list_view);
        list.setAdapter(adapter);

    }

    private class MyListAdapter extends ArrayAdapter<TradePair>{

        public MyListAdapter(){

            super(MainActivity.this, R.layout.pair_item_view, Pairs);

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){


            View itemView = convertView;
            if (itemView == null){

                itemView = getLayoutInflater().inflate(R.layout.pair_item_view, parent, false);

            }


                //Find the Pair
                TradePair currentPair = Pairs.get(position);
                //double changeAbsolute = currentPair.getLastTradePrice() - ((currentPair.getHigh()+currentPair.getLow())/2);
                //double changePercent = ( (changeAbsolute)/currentPair.getLastTradePrice())*100;
                //double changePercentRound = Math.round(changePercent*1000000) / 1000000;


                //Fill the view
                TextView tv1 = (TextView)itemView.findViewById(R.id.moretop_tv1);
                tv1.setText(""+ currentPair.getMainCoin() + "/" + currentPair.getBaseCoin());
                tv1.setTextColor(Color.parseColor("#3b3b3b"));

                TextView tv2 = (TextView)itemView.findViewById(R.id.top_tv1);
                tv2.setText("" + currentPair.getLastTradePrice());

                TextView tv3 = (TextView)itemView.findViewById(R.id.top_tv2);


                /*
                if (changeAbsolute > 0) { tv3.setTextColor(Color.parseColor("#339c2b"));
                    tv3.setText("(+" + changePercentRound + "%)");}
                else{tv3.setTextColor(Color.parseColor("#dc1616"));
                    tv3.setText("(" + changePercentRound + "%)"); }

                TextView tv4 = (TextView)itemView.findViewById(R.id.bot_tv1);
                tv4.setText("Buy: " + currentPair.getCurrentBuyPrice());

                TextView tv5 = (TextView)itemView.findViewById(R.id.bot_tv2);
                tv5.setText("Sell: " + currentPair.getCurrentSellPrice());
                */



                return itemView;

           // }

        }

    }

}


