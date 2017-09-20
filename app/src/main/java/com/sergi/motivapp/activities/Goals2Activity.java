package com.sergi.motivapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.sergi.motivapp.DatabaseGoals;
import com.sergi.motivapp.R;
import com.sergi.motivapp.adapters.GoalsListAdapter;
import com.sergi.motivapp.models.Goal;

import java.util.ArrayList;

/**
 * Created by Sergi on 22/08/2017.
 */

public class Goals2Activity extends AppCompatActivity {

    private ListView listView;
    private GoalsListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_goals2);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        ab.setTitle("My Goals");

        listView = (ListView) findViewById(R.id.lv);
        initData();
    }

    @Override
    protected void onResume(){
        super.onResume();

        initData();
    }

    public void initData() {

        DatabaseGoals db = new DatabaseGoals(getApplicationContext());

        ArrayList<Goal> aListGoals = db.getGoals();

        db.close();

        listAdapter = new GoalsListAdapter(this, aListGoals);
        listView.setAdapter(listAdapter);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.newGoalBtn:
                Intent i = new Intent(this, NewGoalActivity.class);
                startActivity(i);
                finish();
                break;
        }
    }
}