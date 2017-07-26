package com.sergi.motivapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.sergi.motivapp.R;
import com.sergi.motivapp.adapters.ImagesListAdapter;
import com.sergi.motivapp.adapters.QuotesListAdapter;
import com.sergi.motivapp.models.ImageToken;
import com.sergi.motivapp.models.QuoteToken;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gersoft on 22/06/2017.
 */

public class QuotesFragment extends ListFragment {

    JSONArray jsonQuotes;
    ArrayList<QuoteToken> listData = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_view_layout, container, false);

        return v;
    }

    @Override
    public void onViewCreated (View view, Bundle savedInstanceState) {
        initData();
    }

    public static QuotesFragment newInstance() {
        QuotesFragment f = new QuotesFragment();
        return f;
    }

    public void initData(){

        listData.add(new QuoteToken(" “Don't wish it were easier. Wish you were better.” ","― Jim Rohn "));
        listData.add(new QuoteToken(" “Change the way you look at things and the things you look at change.” ","― Wayne W. Dyer "));
        listData.add(new QuoteToken(" “Cry. Forgive. Learn. Move on. Let your tears water the seeds of your future happiness.” ","― Steve Maraboli "));
        listData.add(new QuoteToken(" “Action may not always bring happiness, but there is no happiness without action. ” ","― William James "));
        listData.add(new QuoteToken(" “Only he who attempts the absurd is capable of achieving the impossible.” ","― Miguel de Unamuno "));
        listData.add(new QuoteToken(" “You are the average of the five people you spend the most time with.” ","― Jim Rohn "));

        getListView().setAdapter(new QuotesListAdapter(getContext(), listData));

    }

}