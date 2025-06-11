package com.example.lab04;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.lab04.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_FOOD = 1;
    private static final int REQUEST_DRINK = 2;

    private TextView tvResult;
    private String selectedFood = "";
    private String selectedDrink = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnChooseFood  = findViewById(R.id.btnChooseFood);
        Button btnChooseDrink = findViewById(R.id.btnChooseDrink);
        Button btnExit        = findViewById(R.id.btnExit);
        tvResult             = findViewById(R.id.tvResult);

        btnChooseFood.setOnClickListener(v -> {
            startActivityForResult(
                    new Intent(MainActivity.this, FoodActivity.class),
                    REQUEST_FOOD);
        });

        btnChooseDrink.setOnClickListener(v -> {
            startActivityForResult(
                    new Intent(MainActivity.this, DrinkActivity.class),
                    REQUEST_DRINK);
        });

        btnExit.setOnClickListener(v -> finish());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == REQUEST_FOOD) {
                selectedFood = data.getStringExtra("selectedFood");
            } else if (requestCode == REQUEST_DRINK) {
                selectedDrink = data.getStringExtra("selectedDrink");
            }
            tvResult.setText(selectedFood + " - " + selectedDrink);
        }
    }
}