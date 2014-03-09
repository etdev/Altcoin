package com.ericturnerdev.CryptsyTicker;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

/**
 * Code for Individual Trade Pair Activity
 */
public class IndivActivity extends Activity {


    public static int marketId;
    public String TAG = "IndivActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.indiv_activity);

        FragmentManager fm = getFragmentManager();
        IndivFragmentChart ifc = new IndivFragmentChart();
        IndivFragmentBot ifb = new IndivFragmentBot();

        //Get marketId
        marketId = getIntent().getExtras().getInt("marketId");
        ifc.marketId = marketId;

        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.indivActivLL, ifc);
        //ft.add(R.id.indivActivLL, ifb);
        ft.commit();

    }

}
