package com.example.lab3;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.lab3.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private EditText edtItem;
    private Button btnAdd;
    private Button btnUpdate;
    private Button btnDelete;
    private ListView lvItems;

    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;
    private int selectedIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Ẩn cái toolbar mặc định để EditText trồi lên
        if (getSupportActionBar() != null)
            getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        // 1. Ánh xạ view
        edtItem   = findViewById(R.id.edtItem);
        edtItem.setFocusableInTouchMode(true);
        edtItem.requestFocus();
        btnAdd    = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        lvItems   = findViewById(R.id.lvItems);

        // 2. Khởi tạo dữ liệu và adapter
        items = new ArrayList<>(Arrays.asList("Android", "PHP", "iOS", "Unity", "ASP.net"));
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                items);
        lvItems.setAdapter(adapter);

        // 3. Xử lý THÊM
        btnAdd.setOnClickListener(v -> {
            String text = edtItem.getText().toString().trim();
            if (!text.isEmpty()) {
                items.add(text);
                adapter.notifyDataSetChanged();
                edtItem.setText("");
            }
        });

        // 4. Chọn item để SỬA
        lvItems.setOnItemClickListener((parent, view, position, id) -> {
            selectedIndex = position;
            edtItem.setText(items.get(position));
            btnUpdate.setEnabled(true);    // bật nút CẬP NHẬT
            btnDelete.setEnabled(true);   // bật nút XÓA
        });

        // 5. Xử lý CẬP NHẬT
        btnUpdate.setOnClickListener(v -> {
            if (selectedIndex >= 0) {
                String newText = edtItem.getText().toString().trim();
                if (!newText.isEmpty()) {
                    items.set(selectedIndex, newText);
                    adapter.notifyDataSetChanged();
                    edtItem.setText("");
                    selectedIndex = -1;
                    btnUpdate.setEnabled(false);   // tắt lại sau khi cập nhật
                }
            }
        });


        btnDelete.setOnClickListener(v -> {
            if (selectedIndex >= 0) {
                items.remove(selectedIndex);
                adapter.notifyDataSetChanged();
                // reset state
                edtItem.setText("");
                selectedIndex = -1;
                btnUpdate.setEnabled(false);
                btnDelete.setEnabled(false);
            }
        });

        // 6. Xử lý XÓA (long-click)
        lvItems.setOnItemLongClickListener((parent, view, position, id) -> {
            items.remove(position);
            adapter.notifyDataSetChanged();
            if (position == selectedIndex) {
                edtItem.setText("");
                selectedIndex = -1;
                btnUpdate.setEnabled(false);
                btnDelete.setEnabled(false);   // tắt nút XÓA
            }
            return true;
        });
    }
}
