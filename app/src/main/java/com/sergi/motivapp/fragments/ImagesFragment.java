package com.sergi.motivapp.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.sergi.motivapp.R;
import com.sergi.motivapp.adapters.ImagesListAdapter;
import com.sergi.motivapp.models.ImageToken;

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

/**
 * Created by Sergi on 20/06/2017.
 */

public class ImagesFragment extends ListFragment implements AdapterView.OnItemClickListener {

    JSONArray jsonImages;
    ArrayList<ImageToken> listData = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_view_layout, container, false);

        if (jsonImages == null)
            new downloadImagesData().execute("http://appsergi.esy.es/getimages.php");

        return v;
    }

    public static ImagesFragment newInstance() {
        ImagesFragment f = new ImagesFragment();
        return f;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();
    }


    private class downloadImagesData extends AsyncTask<String, String, String> {

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
                        jsonImages  =  new JSONArray(line);
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

            int lengthArray = jsonImages.length();

            for (int i = 0; i < lengthArray; i++) {
                JSONObject jsonObject;
                try {

                    jsonObject = jsonImages.getJSONObject(i);

                    String title = jsonObject.getString("title");
                    String url = jsonObject.getString("url");
                    int points = jsonObject.getInt("points");

                    listData.add(new ImageToken(title, url, points));
                    getListView().setAdapter(new ImagesListAdapter(getContext(), listData));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
