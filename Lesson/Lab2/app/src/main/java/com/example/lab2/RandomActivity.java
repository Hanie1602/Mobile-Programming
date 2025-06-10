package com.example.lab2;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class RandomActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etMin, etMax;
    private TextView tvResult;
    private Button btnGenerate;
    private final String REQUIRE = "Require";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        etMin       = findViewById(R.id.et_min);
        etMax       = findViewById(R.id.et_max);
        tvResult    = findViewById(R.id.tv_result);
        btnGenerate = findViewById(R.id.btnGenerate);

        btnGenerate.setOnClickListener(this);
    }

    private boolean checkInput() {
        if (TextUtils.isEmpty(etMin.getText())) {
            etMin.setError(REQUIRE);
            return false;
        }
        if (TextUtils.isEmpty(etMax.getText())) {
            etMax.setError(REQUIRE);
            return false;
        }
        return true;
    }

    private void generateRandom() {
        if (!checkInput()) return;

        int min = Integer.parseInt(etMin.getText().toString());
        int max = Integer.parseInt(etMax.getText().toString());
        if (min > max) {
            tvResult.setText("Min phải ≤ Max");
            return;
        }

        int r = new Random().nextInt(max - min + 1) + min;
        tvResult.setText("Kết quả: " + r);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnGenerate) {
            generateRandom();
        }
    }
}
