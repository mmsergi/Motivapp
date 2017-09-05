package com.sergi.motivapp.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.sergi.motivapp.R;
import java.util.ArrayList;

public class GridViewMainAdapter extends ArrayAdapter<String> {

    private Context context;
    private ArrayList<String> arrayTitles;
    private ArrayList<String> arrayIcons;

    public GridViewMainAdapter(Context context, ArrayList<String> arrayTitles, ArrayList<String> arrayIcons) {
        super(context, R.layout.item_grid_main);
        this.context = context;
        this.arrayTitles = arrayTitles;
        this.arrayIcons = arrayIcons;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView;

        rowView = inflater.inflate(R.layout.item_grid_main, parent, false);

        TextView tv = (TextView) rowView.findViewById(R.id.textView);
        tv.setText(arrayTitles.get(position));

        ImageView iv = (ImageView) rowView.findViewById(R.id.imageView);

        if (arrayIcons.get(position) != "") {
            Resources res = context.getResources();
            String mDrawableName = arrayIcons.get(position);
            int resID = res.getIdentifier(mDrawableName, "drawable", context.getPackageName());
            Drawable drawable = res.getDrawable(resID);
            iv.setImageDrawable(drawable);
        } else {
            iv.setVisibility(View.INVISIBLE);
        }

        return rowView;
    }

    public int getCount() {
        if (arrayTitles.size() <= 0) return 1;
        return arrayTitles.size();
    }

}