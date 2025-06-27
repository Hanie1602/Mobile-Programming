package com.example.se1735_duonglnt_se171916;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AccountActivity extends AppCompatActivity {
    TextView tvEmail;
    Button btnLogout, btnDeleteAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        tvEmail = findViewById(R.id.tvEmail);
        btnLogout = findViewById(R.id.btnLogout);
        btnDeleteAccount = findViewById(R.id.btnDeleteAccount);

        String email = getSharedPreferences("USER", MODE_PRIVATE).getString("username", "Chưa đăng nhập");
        tvEmail.setText("Email: " + email);

        btnLogout.setOnClickListener(v -> {
            getSharedPreferences("USER", MODE_PRIVATE).edit().remove("username").apply();
            Toast.makeText(this, "Đã đăng xuất", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, DangNhapActivity.class));
            finish();
        });

        btnDeleteAccount.setOnClickListener(v -> {
            getSharedPreferences("USER", MODE_PRIVATE).edit().clear().apply();
            Toast.makeText(this, "Đã xoá tài khoản", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, DangNhapActivity.class));
            finish();
        });
    }
}
