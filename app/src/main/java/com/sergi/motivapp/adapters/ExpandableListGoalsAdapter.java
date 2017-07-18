package com.sergi.motivapp.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.sergi.motivapp.DatabaseGoals;
import com.sergi.motivapp.GoalsActivity;
import com.sergi.motivapp.R;
import com.sergi.motivapp.items.Goal;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListGoalsAdapter extends BaseExpandableListAdapter {
    private Context context;

    private ArrayList<Goal> goalsList;

    public ExpandableListGoalsAdapter(Context context, ArrayList<Goal> goalsList) {
        this.context = context;
        this.goalsList = goalsList;
    }

    @Override
    public int getGroupCount() {
        return goalsList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        try {
            JSONArray jsonArray = new JSONArray(goalsList.get(i).tasks);
            return jsonArray.length();
        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Object getGroup(int i) {
        return goalsList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return goalsList.size();
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int i, boolean b, View view, ViewGroup viewGroup) {

        if(view == null)
        {
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_group, null);
        }

        TextView goalTextView = (TextView)view.findViewById(R.id.goalTextView);
        goalTextView.setTypeface(null, Typeface.BOLD);
        goalTextView.setText(goalsList.get(i).goal);

        TextView whyTextView = (TextView)view.findViewById(R.id.whyTextView);
        whyTextView.setTypeface(null, Typeface.BOLD);
        whyTextView.setText(goalsList.get(i).why);

        Button deleteBtn = (Button) view.findViewById(R.id.deleteBtn);
        deleteBtn.setFocusable(false);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseGoals db = new DatabaseGoals(context);
                db.deleteGoal(goalsList.get(i).goal);

                ((GoalsActivity) context).initData();

            }
        });

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {

        if(view == null)
        {
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item, null);
        }

        TextView txtListChild = (TextView)view.findViewById(R.id.lblListItem);

        try {
            JSONArray jsonArray = new JSONArray(goalsList.get(i).tasks);

            txtListChild.setText(jsonArray.get(i1).toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
