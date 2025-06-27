package com.example.pe;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pe.database.SQLiteHelper;

public class AddNganhActivity extends AppCompatActivity {
    EditText edtNganh;
    Button btnSave;
    SQLiteHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_nganh);

        edtNganh = findViewById(R.id.edtNganh);
        btnSave = findViewById(R.id.btnSaveNganh);
        db = new SQLiteHelper(this);

        btnSave.setOnClickListener(v -> {
            String name = edtNganh.getText().toString().trim();
            if (!name.isEmpty()) {
                db.insertNganh(0, name); //0 nếu ID tự động tăng
                Toast.makeText(this, "Đã thêm ngành", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
