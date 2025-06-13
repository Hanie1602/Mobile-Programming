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
    private final int resourceLayout; //lưu ID của file layout XML
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

        //Lấy dữ liệu tại vị trí (position)
        Fruit f = getItem(position);

        //Ánh xạ và gán giá trị
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

//Note:
//Khi ListView cần một dòng hiển thị, nó gọi getView(position, convertView, parent)
//Adapter tái sử dụng convertView nếu có, hoặc inflate layout mới nếu chưa có
//Lấy đối tượng Fruit tương ứng với position
//Gán ảnh, tên, mô tả của Fruit vào các view con
//Trả về View hiển thị