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

    //Check for JSON failure
    boolean jsonE = false;

    public ArrayList<TradePair> Pairs;


    //current buy price:
    public double currentBuyPrice = 0.00000;
    //current sell price:
    public double currentSellPrice = 0.00000;
    //Current volume:
    public double volume = 0.00000;
    //Last trade price
    public double lastTradePrice = 0.00000;
    //current high
    public double high;
    //current low
    public double low;
    //sellOrders
    ArrayList<OrderItem> sellOrders;
    //buyOrders
    ArrayList<OrderItem> buyOrders;
    //recentTrades
    ArrayList<OrderItem> recentTrades;
    //visible
    boolean visible;

    ArrayList<Double> recentTradePrices;

    //main coin
    //public String mainCoin;
    //public String baseCoin;

    //Exchange API URLs:
    public final String CRYPTSY_API = "http://pubapi.cryptsy.com/api.php?method=marketdatav2";
    public final String COINEX_API = "https://coinex.pw/api/v2/trade_pairs";
    public final String BTER_API = "http://data.bter.com/api/1/tickers";
    public final String BTCE_API = "https://btc-e.com/api/3/ticker/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        //Initialize Pairs
        Pairs = new ArrayList<TradePair>();

        setContentView(R.layout.fragment_main2);
        this.setTitle("Main Page");

        //**** Change this stuff
        new Cryptsy().execute("cryptsy", CRYPTSY_API, "doge", "btc");

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

        private static final String TAG = "Cryptsy";
        String exchangeName;

        @Override
        protected Double doInBackground(String... params){
            jsonE = false;
            //baseAPI = API URL
            exchangeName = params[0].toUpperCase();
            String baseAPI = params[1];
            String mainCoin = params[2];
            String baseCoin = params[3];
            if(exchangeName.toUpperCase().equals("BTC-E")){ baseAPI = baseAPI + "" + mainCoin + "_" + baseCoin;}

            //the name of the market you want in Cryptsy's JSON style
            String myMarketS = "" + mainCoin.toUpperCase() + "/" + baseCoin.toUpperCase();
            //For storing the raw JSON data in String form
            String rawData = "";


            //execute( base_api_url , main_coin, base_coin)
            //So for example, execute("http://pubapi.cryptsy.com/api.php?method=marketdatav2", "vtc", "btc")

            //First, connect to the api URL:
            try{

                rawData = new URLFetch().getURL(baseAPI);

            } catch(IOException e){
                Log.i(TAG, "aaa Couldn't load data from api");  }

            //Format the JSON to get price, volume

            try{

                //A JSON Object containing the raw data
                JSONObject rawJ = new JSONObject(rawData);

                //CRYPTSY
                if (exchangeName.toUpperCase().equals("CRYPTSY")){

                    //Log.i(TAG, "aaa rawJ: " + rawJ.toString());
                    JSONObject returnJ = rawJ.getJSONObject("return"); //returned data

                    JSONObject marketsJ = returnJ.getJSONObject("markets");
                    //Log.i(TAG, "aaa marketsJ: " + marketsJ.toString());
                    //Log.i(TAG, "aaa marketsJ length: " + marketsJ.length());

                    //Run through the trade pairs
                    for (int i=0; i<returnJ.length(); i++){

                        //JSONObject myMarketJ = marketsJ.getJSONObject(myMarketS);
                        //Log.i(TAG, "aaa myMarketJ: " + myMarketJ.toString());
                        Iterator<?> keys = marketsJ.keys();

                        while (keys.hasNext()){

                            buyOrders = new ArrayList<OrderItem>();
                            sellOrders = new ArrayList<OrderItem>();
                            recentTrades = new ArrayList<OrderItem>();

                            String currentKey = (String)keys.next();
                            Log.i(TAG, "AAAaaaAAA key: " + currentKey);
                            JSONObject myMarketJ = marketsJ.getJSONObject(currentKey);
                            //Get the most recent data for your market:
                            currentSellPrice = myMarketJ.getJSONArray("sellorders").getJSONObject(0).getDouble("price");
                            Log.i(TAG, "....aaa currentSellPrice: " + currentSellPrice);
                            currentBuyPrice = myMarketJ.getJSONArray("buyorders").getJSONObject(0).getDouble("price");
                            Log.i(TAG, "....aaa currentBuyPrice: " + currentBuyPrice);
                            volume = myMarketJ.getDouble("volume");
                            Log.i(TAG, "....aaa volume is: " + volume);
                            lastTradePrice = myMarketJ.getDouble("lasttradeprice");
                            Log.i(TAG, "....aaa last trade price is: " + lastTradePrice);
                            mainCoin = myMarketJ.getString("primarycode");
                            baseCoin = myMarketJ.getString("secondarycode");

                            //So we know mainCoin, baseCoin, currentSellPrice, currentBuyPrice, lastTradePrice, volume
                            //Still need visible, sellOrders, recentTrades, buyOrders, high, and low
                            //Just set visible to false

                            //Set visible to false
                            visible = false;

                            //Fill arraylists
                            for (int j=0; j<myMarketJ.getJSONArray("buyorders").length(); j++){

                                buyOrders.add(new OrderItem(myMarketJ.getJSONArray("buyorders").getJSONObject(j).getDouble("price"), myMarketJ.getJSONArray("buyorders").getJSONObject(j).getDouble("quantity"), myMarketJ.getJSONArray("buyorders").getJSONObject(j).getDouble("total")));
                                sellOrders.add(new OrderItem(myMarketJ.getJSONArray("sellorders").getJSONObject(j).getDouble("price"), myMarketJ.getJSONArray("sellorders").getJSONObject(j).getDouble("quantity"), myMarketJ.getJSONArray("sellorders").getJSONObject(j).getDouble("total")));

                            }

                            for (int j=0; j<myMarketJ.getJSONArray("recenttrades").length(); j++){

                                recentTrades.add(new OrderItem(myMarketJ.getJSONArray("recenttrades").getJSONObject(j).getDouble("price"), myMarketJ.getJSONArray("recenttrades").getJSONObject(j).getDouble("quantity"), myMarketJ.getJSONArray("recenttrades").getJSONObject(j).getDouble("total")));

                            }

                            Collections.sort(recentTrades, new Comparator<OrderItem>() {

                                public int compare(OrderItem oi1, OrderItem oi2) {

                                    if (oi1.getPrice() > oi2.getPrice()) {
                                        return 1;
                                    }
                                    if (oi1.getPrice() < oi2.getPrice()) {
                                        return -1;
                                    } else {
                                        return 0;
                                    }

                                }


                            }) ;

                            ///CONTINUE WORKING HERE WITH HIGH AND LOW!
                            high = recentTrades.get(recentTrades.size()-1).getPrice();
                            low = recentTrades.get(0).getPrice();
                            Log.i(TAG, "aaa high: " + high + " aaa low: " + low);


                            //Here have an if statement  to set visible based on the checkbox value in settings
                            Pairs.add( new TradePair(true, mainCoin, baseCoin, lastTradePrice, currentBuyPrice, currentSellPrice, volume, high, low, buyOrders, sellOrders, recentTrades));


                        }




                        //Testing only



                    }

                }


            } catch(JSONException e){ Log.i(TAG, "aaa JSON Exception"); jsonE = true; }

            //AAAsAAAAAAAAPairs.add(new TradePair(1, mainCoin, baseCoin, lastTradePrice, currentBuyPrice, currentSellPrice, 500.0, 200.0, 150.0));
            return lastTradePrice;
        }

        protected void onPostExecute(Double d ){


            Log.i(TAG, "aaa We're in onPostExecute()");
            Log.i(TAG, "aaa jsonE is: " + jsonE);
            Collections.sort(Pairs, new Comparator<TradePair>(){

                public int compare(TradePair tp1, TradePair tp2){

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


            //if JSON Error (no data collected):
            if (jsonE) {

                TextView tv1 = (TextView)itemView.findViewById(R.id.moretop_tv1);
                Log.i("aaa", "aaa your jsonE code is being run");
                tv1.setText("Sync Failure!");
                tv1.setTextColor(Color.parseColor("#505050"));
                return itemView;

            }

            else{

                //Find the Pair
                TradePair currentPair = Pairs.get(position);
                double changeAbsolute = currentPair.getLastTradePrice() - ((currentPair.getHigh()+currentPair.getLow())/2);
                double changePercent = ( (changeAbsolute)/currentPair.getLastTradePrice())*100;
                double changePercentRound = Math.round(changePercent*1000000) / 1000000;


                //Fill the view
                TextView tv1 = (TextView)itemView.findViewById(R.id.moretop_tv1);
                tv1.setText(""+ currentPair.getMainCoin() + "/" + currentPair.getBaseCoin());
                tv1.setTextColor(Color.parseColor("#3b3b3b"));

                TextView tv2 = (TextView)itemView.findViewById(R.id.top_tv1);
                tv2.setText("" + currentPair.getLastTradePrice());

                TextView tv3 = (TextView)itemView.findViewById(R.id.top_tv2);

                if (changeAbsolute > 0) { tv3.setTextColor(Color.parseColor("#339c2b"));
                    tv3.setText("(+" + changePercentRound + "%)");}
                else{tv3.setTextColor(Color.parseColor("#dc1616"));
                    tv3.setText("(" + changePercentRound + "%)"); }

                TextView tv4 = (TextView)itemView.findViewById(R.id.bot_tv1);
                tv4.setText("Buy: " + currentPair.getCurrentBuyPrice());

                TextView tv5 = (TextView)itemView.findViewById(R.id.bot_tv2);
                tv5.setText("Sell: " + currentPair.getCurrentSellPrice());


                return itemView;

            }

        }

    }

}


