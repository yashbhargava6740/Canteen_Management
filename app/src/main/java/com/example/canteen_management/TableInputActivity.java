package com.example.canteen_management;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.canteen_management.Table;

public class TableInputActivity extends AppCompatActivity {

    TextInputEditText tid;
    String tableId;
    FirebaseFirestore ref;
    Table tb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_input);
        findViewById(R.id.addT).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tid = findViewById(R.id.tid);
                tableId = tid.getText().toString();
                addTable(tableId);
            }
        });
    }

    private void addTable(String tableId) {
        List<String> ids = new ArrayList<>();
        ref = FirebaseFirestore.getInstance();
        ref.collection("tables")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                if(document.getData().get("tableId") != null) {
                                    ids.add(document.getData().get("tableId").toString());
                                }
                            }
                            addTable(tableId, ids);
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    private void addTable(String tableId, List<String>lst) {
        boolean flag = false;
        for(int i = 0; i < lst.size(); i++) {
            if(lst.get(i).equals(tableId)) {
                Toast.makeText(this, "Table Already Exists", Toast.LENGTH_SHORT).show();
                flag = true;
                break;
            }
        }
        if(flag) {
            Intent intent = new Intent(TableInputActivity.this, Apps.class);
            startActivity(intent);
            finish();
        }
        else {
            ref = FirebaseFirestore.getInstance();
            tb = new Table(tableId,false);
            ref.collection("tables")
                    .add(tb)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(TableInputActivity.this, "Table Added Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(TableInputActivity.this, TableActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error adding document", e);

                        }
                    });
        }
    }
}