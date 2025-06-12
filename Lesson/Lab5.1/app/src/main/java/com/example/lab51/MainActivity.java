package com.example.lab51;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab51.Module;
import com.example.lab51.ModuleAdapter;
import com.example.lab51.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rv = findViewById(R.id.rvModules);
        rv.setLayoutManager(new LinearLayoutManager(this));

        // Tạo dữ liệu mẫu
        List<Module> data = new ArrayList<>();
        data.add(new Module(
                R.drawable.ic_android,
                "ListView trong Android",
                "Listview trong Android là một thành phần dùng để nhóm nhiều mục (item) và có thể cuộn.",
                "Android"
        ));
        data.add(new Module(
                R.drawable.ic_ios,
                "Xử lý sự kiện trong iOS",
                "Sau khi các bạn đã biết cách thiết kế giao diện cho các ứng dụng iOS, bước tiếp theo là xử lý sự kiện...",
                "iOS"
        ));

        // Gắn adapter
        rv.setAdapter(new ModuleAdapter(data));
    }
}
