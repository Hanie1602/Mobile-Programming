package com.example.lab2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener{
   //View: Các View trong Layout
    private EditText etUsername;
    private EditText etPassword;
    private TextView tvNotAccountYet;
    private Button btnSignIn;

    // Notify: Thông báo lỗi khi để trống
    private final String REQUIRE = "Require";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        // Ánh xạ View từ Layout
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        tvNotAccountYet = findViewById(R.id.tvNotAccountYet);
        btnSignIn= findViewById(R.id.btnSignIn);

        // Đăng ký sự kiện click
        tvNotAccountYet.setOnClickListener(this);
        btnSignIn.setOnClickListener(this);
    }

    //Check input không rỗng
    private boolean checkInput() {
        // Username
        if (TextUtils.isEmpty(etUsername.getText().toString())) {
            etUsername.setError(REQUIRE);
            return false;
        }

        // Password
        if (TextUtils.isEmpty(etPassword.getText().toString())) {
            etPassword.setError(REQUIRE);
            return false;
        }

        // Valid
        return true;
    }

    //Xử lý đăng nhập
    private void signIn() {
        // Nếu ko hợp lệ thì dừng
        if (!checkInput()) {
            return;
        }

        // Nếu hợp lệ thì chuyển sang CalculatorActivity
        Intent intent = new Intent(this, CalculatorActivity.class);
        startActivity(intent);
        finish(); // Close current activity

    }

    //Mở form đăng ký
    private void signUpForm() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnSignIn) {
            signIn();       // Nhấn nút SIGN IN
        } else if (id == R.id.tvNotAccountYet) {
            signUpForm();   // Nhấn dòng “Not Account Yet?”
        }
    }

}
