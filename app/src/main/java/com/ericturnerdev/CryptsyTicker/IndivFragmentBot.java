package com.ericturnerdev.CryptsyTicker;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Code for Bottom fragment of Individual Trade Pair activity
 */
public class IndivFragmentBot extends Fragment {

    TextView tv;

    public IndivFragmentBot() {


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_indiv_bot, container, false);

        tv = (TextView) getActivity().findViewById(R.id.IndivBotTV);

        return v;

    }

}
