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
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class TableRemoveActivity extends AppCompatActivity {

    TextInputEditText tid;
    String tableId;
    FirebaseFirestore ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_remove);
        findViewById(R.id.remT).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tid = findViewById(R.id.tid);
                tableId = tid.getText().toString();
                removeTable(tableId);
            }
        });
    }

    private void removeTable(String tableId) {
        List<String> lst = new ArrayList<>();
        ref = FirebaseFirestore.getInstance();
        ref.collection("tables")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                if(document.getData().get("tableId") != null && document.getData().get("tableId").equals(tableId)) {
                                    lst.add(document.getId());
                                    Log.d(TAG, lst.get(0));
                                }
                            }
                            fn(lst);
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });

    }

    private void fn(List <String> id) {
        if(id.size() == 0) {
            Toast.makeText(this, "Table do not exist", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(TableRemoveActivity.this, Apps.class);
            startActivity(intent);
            finish();
        }
        else {
            FirebaseFirestore fs = FirebaseFirestore.getInstance();
            fs.collection("tables").document(id.get(0)).delete();
            Toast.makeText(this, "Table removed successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(TableRemoveActivity.this, Apps.class);
            startActivity(intent);
            finish();
        }
    }
}