package com.example.se1735_duonglnt_se171916;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DangNhapActivity extends AppCompatActivity {
    EditText edtEmail, edtPassword;
    Button btnLogin, btnToRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnToRegister = findViewById(R.id.btnToRegister);

        btnLogin.setOnClickListener(v -> {
            String email = edtEmail.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập email và mật khẩu!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Admin mặc định
            if (email.equals("admin") && password.equals("123")) {
                getSharedPreferences("USER", MODE_PRIVATE).edit().putString("username", email).apply();
                startActivity(new Intent(this, MainActivity.class));
                finish();
                return;
            }

            // Lấy dữ liệu từ SharedPreferences
            String savedEmail = getSharedPreferences("USER", MODE_PRIVATE).getString("email", "");
            String savedPassword = getSharedPreferences("USER", MODE_PRIVATE).getString("password", "");

            if (email.equals(savedEmail) && password.equals(savedPassword)) {
                Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                getSharedPreferences("USER", MODE_PRIVATE).edit().putString("username", email).apply();
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Sai tài khoản hoặc mật khẩu!", Toast.LENGTH_SHORT).show();
            }
        });

        btnToRegister.setOnClickListener(v -> {
            startActivity(new Intent(DangNhapActivity.this, DangKyActivity.class));
        });
    }
}
