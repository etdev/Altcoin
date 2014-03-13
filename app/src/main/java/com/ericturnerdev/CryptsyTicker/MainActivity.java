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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class MainActivity extends Activity {

    String TAG = "MainActivity";

    Context mContext;

    //Exchange API URLs:
    public final String CRYPTSY_API = "http://pubapi.cryptsy.com/api.php?method=singlemarketdata&marketid=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        new Pairs();


        //Check for SQLite Database
        DatabaseHandler db = new DatabaseHandler(this);
        //db.clearTable("visibility");
        int marketsCount = db.getMarketsCount();
        if (marketsCount == 0) Log.i(TAG, "Vis table is empty yo!");
        else Log.i(TAG, "Vis table has " + marketsCount + " rows yo!");

        Cursor cur;

        Log.i(TAG, "onCreate CALLED");

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

        //Display Visible Pairs

        mContext = this;
        setContentView(R.layout.fragment_main2);
        this.setTitle("Altcoin Ticker");

    } //End onCreate()

    @Override
    protected void onStop() {

        super.onStop();
        DatabaseHandler db = new DatabaseHandler(this);
        //db.clearTable("visibility");
        //db.addVis(Pairs.getMarket(132), 1);
        //db.dropTable("visibility");
        Log.i(TAG, "onStop CALLED");
        db.close();

    }

    @Override
    protected void onResume() {

        Log.i(TAG, "onResume CALLED");
        super.onResume();
        new Cryptsy(this).execute(CRYPTSY_API);


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
                new Cryptsy(this).execute(CRYPTSY_API);


        }

        return true;
    }


    /*Cryptsy Class*/
    public class Cryptsy extends AsyncTask<String, Void, Double> {

        Market currentMarket;

        //Basically this fetches data from the API and puts it in the SQLite Database
        public Cryptsy(Context context) {

            mContext = context;

        }

        protected Market getData(int _marketId) {

            String rawData = ""; //String representation of raw JSON Data
            JSONObject marketsJ;
            Gson gson = new GsonBuilder().serializeNulls().create();

            int i = 0;
            boolean apiSuccess = false;

            //Get the data from Cryptsy API
            while (!apiSuccess && i < 20) {
                apiSuccess = true;
                try {

                    //rawData = string response from the API
                    rawData = new URLFetch().getURL(CRYPTSY_API + _marketId);
                    Log.i(TAG, "rawData: " + rawData);

                } catch (IOException e) {
                    Log.e(TAG, "aaa Couldn't load data from api.  i is: " + i);
                    i++;
                    apiSuccess = false;
                }


                try {

                    marketsJ = (new JSONObject(rawData)).getJSONObject("return").getJSONObject("markets").getJSONObject(Pairs.getMarket(_marketId).getSecondarycode());
                    currentMarket = gson.fromJson(marketsJ.toString(), Market.class);

                } catch (JSONException e) {
                    Log.e(TAG, "JSON Exception! i is: " + i);
                    //e.printStackTrace();
                    //Log.i(TAG, "Primarycode: " + Pairs.getMarket(_marketId).getPrimarycode());
                    i++;
                    apiSuccess = false;
                }

                Log.i(TAG, "currentMarket: " + currentMarket);
                Log.i(TAG, "ccc Volume for currentMarket: " + currentMarket.getVolume());

                //Log.i(TAG, "marketsJ: " + marketsJ.toString());

                //Fill in info in Pairs
                Pairs.getMarket(_marketId).setLasttradeprice(currentMarket.getLasttradeprice());
                Pairs.getMarket(_marketId).setVolume(currentMarket.getVolume());
                Pairs.getMarket(_marketId).setRecenttrades(currentMarket.getRecenttrades());
                Pairs.getMarket(_marketId).setSellorders(currentMarket.getSellorders());
                Pairs.getMarket(_marketId).setBuyorders(currentMarket.getBuyorders());

            }

            return currentMarket;


        }

        @Override
        protected Double doInBackground(String... params) {


            for (Market m : Pairs.getVisibleMarkets()) {

                getData(m.getMarketid());

            }

            return null;
        }

        protected void onPostExecute(Double d) {

            //CONTINUE WORKING HERE!

            populateListView();
        }

    }

    private void populateListView() {


        ListView list = (ListView) findViewById(R.id.fragment_list_view);
        list.setAdapter(new PairAdapter(mContext, R.layout.pair_item_view, Pairs.getVisibleMarkets()));
        list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.i(TAG, "List item clicked");


            }

        });


    }


}




