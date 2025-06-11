package com.example.lab04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class DrinkActivity extends AppCompatActivity {

    private RadioGroup rgDrink;
    private Button btnOrderDrink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        rgDrink = findViewById(R.id.rgDrink);
        btnOrderDrink = findViewById(R.id.btnOrderDrink);

        btnOrderDrink.setOnClickListener(v -> {
            int id = rgDrink.getCheckedRadioButtonId();
            if (id == -1) {
                Toast.makeText(this, "Vui lòng chọn đồ uống", Toast.LENGTH_SHORT).show();
                return;
            }
            String drink = ((RadioButton)findViewById(id)).getText().toString();
            Intent data = new Intent();
            data.putExtra("selectedDrink", drink);
            setResult(RESULT_OK, data);
            finish();
        });
    }
}
