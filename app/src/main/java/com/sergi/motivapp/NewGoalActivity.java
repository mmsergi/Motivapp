package com.sergi.motivapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.sergi.motivapp.adapters.GoalListViewAdapter;

import java.util.ArrayList;

/**
 * Created by gersoft on 12/07/2017.
 */

public class NewGoalActivity extends AppCompatActivity {

    ArrayList<String> aList = new ArrayList<>();;
    ListView list;
    EditText etTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_goal);

        list = (ListView) findViewById(R.id.listView);
        etTask = (EditText) findViewById(R.id.editTextTask);

        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(etTask.getWindowToken(), 0);

                aList.add(etTask.getText().toString());
                etTask.setText("");

                GoalListViewAdapter adapter = new GoalListViewAdapter(getApplicationContext(), aList);
                list.setAdapter(adapter);
            }
        });
    }
}
