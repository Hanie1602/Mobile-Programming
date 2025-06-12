package com.example.lab5;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {
    ArrayList<User> userList;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        //Lookup the RecycleView in Activity layout
        RecyclerView rvUser = findViewById(R.id.rvUsers);

        //initialize user list
        userList = new ArrayList<>();
        userList.add(new User("NguyenTT", "Tran Nguyen Thanh", "Nguyentt@fpt.edu.vn"));
        userList.add(new User("Antv", "Tran Van An", "antv@fpt.edu.vn"));
        userList.add(new User("BangTT", "Tran Thanh Bang", "bangtt@fpt.edu.vn"));
        userList.add(new User("KhangTT", "Tran Thanh Khang", "khangtt@fpt.edu.vn"));
        userList.add(new User("BaoNT", "Nguyen Thanh Bao", "baott@fpt.edu.vn"));
        userList.add(new User("HungVH", "Vo Huy Hung", "hungvh@fpt.edu.vn"));

        //Create adapter passing in the sample user data
        UserAdapter adapter = new UserAdapter(userList);

        //Attach the Adapter to the RecycleView to populist item
        rvUser.setAdapter(adapter);

        //set layout manager to position the item
        rvUser.setLayoutManager(new LinearLayoutManager(this));
    }
}
