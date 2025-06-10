package com.example.lab1;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.lab1.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Tìm button theo ID
        Button btnOpen = findViewById(R.id.btnOpenActivityLab1b);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Tạo intent để mở Lab1b
                Intent intent = new Intent(MainActivity.this, ActivityLab1b.class);
                startActivity(intent);
            }
        });

        //Sau khi thiết lập btnOpenActivityLab2a
        Button btnOpen2a = findViewById(R.id.btnOpenActivityLab2a);
        btnOpen2a.setOnClickListener(v -> {
            Intent intent2a = new Intent(MainActivity.this, ActivityLab2a.class);
            startActivity(intent2a);
        });

        //Sau khi thiết lập btnOpenActivityLab2b
        Button btnOpen2b = findViewById(R.id.btnOpenActivityLab2b);
        btnOpen2b.setOnClickListener(v -> {
            Intent intent2b = new Intent(MainActivity.this, ActivityLab2b.class);
            startActivity(intent2b);
        });

    }
}
