package com.example.canteen_management;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class LoginActivity extends AppCompatActivity {
    EditText userid, userpass;
    String userId, userPass;
    GoogleSignInOptions gso;
    GoogleSignInAccount account;
    GoogleSignInClient mGoogleSignInClient;
    FirebaseFirestore ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ref = FirebaseFirestore.getInstance();
        // Google signIn
        findViewById(R.id.sign_in_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        // Firebase Login
        findViewById(R.id.loginbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userid = (EditText) findViewById(R.id.userId);
                userpass = (EditText) findViewById(R.id.userPassword);
                userId = userid.getText().toString();
                userPass = userpass.getText().toString();
                firebaseLogin(userId,userPass,ref);
            }
        });
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void firebaseLogin(String userId, String userPass, FirebaseFirestore ref) {
        // Logic
        if(userId.charAt(0) == 'A') {
            ref.collection("admin")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d(TAG, document.getId() + " => " + document.getData());
                                    Admin ad = document.toObject(Admin.class);
                                    if(ad.getAdminId().equals(userId) && ad.getAdminPass().equals(userPass)) {
                                        Intent intent = new Intent(LoginActivity.this, Apps.class);
                                        startActivity(intent);
                                    }
                                }
                            } else {
                                Log.w(TAG, "Error getting documents.", task.getException());
                            }
                        }
                    });
        }
        else {
            // User Login
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        account = GoogleSignIn.getLastSignedInAccount(this);
        makeChange(account);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, 1);
    }

    private void makeChange(GoogleSignInAccount account) {
        if(account != null) {
            Intent intent = new Intent(this, Apps.class);
            startActivity(intent);
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            makeChange(account);
        } catch (ApiException e) {
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            makeChange(null);
        }
    }
}