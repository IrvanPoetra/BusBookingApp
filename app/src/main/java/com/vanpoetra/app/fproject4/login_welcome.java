package com.vanpoetra.app.fproject4;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login_welcome extends AppCompatActivity {

    EditText eTEmail, eTPass;
    TextView btnSignIn, btnSignUp;
    String inputEmail, inputPassword;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_welcome);

        eTEmail = findViewById(R.id.editTextLoginEmail);
        eTPass = findViewById(R.id.editTextLoginPass);
        btnSignIn = findViewById(R.id.buttonSignIn);
        btnSignUp = findViewById(R.id.buttonSignUp);

        mAuth = FirebaseAuth.getInstance();
    }
    public void SIGNIN (View view) {

        inputEmail = eTEmail.getText().toString().trim();
        inputPassword = eTPass.getText().toString().trim();

        if (!TextUtils.isEmpty(inputEmail)) {
            if (inputEmail.matches(emailPattern)) {
                if (!TextUtils.isEmpty(inputPassword)) {
                    SignInUser();
                } else {
                    eTPass.setError("Password tidak boleh kosong");
                }
            } else {
                eTEmail.setError("Masukkan Email yang valid");
            }
        } else {
            eTEmail.setError("Email tidak boleh kosong");
        }
    }

    public void SIGNUP (View view) {
        Intent intent = new Intent(login_welcome.this, register.class);
        startActivity(intent);

    }

    private void SignInUser() {

        mAuth.signInWithEmailAndPassword(inputEmail, inputPassword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(login_welcome.this, "Login Berhasil !", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(login_welcome.this, Home.class);
                startActivity(intent);
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(login_welcome.this, "Error - " + e.getMessage(), Toast.LENGTH_SHORT).show();
                btnSignIn.setVisibility(View.VISIBLE);
            }
        });

    }

}