package com.example.se1735_duonglnt_se171916;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.se1735_duonglnt_se171916.database.SQLiteHelper;

public class TraineeDetailActivity extends AppCompatActivity {
    TextView tvName, tvDate, tvGender, tvAddress, tvPhone, tvIdNganh;
    Button btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainee_detail);

        tvName = findViewById(R.id.tvName);
        tvDate = findViewById(R.id.tvDate);
        tvGender = findViewById(R.id.tvGender);
        tvAddress = findViewById(R.id.tvAddress);
        tvPhone = findViewById(R.id.tvPhone);
        tvIdNganh = findViewById(R.id.tvIdNganh);
        btnEdit = findViewById(R.id.btnEdit);

        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String date = intent.getStringExtra("date");
        String gender = intent.getStringExtra("gender");
        String address = intent.getStringExtra("address");
        String phone = intent.getStringExtra("phone");
        int idNganh = intent.getIntExtra("idNganh", 0);

        // Hiển thị
        tvName.setText("Tên: " + name);
        tvDate.setText("Ngày sinh: " + date);
        tvGender.setText("Giới tính: " + gender);
        tvAddress.setText("Địa chỉ: " + address);
        tvPhone.setText("SĐT: " + phone);

        //Lấy tên ngành
        SQLiteHelper db = new SQLiteHelper(this);
        String tenNganh = db.getNganhNameById(idNganh);
        tvIdNganh.setText("Ngành: " + tenNganh);

        //Nút chỉnh sửa
        btnEdit.setOnClickListener(v -> {
            Intent i = new Intent(this, UpdateSinhvienActivity.class);
            i.putExtra("id", getIntent().getIntExtra("id", -1));
            i.putExtra("name", getIntent().getStringExtra("name"));
            i.putExtra("date", getIntent().getStringExtra("date"));
            i.putExtra("gender", getIntent().getStringExtra("gender"));
            i.putExtra("address", getIntent().getStringExtra("address"));
            i.putExtra("phone", getIntent().getStringExtra("phone"));
            i.putExtra("idNganh", getIntent().getIntExtra("idNganh", 0));
            startActivity(i);
        });
    }
}
