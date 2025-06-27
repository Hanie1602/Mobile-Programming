package com.example.pe;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pe.database.SQLiteHelper;
import com.example.pe.model.Sinhvien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lvSinhVien;
    ArrayAdapter<String> adapter;
    List<Sinhvien> list;
    SQLiteHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        lvSinhVien = findViewById(R.id.lvSinhVien);
        FloatingActionButton fabAddSv = findViewById(R.id.fabAddSv);
        FloatingActionButton fabViewNganh = findViewById(R.id.fabViewNganh);

        db = new SQLiteHelper(this);

        list = db.getAllSinhVien();
        List<String> nameList = new ArrayList<>();
        for (Sinhvien sv : list) {
            nameList.add(sv.getName() + " - " + sv.getPhone());
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nameList);
        lvSinhVien.setAdapter(adapter);

        lvSinhVien.setOnItemClickListener((parent, view, position, id) -> {
            Sinhvien selected = list.get(position);
            Intent intent = new Intent(MainActivity.this, TraineeDetailActivity.class);
            intent.putExtra("id", selected.getId());
            intent.putExtra("name", selected.getName());
            intent.putExtra("date", selected.getDate());
            intent.putExtra("gender", selected.getGender());
            intent.putExtra("address", selected.getAddress());
            intent.putExtra("phone", selected.getPhone());
            intent.putExtra("idNganh", selected.getIdNganh());
            startActivity(intent);
        });

        //2 nút Floating Action Button cho cả 2 chức năng
        //Thêm sinh viên và Xem danh sách Ngành
        fabAddSv.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AddSinhvienActivity.class));
        });

        fabViewNganh.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ListNganhActivity.class));
        });

        //Xóa (Bấm giữ vào 1 sinh viên trong List => Sẽ hiển thị muốn xóa hay không)
        lvSinhVien.setOnItemLongClickListener((parent, view, position, id) -> {
            Sinhvien selected = list.get(position);

            new AlertDialog.Builder(this)
                    .setTitle("Xóa sinh viên")
                    .setMessage("Bạn có chắc chắn muốn xóa sinh viên '" + selected.getName() + "'?")
                    .setPositiveButton("Xóa", (dialog, which) -> {
                        db.deleteSinhvienById(selected.getId());
                        Toast.makeText(this, "Đã xóa sinh viên", Toast.LENGTH_SHORT).show();
                        reloadSinhvienList(); //Tải lại danh sách sau khi xóa
                    })
                    .setNegativeButton("Hủy", null)
                    .show();

            return true;
        });

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
    }

    //Load lại danh sách
    private void reloadSinhvienList() {
        list = db.getAllSinhVien();
        List<String> nameList = new ArrayList<>();
        for (Sinhvien sv : list) {
            nameList.add(sv.getName() + " - " + sv.getPhone());
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nameList);
        lvSinhVien.setAdapter(adapter);
    }

    //Tự động cập nhật
    @Override
    protected void onResume() {
        super.onResume();
        reloadSinhvienList();
    }

}