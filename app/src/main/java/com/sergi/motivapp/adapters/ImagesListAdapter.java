package com.sergi.motivapp.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sergi.motivapp.R;
import com.sergi.motivapp.items.ImageToken;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Sergi on 22/06/2017.
 */

public class ImagesListAdapter extends BaseAdapter {
    private ArrayList listData;
    private LayoutInflater layoutInflater;

    public ImagesListAdapter(Context context, ArrayList listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        Log.e("POSITIONE", Integer.toString(position));

        convertView = layoutInflater.inflate(R.layout.token_image, null);
        TextView title = (TextView) convertView.findViewById(R.id.textViewTitle);
        TextView points = (TextView) convertView.findViewById(R.id.textViewPoints);
        ImageView image = (ImageView) convertView.findViewById(R.id.imageView);

        ImageToken token = (ImageToken) listData.get(position);
        title.setText(token.title);
        points.setText(Integer.toString(token.points) + " points");
        if (image != null) {
            Glide.with(convertView).load(token.url).into(image);
        }

        return convertView;
    }

}

