package com.sergi.motivapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;

import com.sergi.motivapp.adapters.ExpandableListGoalsAdapter;
import com.sergi.motivapp.items.Goal;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GoalsActivity extends AppCompatActivity {

    private ExpandableListView listView;
    private ExpandableListGoalsAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String,List<String>> listHash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_goals);

        listView = (ExpandableListView)findViewById(R.id.lvExp);
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

        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();



        listAdapter = new ExpandableListGoalsAdapter(this, aListGoals);
        listView.setAdapter(listAdapter);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.backBtn:
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                break;
            case R.id.newGoalBtn:
                Intent i2 = new Intent(this, NewGoalActivity.class);
                startActivity(i2);
                break;
        }
    }
}