package com.example.lab2;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etNum1, etNum2;
    private TextView tvCalcResult;
    private Button btnAdd, btnSub, btnMul, btnDiv;
    private final String REQUIRE = "Require";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        etNum1       = findViewById(R.id.etNum1);
        etNum2       = findViewById(R.id.etNum2);
        tvCalcResult = findViewById(R.id.tvCalcResult);

        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        btnMul = findViewById(R.id.btnMul);
        btnDiv = findViewById(R.id.btnDiv);

        btnAdd.setOnClickListener(this);
        btnSub.setOnClickListener(this);
        btnMul.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
    }

    private boolean checkInput() {
        if (TextUtils.isEmpty(etNum1.getText())) {
            etNum1.setError(REQUIRE);
            return false;
        }
        if (TextUtils.isEmpty(etNum2.getText())) {
            etNum2.setError(REQUIRE);
            return false;
        }
        return true;
    }

    private void calculate(char op) {
        if (!checkInput()) return;

        double a = Double.parseDouble(etNum1.getText().toString());
        double b = Double.parseDouble(etNum2.getText().toString());
        double res;

        switch (op) {
            case '+': res = a + b; break;
            case '-': res = a - b; break;
            case '*': res = a * b; break;
            case '/':
                if (b == 0) {
                    tvCalcResult.setText("Không chia được cho 0");
                    return;
                }
                res = a / b; break;
            default: return;
        }
        tvCalcResult.setText("Kết quả: " + res);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if      (id == R.id.btnAdd) calculate('+');
        else if (id == R.id.btnSub) calculate('-');
        else if (id == R.id.btnMul) calculate('*');
        else if (id == R.id.btnDiv) calculate('/');
    }
}
