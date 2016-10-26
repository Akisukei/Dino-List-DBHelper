package com.example.a1436664.dinolist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by 1436664 on 10/19/2016.
 */
public class CustomAdapter extends BaseAdapter{

    private Context context;
    private int[] dinoIconIds;
    private String[] dinoNames;
    private static LayoutInflater inflater = null;

    public CustomAdapter(MainActivity mainActivity, int[] dinoImageIds, String[] dinoNames) {

        this.context = mainActivity;
        this.dinoIconIds = dinoImageIds;
        this.dinoNames = dinoNames;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return dinoNames.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder {
        TextView tv;
        ImageView img;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        Holder holder = new Holder();

        View rowView = inflater.inflate(R.layout.dino_list, null);
        holder.tv = (TextView) rowView.findViewById(R.id.listTextView);
        holder.img = (ImageView) rowView.findViewById(R.id.listImageView);

        holder.tv.setText(dinoNames[position]);
        holder.img.setImageResource(dinoIconIds[position]);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, DescriptionActivity.class);
                i.putExtra("position", position);
                context.startActivity(i);
            }
        });

        return rowView;
    }
}
