package com.example.pe;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pe.database.SQLiteHelper;
import com.example.pe.model.Sinhvien;

public class UpdateSinhvienActivity extends AppCompatActivity {
    EditText edtName, edtDate, edtGender, edtAddress, edtPhone;
    Button btnUpdate;
    SQLiteHelper db;
    int id, idNganh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_sinhvien);

        edtName = findViewById(R.id.edtName);
        edtDate = findViewById(R.id.edtDate);
        edtGender = findViewById(R.id.edtGender);
        edtAddress = findViewById(R.id.edtAddress);
        edtPhone = findViewById(R.id.edtPhone);
        btnUpdate = findViewById(R.id.btnUpdate);
        db = new SQLiteHelper(this);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);
        idNganh = intent.getIntExtra("idNganh", 0);

        edtName.setText(intent.getStringExtra("name"));
        edtDate.setText(intent.getStringExtra("date"));
        edtGender.setText(intent.getStringExtra("gender"));
        edtAddress.setText(intent.getStringExtra("address"));
        edtPhone.setText(intent.getStringExtra("phone"));

        btnUpdate.setOnClickListener(v -> {
            Sinhvien sv = new Sinhvien(
                    id,
                    edtName.getText().toString().trim(),
                    edtDate.getText().toString().trim(),
                    edtGender.getText().toString().trim(),
                    edtAddress.getText().toString().trim(),
                    idNganh,
                    edtPhone.getText().toString().trim()
            );
            db.updateSinhvien(sv);
            Toast.makeText(this, "Đã cập nhật!", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(this, MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        });
    }
}

