package com.sergi.motivapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.sergi.motivapp.R;
import com.sergi.motivapp.adapters.GridViewMainAdapter;

import java.util.ArrayList;

public class MainGridActivity extends AppCompatActivity{

    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_grid);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        gridView = (GridView) findViewById(R.id.gridView);

        ArrayList<String> titlesNames = new ArrayList<>();
        final ArrayList<String> iconsNames = new ArrayList<>();

        titlesNames.add("Images");
        titlesNames.add("Quotes");
        titlesNames.add("Videos");
        titlesNames.add("Notifications");
        titlesNames.add("My Goals");

        iconsNames.add("ic_photo_white_48dp");
        iconsNames.add("ic_format_quote_white_48dp");
        iconsNames.add("ic_ondemand_video_white_48dp");
        iconsNames.add("ic_notifications_white_48dp");
        iconsNames.add("moti");

        gridView.setAdapter(new GridViewMainAdapter(this, titlesNames, iconsNames));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (iconsNames.get(position).toLowerCase()) {
                    case "moti":
                        startActivity(new Intent(MainGridActivity.this, Goals2Activity.class));
                        break;
                }
            }
        });

    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.goalsBtn:
                Intent i = new Intent(this, Goals2Activity.class);
                startActivity(i);
                break;
        }
    }
}