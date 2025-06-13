package com.example.lab2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    // Views: Các View trong Layout
    private EditText etUsername;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private TextView tvAlreadyAccount;
    private Button btnSignUp;

    // Notify: Thông báo lỗi khi để trống
    private final String REQUIRE = "Require";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Ánh xạ View từ Layout
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        tvAlreadyAccount = findViewById(R.id.tvAlreadyAccount);
        btnSignUp = findViewById (R.id.btnSignUp);

        // Đăng ký sự kiện click
        tvAlreadyAccount.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
    }

    //Check input không rỗng
    private boolean checkInput() {
        if (TextUtils.isEmpty(etUsername.getText().toString())) {
            etUsername.setError(REQUIRE);
            return false;
        }

        if (TextUtils.isEmpty(etPassword.getText().toString())) {
            etPassword.setError(REQUIRE);
            return false;
        }

        if (TextUtils.isEmpty(etConfirmPassword.getText().toString())) {
            etConfirmPassword.setError(REQUIRE);
            return false;
        }

        if (!TextUtils.equals(etPassword.getText().toString()
                , etConfirmPassword.getText().toString())) {
            Toast.makeText(this, "Password are not match", Toast.LENGTH_LONG).show();
            return false;
        }

        // Valid
        return true;
    }

    private void signUp() {
        if (!checkInput()) return;

        SharedPreferences prefs = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        prefs.edit()
                .putString("user_"+etUsername.getText(), etPassword.getText().toString())
                .apply();

        Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
        signInForm();  // chuyển về SignIn
    }

    private void signInForm() {
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnSignUp) {
            signUp();
        } else if (id == R.id.tvAlreadyAccount) {
            signInForm();
        }
    }
}