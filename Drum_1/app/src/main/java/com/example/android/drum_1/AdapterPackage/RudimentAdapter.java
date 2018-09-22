package com.example.android.drum_1.AdapterPackage;

/**
 * Created by Minjeong Kim on 2018-09-21.
 */


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.drum_1.Model.Rudiment;
import com.example.android.drum_1.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Minjeong Kim on 2018-09-21.
 */

public class RudimentAdapter extends BaseAdapter {

    List<Rudiment> rudimentList;
    Context context;

    public RudimentAdapter(List<Rudiment> rudimentList, Context context) {
        this.rudimentList = rudimentList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return rudimentList.size();
    }

    @Override
    public Object getItem(int i) {
        return rudimentList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View rootView = view;
        if(rootView==null){
            LayoutInflater inflater = LayoutInflater.from(context);
            View itemView = inflater.inflate(R.layout.layout_item,null);
            TextView name = (TextView)itemView.findViewById(R.id.label);
            ImageView imageView = (ImageView)itemView.findViewById(R.id.image);
            Picasso.with(context).load(rudimentList.get(i).getUrl()).into(imageView);
            name.setText(rudimentList.get(i).getRudiment());
            return itemView;
        }
        return rootView;
    }
}
