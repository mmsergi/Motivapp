package com.sergi.motivapp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.sergi.motivapp.adapters.GoalListViewAdapter;

import java.util.ArrayList;

/**
 * Created by gersoft on 29/06/2017.
 */

public class NotificationsFragment extends ListFragment{

    ArrayList<String> aList = new ArrayList<>();;
    ListView list;
    EditText etTask;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_goal, container, false);

        etTask = (EditText) v.findViewById(R.id.editTextTask);

        final Button button = (Button) v.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(etTask.getWindowToken(), 0);

                aList.add(etTask.getText().toString());
                etTask.setText("");

                GoalListViewAdapter adapter = new GoalListViewAdapter(getActivity().getApplicationContext(), aList);
                setListAdapter(adapter);
            }
        });

        return v;
    }

    public static NotificationsFragment newInstance() {
        NotificationsFragment f = new NotificationsFragment();
        return f;
    }

}