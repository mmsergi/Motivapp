package com.sergi.motivapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
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
import com.sergi.motivapp.models.ImageToken;

import java.util.ArrayList;

/**
 * Created by Sergi on 22/06/2017.
 */

public class ImagesListAdapter extends BaseAdapter {
    private ArrayList listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public ImagesListAdapter(Context context, ArrayList listData) {
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

    public View getView(int position, View v, ViewGroup parent) {

        v = layoutInflater.inflate(R.layout.item_image, null);

        TextView title = v.findViewById(R.id.textViewTitle);
        TextView points = v.findViewById(R.id.textViewPoints);
        final ImageView image = v.findViewById(R.id.imageView);
        Button shareBtn = v.findViewById(R.id.shareBtn);

        final ProgressBar progressBar = v.findViewById(R.id.progress);

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

        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmapImg = ((BitmapDrawable) image.getDrawable()).getBitmap();

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, "More images at: https://goo.gl/GPUoTJ");
                String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmapImg, "", null);
                Uri screenshotUri = Uri.parse(path);

                intent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
                intent.setType("image/*");
                context.startActivity(Intent.createChooser(intent, "Share:"));
            }
        });

        return v;
    }
}