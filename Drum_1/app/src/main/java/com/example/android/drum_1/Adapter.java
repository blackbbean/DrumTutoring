package com.example.android.drum_1;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Minjeong Kim on 2018-09-17.
 */

public class Adapter extends PagerAdapter {

    private int[] images = {R.drawable.practice, R.drawable.learning, R.drawable.aboutdrum};
    private LayoutInflater inflater;
    private Context context;

    public Adapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.slider, container, false);
        ImageView ImageView = (ImageView) v.findViewById(R.id.imageView);
        ImageView.setImageResource(images[position]);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position==0){
                    Intent selectIntent = new Intent(context,selectRudimentActivity.class);
                    selectIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(selectIntent);
                }
            }
        });


        container.addView(v);

        return v;
    }

    public void destroyItem(ViewGroup container, int position, Object object) {
        container.invalidate();
    }

}
