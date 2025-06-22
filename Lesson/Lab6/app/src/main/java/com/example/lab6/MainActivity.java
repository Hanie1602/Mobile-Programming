package com.example.lab6;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btnMenu;
    Button btnChonMau;
    ConstraintLayout manHinh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnMenu = findViewById(R.id.buttonMenu);
        btnChonMau = findViewById(R.id.button_Chonmau);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowMenu();
            }
        });

        btnChonMau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowColorMenu();
            }
        });


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        manHinh = findViewById(R.id.main);
    }

    //Tạo riêng function hiển thị Menu (Thêm/Sửa/Xóa)
    private void ShowMenu(){
        PopupMenu popupMenu = new PopupMenu(this, btnMenu);
        popupMenu.getMenuInflater().inflate(R.menu.menu_popup, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(item -> {
            int id = item.getItemId();
            if (id == R.id.menuThem) {
                btnMenu.setText("Menu Thêm");
                return true;
            } else if (id == R.id.menuSua) {
                btnMenu.setText("Menu Sửa");
                return true;
            } else if (id == R.id.menuXoa) {
                btnMenu.setText("Menu Xóa");
                return true;
            }
            return false;
        });

        popupMenu.show();
    }

    //Tạo riêng function hiển thị Menu Color
    private void ShowColorMenu() {
        PopupMenu popupColor = new PopupMenu(this, btnChonMau);
        popupColor.getMenuInflater().inflate(R.menu.menu_context, popupColor.getMenu());

        popupColor.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.menuVang) {
                    manHinh.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
                    return true;
                } else if (id == R.id.menuDo) {
                    manHinh.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                    return true;
                } else if (id == R.id.menuXanh) {
                    manHinh.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
                    return true;
                }
                return false;
            }
        });

        popupColor.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);

        //Đổi màu đen cho từng item
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            SpannableString spanString = new SpannableString(item.getTitle());
            spanString.setSpan(new ForegroundColorSpan(Color.BLACK), 0, spanString.length(), 0);
            item.setTitle(spanString);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mAdd) {
            Toast.makeText(this, "You clicked Add", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.mExit) {
            finishAffinity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        getMenuInflater().inflate(R.menu.menu_context, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }
}