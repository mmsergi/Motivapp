package com.sergi.motivapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by gersoft on 22/06/2017.
 */

public class QuotesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_quotes, container, false);

        return v;
    }

    public static QuotesFragment newInstance() {
        QuotesFragment f = new QuotesFragment();
        return f;
    }


}
