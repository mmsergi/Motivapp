package com.sergi.motivapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.pager) ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(3);

        TabLayout tabs = (TabLayout) findViewById(R.id.tab_layout);
        tabs.setTabMode(TabLayout.MODE_SCROLLABLE);

        tabs.setupWithViewPager(viewPager);
    }

    public class FragmentAdapter extends FragmentPagerAdapter {
        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position) {
                case 0:
                    return ImagesFragment.newInstance();
                case 1:
                    return QuotesFragment.newInstance();
                case 2:
                    return QuotesFragment.newInstance();
                case 3:
                    return NotificationsFragment.newInstance();
                default:
                    return ImagesFragment.newInstance();
            }
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            switch(position) {
                case 0: return "Images";
                case 1: return "Quotes";
                case 2: return "Videos";
                case 3: return "Notifications";
                default: return "Favorites";
            }
        }

    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.goalsBtn:
                Intent i = new Intent(this, GoalsActivity.class);
                startActivity(i);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(MainActivity.this);
        builderSingle.setTitle("We do, we decide");
        builderSingle.setMessage("Are you ready to take action?");
        builderSingle.setNegativeButton("Not yet",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setPositiveButton("YEAH",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }

                });

        builderSingle.show();
    }
}
