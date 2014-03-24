package com.ericturnerdev.CryptsyTicker;

import android.app.Fragment;

/**
 * Code for Settings Activity (check marks)
 */
public class SettingsActivity extends SingleFragmentActivity{

    protected Fragment createFragment(){

        Fragment fragment = new SettingsFragment();
        setTitle("Settings");
        return fragment;

    }

}
