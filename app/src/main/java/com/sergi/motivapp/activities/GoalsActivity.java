package com.sergi.motivapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;

import com.sergi.motivapp.DatabaseGoals;
import com.sergi.motivapp.R;
import com.sergi.motivapp.adapters.ExpandableListGoalsAdapter;
import com.sergi.motivapp.models.Goal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GoalsActivity extends AppCompatActivity {

    private ExpandableListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_goals);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setTitle("My Goals");
        }

        listView = (ExpandableListView) findViewById(R.id.lvExp);
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

        ExpandableListGoalsAdapter listAdapter;
        listAdapter = new ExpandableListGoalsAdapter(this, aListGoals);
        listView.setAdapter(listAdapter);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.newGoalBtn:
                Intent i2 = new Intent(this, NewGoalActivity.class);
                startActivity(i2);
                break;
        }
    }
}