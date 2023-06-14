package com.example.canteen_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        findViewById(R.id.addT).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTable();
            }
        });

        findViewById(R.id.removeT).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeTable();
            }
        });

        findViewById(R.id.viewT).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewTables();
            }
        });
    }

    private void viewTables() {
        Intent intent = new Intent(this, ViewTableActivity.class);
        startActivity(intent);
    }

    private void removeTable() {
        Intent intent = new Intent(this, TableRemoveActivity.class);
        startActivity(intent);
    }

    private void addTable() {
        Intent intent = new Intent(this, TableInputActivity.class);
        startActivity(intent);
    }
}