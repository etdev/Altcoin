package com.ericturnerdev.CryptsyTicker;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

/**
 * Generic Activity code to display a single Fragment
 * depending on what kind of fragment it is
 */
public abstract class SingleFragmentActivity extends Activity {

    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        //This is an Activity, so the content view is set to activity_fragment
        setContentView(R.layout.activity_fragment);
        //Then we get a FragmentManager
        FragmentManager fm = getFragmentManager();
        //Create the code representation of the fragment
        //that will fill this activity, and assign fragmentContainer to it
        //(the layout in activity_fragment)
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
        if (fragment == null){

            fragment = createFragment();
            fm.beginTransaction().add(R.id.fragmentContainer, fragment).commit();

        }

    }

}
