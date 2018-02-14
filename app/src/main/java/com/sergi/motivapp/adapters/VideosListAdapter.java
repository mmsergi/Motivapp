package com.sergi.motivapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.sergi.motivapp.R;
import com.sergi.motivapp.activities.VideoActivity;
import com.sergi.motivapp.models.VideoToken;

import java.util.ArrayList;

/**
 * Created by gersoft on 26/07/2017.
 */

public class VideosListAdapter extends BaseAdapter {

    private ArrayList listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public VideosListAdapter(Context context, ArrayList listData) {
        this.listData = listData;
        this.layoutInflater = LayoutInflater.from(context);

        this.context = context;
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

    @Override
    public View getView(int position, View v, ViewGroup parent) {

        v = layoutInflater.inflate(R.layout.item_video, null);

        TextView title = (TextView) v.findViewById(R.id.textViewTitle);
        final ImageView image = (ImageView) v.findViewById(R.id.imageView);
        Button watchBtn = (Button) v.findViewById(R.id.watchBtn);

        final ProgressBar progressBar = (ProgressBar) v.findViewById(R.id.progress);

        final VideoToken token = (VideoToken) listData.get(position);
        title.setText(token.title);

        if (image != null) {
            Glide.with(v).load("https://img.youtube.com/vi/" + token.url + "/hqdefault.jpg").listener(new RequestListener<Drawable>() {
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

        watchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, VideoActivity.class);
                i.putExtra(Intent.EXTRA_TEXT, token.url);
                context.startActivity(i);
            }
        });

        return v;
    }
}
