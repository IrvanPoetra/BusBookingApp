package com.vanpoetra.app.fproject4;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private int waktu_loading = 1000;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mAuth.getCurrentUser() != null){
                    Intent home = new Intent(MainActivity.this, Home.class);
                    startActivity(home);
                }else {
                    Intent home = new Intent(MainActivity.this, login_welcome.class);
                    startActivity(home);
                }

                finish();

            }
        }, waktu_loading);
    }
}