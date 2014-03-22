package com.ericturnerdev.CryptsyTicker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class MainActivity extends Activity {

    String TAG = "MainActivity";

    BasicNameValuePair nvp;

    Context mContext;

    //Exchange API URLs:
    public final String CRYPTSY_API = "http://pubapi.cryptsy.com/api.php?method=singlemarketdata&marketid=";
    public final String CRYPTOCOIN_API = "http://www.cryptocoincharts.info/v2/api/tradingPairs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.setTitle("Altcoin");
        super.onCreate(savedInstanceState);
        //If Pairs doesn't exist
        new Pairs();
        //Log.i(TAG, "aaa onCreate CALLED");


        /*
        ArrayList<NameValuePair> testList = new ArrayList<NameValuePair>();
        testList.add(new BasicNameValuePair("pairs", "doge_btc"));
        */

        /*Testing
        new APIData(this, "http://www.cryptocoincharts.info/v2/api/tradingPairs", true, testList).execute();
        ArrayList<NameValuePair> testList2 = new ArrayList<NameValuePair>();
        testList2.add(new BasicNameValuePair("method", "singleorderdata"));
        testList2.add(new BasicNameValuePair("marketid", "132"));
        new APIData(this, "http://pubapi.cryptsy.com/api.php", false, testList).execute();
        */


    } //End onCreate()

    @Override
    protected void onStart() {


        super.onStart();
        mContext = this;
        //new Pairs();
        //Log.i(TAG, "aaa onStart() CALLED");


        //Check for SQLite Database
        DatabaseHandler db = new DatabaseHandler(this);
        //db.clearTable("visibility");
        int marketsCount = db.getMarketsCount();
        if (marketsCount == 0) Log.i(TAG, "Vis table is empty");
        else Log.i(TAG, "Vis table has " + marketsCount + " rows");

        Cursor cur;

        //Set Pairs visibility from SQLite database
        cur = db.printMarkets();
        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {

                if (cur.getInt(1) == 1) {
                    Pairs.getMarket(cur.getInt(0)).setVisible(true);
                } else {
                    Pairs.getMarket(cur.getInt(0)).setVisible(false);
                }

            }
        }

        //new CryptoCoin(Pairs.getVisibleMarkets()).execute();

        String marketLabel = "";

        for (Market m : Pairs.getVisibleMarkets()) {


            /*EDIT THIS*/
            marketLabel += m.getSecondarycode().toLowerCase() + "_" + m.getPrimarycode().toLowerCase() + ",";


        }

        if (marketLabel.length() > 0) {
            marketLabel = marketLabel.substring(0, marketLabel.length() - 1);
        }

        nvp = new BasicNameValuePair("pairs", marketLabel);

        /*
        nvp = new ArrayList<NameValuePair>();
        nvp.add(new BasicNameValuePair("marketid", "" + m.getMarketid()));
        new APIData(CRYPTSY_API, false, nvp, m.getSecondarycode(), m.getPrimarycode()).execute();
        */

        //Display Visible Pairs
        if (Pairs.getVisibleMarkets().size() > 0) {
            setContentView(R.layout.fragment_main2);
            populateListView();
            Log.i(TAG, "getvisible is greater than 0!");
            new CryptoCoin(marketLabel).execute();
        } else {
            setContentView(R.layout.init_splash);
            Log.i(TAG, "getvisible is not greater than 0!");
        }


    }

    @Override
    protected void onStop() {

        super.onStop();
        DatabaseHandler db = new DatabaseHandler(this);
        //db.clearTable("visibility");
        //db.addVis(Pairs.getMarket(132), 1);
        //db.dropTable("visibility");
        //Log.i(TAG, "aaa onStop CALLED");
        db.close();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //Log.i(TAG, "aaa onRestart CALLED");

    }

    @Override
    protected void onResume() {

        //Log.i(TAG, " aaa onResume CALLED");
        super.onResume();

    }

    //Place items on the action bar
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate the menu items
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        inflater.inflate(R.menu.refresh, menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        super.onSaveInstanceState(savedInstanceState);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //Handle presses on the action bar items
        switch (item.getItemId()) {

            case R.id.action_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);

            case R.id.menu_refresh:
                //new Pairs();
                this.onResume();
                //new Cryptsy(this).execute(CRYPTSY_API);


        }

        return true;
    }


    public void populateListView() {


        ListView list = (ListView) findViewById(R.id.fragment_list_view);
        list.setAdapter(new PairAdapter(mContext, R.layout.pair_item_view, Pairs.getVisibleMarkets()));
        list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Log.i(TAG, "List item clicked");


            }

        });


    }

    /*
        public class APIData extends AsyncTask<String, Void, Double> {

            public String TAG = "APIData";
            public String API_URL;
            public boolean isPost;
            public ArrayList<NameValuePair> mParams;
            String primaryCode;
            String secondaryCode;
            boolean Cryptsy;

            public APIData(String api_url, boolean p, BasicNameValuePair n) {


                API_URL = api_url;
                isPost = p;
                mParams = params;
                if (api_url.equals(CRYPTSY_API)) {
                    Cryptsy = true;
                } else {
                    Cryptsy = false;
                }
                //Log.i(TAG, "CRYPTSY IS " + Cryptsy);
                primaryCode = primary;
                secondaryCode = secondary;

            }

            //Get raw data from URL
            protected String getData() {


                String rawData = null;
                String fullURL;
                int i = 0;
                boolean apiSuccess = false;
                Market currentMarket = new Market();


                //Get the data from the API
                while (!apiSuccess && i < 20) {
                    apiSuccess = true;
                    try {
                        //If GET
                        if (!isPost) {

                            fullURL = API_URL + mParams.get(0).getValue();
                            //Log.i(TAG, "GET FULLURL: " + fullURL);
                            for (int j = 1; j < mParams.size(); j++) {
                                fullURL = fullURL + "&" + mParams.get(j);
                                //Log.i(TAG, "GET fullURL: " + fullURL);
                            }
                            rawData = new URLFetch().getURL(fullURL);
                            //Log.i(TAG, "GET rawData: " + rawData);
                        }
                        //if post
                        else {
                            rawData = new URLFetch().postURL(API_URL, mParams);
                            //Log.i(TAG, "POST rawData: " + rawData);

                        }

                    } catch (IOException e) {
                        //Log.e(TAG, "aaa Couldn't load data from api.  i is: " + i);
                        i++;
                        apiSuccess = false;
                    }
                }

                try {

                    //Here split up Cryptsy and CryptoCoin
                    if (!Cryptsy) {


                        Gson gson = new GsonBuilder().serializeNulls().create();
                        JSONArray resultsJ = new JSONArray(rawData);
                        //Log.i(TAG, "resultsJ is: " + resultsJ);
                        //Log.i(TAG, "CRYPTSY IS " + Cryptsy);


                        for (i = 0; i < resultsJ.length(); i++) {

                            JSONObject marketJ = resultsJ.getJSONObject(i);
                            //Log.i(TAG, "  marketJ is: " + marketJ);
                            currentMarket = gson.fromJson(marketJ.toString(), Market.class);
                            currentMarket.setSecondarycode(currentMarket.getId().substring(0, currentMarket.getId().indexOf("/")));
                            currentMarket.setPrimarycode(currentMarket.getId().substring(currentMarket.getId().indexOf("/") + 1, currentMarket.getId().length()));
                            //Log.i(TAG, "  currentMarket price: " + currentMarket.getPrice());
                            //Log.i(TAG, "  currentMarket label: " + currentMarket.getId());
                            //Log.i(TAG, "  currentMarket 24hr : " + currentMarket.getPrice_before_24h());
                            Log.i(TAG, " currentMarket volume: " + currentMarket.getVolume_btc());
                            //Log.i(TAG, " primaryCode: " + currentMarket.getPrimarycode() + " secondaryCode: " + currentMarket.getSecondarycode());

                            Pairs.getMarket(currentMarket.getPrimarycode(), currentMarket.getSecondarycode()).setPrice(currentMarket.getPrice());
                            Pairs.getMarket(currentMarket.getPrimarycode(), currentMarket.getSecondarycode()).setVolume_btc(currentMarket.getVolume_btc());
                            Pairs.getMarket(currentMarket.getPrimarycode(), currentMarket.getSecondarycode()).setPrice_before_24h(currentMarket.getPrice_before_24h());

                        }
                    } else {

                        //Log.i(TAG, "AAAAAA");

                        Gson gson = new GsonBuilder().serializeNulls().create();
                        JSONObject returnJ = new JSONObject(rawData).getJSONObject("return");
                        JSONObject marketsJ2 = returnJ.getJSONObject("markets");
                        JSONObject singleMarketJ = marketsJ2.getJSONObject((Pairs.getMarket(secondaryCode.toUpperCase(), primaryCode.toUpperCase()).getSecondarycode().toUpperCase()));
                        //JSONObject singleMarketJ = marketsJ2.getJSONObject("DOGE");
                        //Log.i(TAG, "singleMarketJ " + singleMarketJ);

                        //JSONObject marketsJ = new JSONObject(rawData).getJSONObject("return").getJSONObject("markets").getJSONObject(Pairs.getMarket(secondaryCode, primaryCode).getPrimarycode().toUpperCase());
                        //Log.i(TAG, "resultsJ is: " + marketsJ);
                        // Log.i(TAG, "CRYPTSY IS " + Cryptsy);


                        currentMarket = gson.fromJson(singleMarketJ.toString(), Market.class);

                        //Log.i(TAG, "currentMarket lastTradePrice: " + currentMarket.getLasttradeprice());
                        // Log.i(TAG, "currentMarket primarycode: " + currentMarket.getPrimarycode());
                        //Log.i(TAG, "currentMarket secondarycode: " + currentMarket.getSecondarycode());
                        Pairs.getMarket(currentMarket.getSecondarycode(), currentMarket.getPrimarycode()).setBuyorders(currentMarket.getBuyorders());
                        Pairs.getMarket(currentMarket.getSecondarycode(), currentMarket.getPrimarycode()).setSellorders(currentMarket.getSellorders());


                    }


                } catch (JSONException e) {
                    Log.e(TAG, "JSON Exception! i is: " + i);
                    e.printStackTrace();
                    //Log.i(TAG, "Primarycode: " + Pairs.getMarket(_marketId).getPrimarycode());
                    i++;
                    apiSuccess = false;
                }


                return rawData;
            }

            @Override
            protected Double doInBackground(String... params) {


                getData();
                return null;


            }

            protected void onPostExecute(Double d) {

                //Log.i(TAG, "from onPostExecute, dogecoin price: " + Pairs.getMarket(132).getPrice());
                populateListView();

            }

            //Cryptsy
            //Just take a string for a single marketId and get it using getURL
            //where the URL is CRYPTSY_API + marketId

            //CryptoCoin
            //Get data in form "doge_btc,vtc_btc,doge_btc ..."
            //Create a namevaluepair ("pairs", <the above string>)
            //Fetch all the data at once and store it into Pairs
        }

     */
    public class CryptoCoin extends AsyncTask<String, Void, Double> {

        BasicNameValuePair nvp;
        String pairsS;
        String API_URL = CRYPTOCOIN_API;

        public CryptoCoin(String s) {

            nvp = new BasicNameValuePair("pairs", s);
            pairsS = s;

        }

        public void getData() {

            String rawData = null;
            String fullURL;
            int i = 0;
            boolean apiSuccess = false;
            Market currentMarket;


            //Get the data from the API
            while (!apiSuccess && i < 20) {
                apiSuccess = true;
                try {

                    rawData = new URLFetch().postURL(API_URL, nvp);
                    //Log.i(TAG, "POST rawData: " + rawData);

                } catch (IOException e) {
                    //Log.e(TAG, "aaa Couldn't load data from api.  i is: " + i);
                    i++;
                    apiSuccess = false;
                }
                }

            try {

                Gson gson = new GsonBuilder().serializeNulls().create();
                JSONArray resultsJ = new JSONArray(rawData);
                //Log.i(TAG, "resultsJ is: " + resultsJ);
                //Log.i(TAG, "CRYPTSY IS " + Cryptsy);

                for (i = 0; i < resultsJ.length(); i++) {

                    JSONObject marketJ = resultsJ.getJSONObject(i);
                    //Log.i(TAG, "  marketJ is: " + marketJ);
                    currentMarket = gson.fromJson(marketJ.toString(), Market.class);
                    currentMarket.setSecondarycode(currentMarket.getId().substring(0, currentMarket.getId().indexOf("/")));
                    currentMarket.setPrimarycode(currentMarket.getId().substring(currentMarket.getId().indexOf("/") + 1, currentMarket.getId().length()));
                    //Log.i(TAG, "  currentMarket price: " + currentMarket.getPrice());
                    //Log.i(TAG, "  currentMarket label: " + currentMarket.getId());
                    //Log.i(TAG, "  currentMarket 24hr : " + currentMarket.getPrice_before_24h());
                    Log.i(TAG, " currentMarket volume: " + currentMarket.getVolume_btc());
                    //Log.i(TAG, " primaryCode: " + currentMarket.getPrimarycode() + " secondaryCode: " + currentMarket.getSecondarycode());

                    Pairs.getMarket(currentMarket.getPrimarycode(), currentMarket.getSecondarycode()).setPrice(currentMarket.getPrice());
                    Pairs.getMarket(currentMarket.getPrimarycode(), currentMarket.getSecondarycode()).setVolume_btc(currentMarket.getVolume_btc());
                    Pairs.getMarket(currentMarket.getPrimarycode(), currentMarket.getSecondarycode()).setPrice_before_24h(currentMarket.getPrice_before_24h());

                }

            } catch (JSONException e) {
                Log.e(TAG, "JSON Exception! i is: " + i);
                e.printStackTrace();
                //Log.i(TAG, "Primarycode: " + Pairs.getMarket(_marketId).getPrimarycode());
                i++;
                apiSuccess = false;
            }

        } //end getData method

        protected Double doInBackground(String... params) {

            getData();
            return null;
        }

        protected void onPostExecute(Double d) {

            populateListView();
            }


    } //end CryptoCoin class


}





