package com.sergi.motivapp;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
                    return ImagesFragment.newInstance();
                case 3:
                    return QuotesFragment.newInstance();
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
}