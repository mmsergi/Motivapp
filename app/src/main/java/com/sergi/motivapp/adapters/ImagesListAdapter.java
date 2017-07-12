package com.sergi.motivapp.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
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

    public View getView(int position, View v, ViewGroup parent) {

        v = layoutInflater.inflate(R.layout.token_image, null);
        TextView title = (TextView) v.findViewById(R.id.textViewTitle);
        TextView points = (TextView) v.findViewById(R.id.textViewPoints);
        final ImageView image = (ImageView) v.findViewById(R.id.imageView);

        final ProgressBar progressBar = (ProgressBar) v.findViewById(R.id.progress);

        ImageToken token = (ImageToken) listData.get(position);
        title.setText(token.title);
        points.setText(Integer.toString(token.points) + " points");

        if (image != null) {
            Glide.with(v).load(token.url).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    progressBar.setVisibility(View.GONE);
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    progressBar.setVisibility(View.GONE);
                    return false;
                }
            }).into(image);
        }

        return v;
    }
}