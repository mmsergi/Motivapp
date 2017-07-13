package com.sergi.motivapp.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sergi.motivapp.R;

import java.util.ArrayList;

/**
 * Created by gersoft on 29/06/2017.
 */

public class TasksGoalListViewAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final ArrayList<String> values;

    public TasksGoalListViewAdapter(Context context, ArrayList<String> aList) {
        super(context, R.layout.item_task);

        this.values = aList;
        this.context = context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.item_task, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.textView);

        textView.setText(values.get(position));

        return rowView;
    }

    @Override
    public int getCount() {
        if (values.size() <= 0)
            return 1;
        return values.size();
    }

}
