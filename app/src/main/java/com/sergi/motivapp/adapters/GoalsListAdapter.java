package com.sergi.motivapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.sergi.motivapp.R;
import com.sergi.motivapp.models.Goal;
import com.sergi.motivapp.models.QuoteToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by Sergi on 21/08/2017.
 */

public class GoalsListAdapter extends BaseAdapter {

    private  ArrayList<Goal> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public GoalsListAdapter(Context context,  ArrayList<Goal> listData) {
        this.listData = listData;
        this.layoutInflater = LayoutInflater.from(context);

        this.context = context;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        v = layoutInflater.inflate(R.layout.cardview_goal, null);

        Goal g = listData.get(position);

        TextView goal = (TextView) v.findViewById(R.id.goal);
        TextView why = (TextView) v.findViewById(R.id.why);

        goal.setText(g.goal);
        why.setText(g.why);

        ListView lv = (ListView) v.findViewById(R.id.lv);

        ArrayList<String> listItems = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(context,
                android.R.layout.simple_list_item_1,
                listItems);

        try {
            JSONArray jsonArray = new JSONArray(g.tasks);

            for (int e = 0; e < jsonArray.length(); e++) {
                listItems.add((String) jsonArray.get(e));
            }

            lv.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return v;
    }
}
