package com.ericturnerdev.CryptsyTicker;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;

/**
 * Fragment for Settings Activity
 */
public class SettingsFragment extends Fragment {

    private ArrayList<String> dummy;
    GridView mGridView;

    @Override
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.settings_title);
        setRetainInstance(true);

        //Fill with dummy data
        dummy = new ArrayList<String>();

        for (int i=0; i<100; i++){

            dummy.add("Square " + i + " " + "\n" + " " + i + " " + i);

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        mGridView = (GridView)v.findViewById(R.id.gridView);
        setupAdapter();
        return v;
    }

    void setupAdapter(){

        if (getActivity() == null || mGridView == null) return;
        if (dummy != null){

            mGridView.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_gallery_item, dummy)); }
        else{ mGridView.setAdapter(null); }

    }



}
