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
        listAdapter = new ExpandableListGoalsAdapter(this,listDataHeader,listHash);
        listView.setAdapter(listAdapter);
    }

    @Override
    protected void onResume(){
        super.onResume();

        initData();
    }

    private void initData() {

        DatabaseGoals db = new DatabaseGoals(getApplicationContext());

        ArrayList<Goal> aListGoals = db.getGoals();

        db.close();

        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        for (int i = 0; i < aListGoals.size(); i++) {

            Goal item = aListGoals.get(i);

            listDataHeader.add(item.goal);

            try {
                JSONArray jsonArray = new JSONArray(item.tasks);
                List<String> tasksList = new ArrayList<>();

                for (int e = 0; e < jsonArray.length(); e++) {
                    tasksList.add(jsonArray.get(e).toString());
                    listHash.put(listDataHeader.get(i), tasksList);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

      /*  listDataHeader.add("EDMTDev");
        listDataHeader.add("Android");
        listDataHeader.add("Xamarin");
        listDataHeader.add("UWP");

        List<String> edmtDev = new ArrayList<>();
        edmtDev.add("This is Expandable ListView");

        List<String> androidStudio = new ArrayList<>();
        androidStudio.add("Expandable ListView");
        androidStudio.add("Google Map");
        androidStudio.add("Chat Application");
        androidStudio.add("Firebase ");

        List<String> xamarin = new ArrayList<>();
        xamarin.add("Xamarin Expandable ListView");
        xamarin.add("Xamarin Google Map");
        xamarin.add("Xamarin Chat Application");
        xamarin.add("Xamarin Firebase ");

        List<String> uwp = new ArrayList<>();
        uwp.add("UWP Expandable ListView");
        uwp.add("UWP Google Map");
        uwp.add("UWP Chat Application");
        uwp.add("UWP Firebase ");

        listHash.put(listDataHeader.get(0),edmtDev);
        listHash.put(listDataHeader.get(1),androidStudio);
        listHash.put(listDataHeader.get(2),xamarin);
        listHash.put(listDataHeader.get(3),uwp);*/
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