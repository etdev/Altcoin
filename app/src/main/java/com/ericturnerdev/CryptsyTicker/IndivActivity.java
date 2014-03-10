package com.ericturnerdev.CryptsyTicker;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

/**
 * Code for Individual Trade Pair Activity
 */
public class IndivActivity extends Activity {


    public static int marketId;
    public static double volume;

    public String TAG = "IndivActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //Get marketId
        marketId = getIntent().getExtras().getInt("marketId");
        volume = getIntent().getExtras().getDouble("volume");
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

    }

}
