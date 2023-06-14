package com.example.canteen_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.canteen_management.Adapter.Adapter;
import com.example.canteen_management.Model.Model;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Apps extends AppCompatActivity {

    ListView lv;
    ArrayList<Model> models = new ArrayList<>();
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps);
        lv = findViewById(R.id.lv);
        BindData();
        findViewById(R.id.tableSet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Apps.this, TableActivity.class);
                startActivity(intent);
            }
        });
    }
    void BindData() {
        // Just Update details from database
        models.add(new Model("O101","T101","Chips, Kurkure, Burger", true));
        adapter = new Adapter(getApplicationContext(), models);
        lv.setAdapter(adapter);
    }
}