package com.sergi.motivapp.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sergi.motivapp.R;
import com.sergi.motivapp.adapters.VideosListAdapter;
import com.sergi.motivapp.models.VideoToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class VideosFragment extends ListFragment {

    JSONArray jsonVideos;
    ArrayList<VideoToken> listData = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_view_layout, container, false);

        return v;
    }

    @Override
    public void onViewCreated (View view, Bundle savedInstanceState) {

        new downloadVideosData().execute("http://appsergi.esy.es/getvideos.php");

    }

    public static VideosFragment newInstance() {
        VideosFragment f = new VideosFragment();
        return f;
    }

    private class downloadVideosData extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... params) {

            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line+"\n");

                    try {
                        jsonVideos =  new JSONArray(line);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    Log.d("Response", line);   //here u ll get whole response...... :-)

                }

                return buffer.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            int lengthArray = jsonVideos.length();

            for (int i = 0; i < lengthArray; i++) {
                JSONObject jsonObject;
                try {

                    jsonObject = jsonVideos.getJSONObject(i);

                    String title = jsonObject.getString("title");
                    String url = jsonObject.getString("url");
                    int points = jsonObject.getInt("points");

                    listData.add(new VideoToken(title, url, points));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            getListView().setAdapter(new VideosListAdapter(getContext(), listData));
        }
    }

}