package com.sergi.motivapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.sergi.motivapp.adapters.TasksGoalListViewAdapter;
import com.sergi.motivapp.items.Goal;

import org.json.JSONArray;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gersoft on 12/07/2017.
 */

public class NewGoalActivity extends AppCompatActivity {

    @BindView(R.id.listView) ListView list;

    @BindView(R.id.editTextGoal) EditText etGoal;
    @BindView(R.id.editTextWhy) EditText etWhy;
    @BindView(R.id.editTextTask) EditText etTask;

    ArrayList<String> arrayTasks = new ArrayList<>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);
        ButterKnife.bind(this);

        list = (ListView) findViewById(R.id.listView);
        etTask = (EditText) findViewById(R.id.editTextTask);

        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(etTask.getWindowToken(), 0);

                arrayTasks.add(etTask.getText().toString());
                etTask.setText("");

                TasksGoalListViewAdapter adapter = new TasksGoalListViewAdapter(getApplicationContext(), arrayTasks);
                list.setAdapter(adapter);
            }
        });

    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.addBtn:

                JSONArray jsonTasks = new JSONArray(arrayTasks);

                Goal g = new Goal(
                        etGoal.getText().toString(),
                        etWhy.getText().toString(),
                        jsonTasks.toString());

                DatabaseGoals db = new DatabaseGoals(getApplicationContext());
                db.insertGoal(g);

                onBackPressed();

                break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, GoalsActivity.class);
        startActivity(i);
        finish();
    }
}