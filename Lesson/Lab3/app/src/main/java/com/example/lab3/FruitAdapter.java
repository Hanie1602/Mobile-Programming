package com.example.lab3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class FruitAdapter extends ArrayAdapter<Fruit> {
    private final int resourceLayout;
    private final Context mContext;

    public FruitAdapter(Context context, int resource, List<Fruit> items) {
        super(context, resource, items);
        this.resourceLayout = resource;
        this.mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = LayoutInflater.from(mContext);
            v = vi.inflate(resourceLayout, parent, false);
        }
        Fruit f = getItem(position);
        if (f != null) {
            ImageView img = v.findViewById(R.id.imgFruit);
            TextView name = v.findViewById(R.id.tvName);
            TextView desc = v.findViewById(R.id.tvDesc);
            img.setImageResource(f.getImageRes());
            name.setText(f.getName());
            desc.setText(f.getDesc());
        }
        return v;
    }
}

