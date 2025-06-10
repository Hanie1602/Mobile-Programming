package com.example.lab3;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class CustomListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list);

        ListView lv = findViewById(R.id.lvCustom);
        List<Fruit> fruits = new ArrayList<>();
        fruits.add(new Fruit("apple",    "apple ... some description goes here",    R.drawable.apple));
        fruits.add(new Fruit("banana",   "banana ... some description goes here",   R.drawable.banana));
        fruits.add(new Fruit("blueberry","blueberry ... some description goes here",R.drawable.blueberry));
        fruits.add(new Fruit("corn",     "corn ... some description goes here",     R.drawable.corn));
        fruits.add(new Fruit("grapes",   "grapes ... some description goes here",   R.drawable.grapes));

        FruitAdapter adapter = new FruitAdapter(this, R.layout.item_fruit, fruits);
        lv.setAdapter(adapter);
    }
}

