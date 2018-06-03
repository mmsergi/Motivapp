package com.sergi.motivapp.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sergi.motivapp.R;
import com.sergi.motivapp.adapters.ImagesListAdapter;
import com.sergi.motivapp.models.ImageToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Sergi on 20/06/2017.
 */

public class ImagesFragment extends ListFragment implements AdapterView.OnItemClickListener {

    ArrayList<ImageToken> listData = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_list_view, container, false);
    }

    public static ImagesFragment newInstance() {
        return new ImagesFragment();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getImagesData();

        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();
    }

    void getImagesData (){

        OkHttpClient client = new OkHttpClient();
        Request request;

        request = new Request.Builder().url("http://appsergi.esy.es/getimages.php").get().build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) { e.printStackTrace(); }
            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                } else {
                    String line = response.body().string();

                    Gson gson = new Gson();
                    Type listOfImageTokens = new TypeToken<List<ImageToken>>(){}.getType();
                    listData = gson.fromJson(line, listOfImageTokens);
                    updateUI();

                }
            }
        });

    }

    void updateUI() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getListView().setAdapter(new ImagesListAdapter(getContext(), listData));
            }
        });
    }
}