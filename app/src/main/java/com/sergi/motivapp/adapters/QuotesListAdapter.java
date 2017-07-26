package com.sergi.motivapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sergi.motivapp.R;
import com.sergi.motivapp.models.ImageToken;
import com.sergi.motivapp.models.QuoteToken;

import java.util.ArrayList;

/**
 * Created by gersoft on 26/07/2017.
 */

public class QuotesListAdapter extends BaseAdapter{

    private ArrayList listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public QuotesListAdapter(Context context, ArrayList listData) {
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
        v = layoutInflater.inflate(R.layout.token_quote, null);

        TextView text = (TextView) v.findViewById(R.id.quoteTextView);
        TextView author = (TextView) v.findViewById(R.id.authorTextView);

        final QuoteToken token = (QuoteToken) listData.get(position);

        text.setText(token.text);
        author.setText(token.author);

        v.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                shareQuote();

                return false;
            }

            private void shareQuote() {
                Intent intent = new Intent(Intent.ACTION_SEND);

                intent.putExtra(Intent.EXTRA_TEXT, token.text + " " + token.author);
                intent.setType("text/plain");

                context.startActivity(Intent.createChooser(intent, "Share:"));
            }

        });

        return v;
    }
}
