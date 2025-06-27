package com.example.pe;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pe.database.SQLiteHelper;
import com.example.pe.model.Nganh;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListNganhActivity extends AppCompatActivity {
    ListView lvNganh;
    SQLiteHelper db;
    List<Nganh> nganhList;
    ArrayAdapter<String> adapter;
    List<String> nganhNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_nganh);

        lvNganh = findViewById(R.id.lvNganh);
        FloatingActionButton fabAddNganh = findViewById(R.id.fabAddNganh);

        db = new SQLiteHelper(this);

        loadNganhList();

        //Nút Floating Action Button cho chức năng Create
        fabAddNganh.setOnClickListener(v -> {
            startActivity(new Intent(ListNganhActivity.this, AddNganhActivity.class));
        });

        //Nút Update Ngành
        lvNganh.setOnItemClickListener((parent, view, position, id) -> {
            Nganh selected = nganhList.get(position);

            // Gửi dữ liệu sang màn hình UpdateNganhActivity
            Intent intent = new Intent(ListNganhActivity.this, UpdateNganhActivity.class);
            intent.putExtra("id", selected.getId());
            intent.putExtra("name", selected.getName());
            startActivity(intent);
        });

        //Xóa ngành (Bấm giữ vào 1 ngành trong List => Sẽ hiển thị muốn xóa hay không)
        lvNganh.setOnItemLongClickListener((parent, view, position, id) -> {
            Nganh selected = nganhList.get(position);

            new AlertDialog.Builder(this)
                    .setTitle("Xóa ngành")
                    .setMessage("Bạn có chắc chắn muốn xóa ngành '" + selected.getName() + "'?")
                    .setPositiveButton("Xóa", (dialog, which) -> {
                        db.deleteNganhById(selected.getId());
                        Toast.makeText(this, "Đã xóa", Toast.LENGTH_SHORT).show();
                        loadNganhList();
                    })
                    .setNegativeButton("Hủy", null)
                    .show();

            return true;
        });

    }

    //Load lại danh sách
    private void loadNganhList() {
        nganhList = db.getAllNganh();
        nganhNames = new ArrayList<>();
        for (Nganh n : nganhList) nganhNames.add(n.getName());

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nganhNames);
        lvNganh.setAdapter(adapter);
    }

    //Tự cập nhật sau khi Create hàm
    @Override
    protected void onResume() {
        super.onResume();
        loadNganhList();
    }
}
