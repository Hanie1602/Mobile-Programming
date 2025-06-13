package com.example.lab1;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityLab1b extends  AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lab1b); //Layout Lab1b
    }
}

//Note:
//- kế thừa từ AppCompatActivity: sd tính năng của thư viện hỗ trợ như toolbar, theme tương thích vs nhiều phiên bản Android
// onCreate(Bundle): phương thức khởi tạo Activity