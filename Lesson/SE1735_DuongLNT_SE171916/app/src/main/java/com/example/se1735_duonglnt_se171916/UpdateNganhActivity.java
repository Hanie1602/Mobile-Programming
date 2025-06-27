package com.example.se1735_duonglnt_se171916;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.se1735_duonglnt_se171916.database.SQLiteHelper;

public class UpdateNganhActivity extends AppCompatActivity {
    EditText edtUpdateName;
    Button btnUpdate;
    SQLiteHelper db;
    int nganhId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_nganh);

        edtUpdateName = findViewById(R.id.edtUpdateName);
        btnUpdate = findViewById(R.id.btnUpdate);
        db = new SQLiteHelper(this);

        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        nganhId = intent.getIntExtra("id", -1);
        String name = intent.getStringExtra("name");

        edtUpdateName.setText(name);

        btnUpdate.setOnClickListener(v -> {
            String newName = edtUpdateName.getText().toString().trim();
            if (!newName.isEmpty()) {
                db.updateNganhName(nganhId, newName);
                Toast.makeText(this, "Đã cập nhật ngành!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Tên ngành không được để trống", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
