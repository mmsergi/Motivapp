package com.sergi.motivapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Sergi on 20/06/2017.
 */

public class ImagesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_images, container, false);

        return v;
    }

    public static ImagesFragment newInstance() {
        ImagesFragment f = new ImagesFragment();
        return f;
    }
}
