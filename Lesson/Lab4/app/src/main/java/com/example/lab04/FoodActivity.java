package com.example.lab04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class FoodActivity extends AppCompatActivity {

    private RadioGroup rgFood;
    private Button btnOrderFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        rgFood = findViewById(R.id.rgFood);
        btnOrderFood = findViewById(R.id.btnOrderFood);

        btnOrderFood.setOnClickListener(v -> {
            int id = rgFood.getCheckedRadioButtonId();
            if (id == -1) {
                Toast.makeText(this, "Vui lòng chọn thức ăn", Toast.LENGTH_SHORT).show();
                return;
            }
            String food = ((RadioButton)findViewById(id)).getText().toString();
            Intent data = new Intent();
            data.putExtra("selectedFood", food);
            setResult(RESULT_OK, data);
            finish();
        });
    }
}