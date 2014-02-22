package com.ericturnerdev.CryptsyTicker;

import android.app.Activity;
import android.os.Bundle;

/**
 * Settings page allowing users to choose what coins they want displayed
 */
public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setTitle("Filter Coins");
        setContentView(R.layout.activity_settings);

        //setContentView(R.layout.activity_settings);


    }

}
