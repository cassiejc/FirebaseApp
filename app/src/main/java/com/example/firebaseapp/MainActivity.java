package com.example.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        String email = "test123@gmail.com";
        String pass = "testpass123";
        createuser(email,pass);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser!=null) {
            //load activity with authenticated user
        }
    }

    public void signin(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Log.d("FIREBASE","sign in succes");
                            updateUI(user);
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "Authentication failed",
                                    Toast.LENGTH_LONG).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) {

    }

    public void createuser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d("FIREBASE", "createusersuccess");
                } else{

                }
            }
        });

    }
}

//add googleservice.json in app folder (project)
//Add in build.gradle.kts (module.app)
// implementation("com.google.firebase:firebase-bom:32.6.0")
// implementation("com.google.firebase:firebase-auth")
// id("com.google.gms.google-services")
//Add in build.gradle.kts (project.FirebaseApp)
//id("com.google.gms.google-services") version "4.4.0" apply false}
//sync
//After run the App, the email and pass will be add in firebase auth