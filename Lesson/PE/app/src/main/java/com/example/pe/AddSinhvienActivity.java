package com.example.pe;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pe.database.SQLiteHelper;
import com.example.pe.model.Nganh;
import com.example.pe.model.Sinhvien;

import java.util.ArrayList;
import java.util.List;

public class AddSinhvienActivity extends AppCompatActivity {
    EditText edtName, edtDate, edtGender, edtAddress, edtPhone;
    Spinner spinnerNganh;
    Button btnSave;
    SQLiteHelper db;
    List<Nganh> nganhList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sinhvien);

        edtName = findViewById(R.id.edtName);
        edtDate = findViewById(R.id.edtDate);
        edtGender = findViewById(R.id.edtGender);
        edtAddress = findViewById(R.id.edtAddress);
        edtPhone = findViewById(R.id.edtPhone);
        spinnerNganh = findViewById(R.id.spinnerNganh);
        btnSave = findViewById(R.id.btnSaveSinhvien);

        db = new SQLiteHelper(this);

        // Load ngành từ DB vào spinner
        nganhList = db.getAllNganh();
        List<String> nganhNames = new ArrayList<>();
        for (Nganh ng : nganhList) nganhNames.add(ng.getName());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nganhNames);
        spinnerNganh.setAdapter(adapter);

        btnSave.setOnClickListener(v -> {
            String name = edtName.getText().toString().trim();
            String date = edtDate.getText().toString().trim();
            String gender = edtGender.getText().toString().trim();
            String address = edtAddress.getText().toString().trim();
            String phone = edtPhone.getText().toString().trim();
            int idNganh = nganhList.get(spinnerNganh.getSelectedItemPosition()).getId();

            if (!name.isEmpty()) {
                Sinhvien sv = new Sinhvien(0, name, date, gender, address, idNganh, phone);
                db.insertSinhvien(sv);
                Toast.makeText(this, "Đã thêm sinh viên", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
