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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity {

    TextView btnReg;
    EditText eTName, eTEmail, eTPhone, eTPass, eTConpass;
    String inputName, inputEmail, inputPhone, inputPass, inputConPass;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private FirebaseAuth mAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        eTName = findViewById(R.id.editTextSignupName);
        eTEmail = findViewById(R.id.editTextSignupEmail);
        eTPhone = findViewById(R.id.editTextSignUpPhoneNumber);
        eTPass = findViewById(R.id.editTextSignUpPassword);
        eTConpass = findViewById(R.id.editTextConfirmPassword);
        btnReg = findViewById(R.id.buttonRegister);

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

    }

    public void REGISTER (View view) {

        inputName = eTName.getText().toString();
        inputEmail = eTEmail.getText().toString().trim();
        inputPhone = eTPhone.getText().toString().trim();
        inputPass = eTPass.getText().toString().trim();
        inputConPass = eTConpass.getText().toString().trim();

        if (!TextUtils.isEmpty(inputName)) {
            if (!TextUtils.isEmpty(inputEmail)) {
                if (inputEmail.matches(emailPattern)) {
                    if (!TextUtils.isEmpty(inputPhone)) {
                        if (inputPhone.length() == 12) {
                            if (!TextUtils.isEmpty(inputPass)) {
                                if (!TextUtils.isEmpty(inputConPass)) {
                                    if (inputPass.equals(inputPass)) {
                                        SignUpUser();
                                    } else {
                                        eTConpass.setError("Konfirmasi Password and Password harus sama.");
                                    }
                                } else {
                                    eTConpass.setError("Kolom Konfirmasi Password tidak boleh kosong");
                                }
                            } else {
                                eTPass.setError("Kolom Password tidak boleh kosong");
                            }
                        } else {
                            eTPhone.setError("Masukkan Nomor Telepon yang valid");
                        }
                    } else {
                        eTPhone.setError("Kolom Nomor Telepon tidak boleh kosong");
                    }
                } else {
                    eTEmail.setError("Masukkan Email yang valid");
                }
            } else {
                eTEmail.setError("Kolom Email tidak boleh kosong");
            }
        } else {
            eTName.setError("Kolom Nama tidak boleh kosong");
        }

    }



    private void SignUpUser() {

        btnReg.setVisibility(View.INVISIBLE);

        mAuth.createUserWithEmailAndPassword(inputEmail, inputPass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(register.this, "Registrasi berhasil !", Toast.LENGTH_SHORT).show();
                mAuth.signInWithEmailAndPassword(inputEmail, inputPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        Intent intent = new Intent(register.this, finish_registration.class);
                        intent.putExtra("NAME_USER",inputName);
                        intent.putExtra("EMAIL_USER",inputEmail);
                        intent.putExtra("PHONE_USER",inputPhone);
                        intent.putExtra("PASS_USER", inputPass);
                        startActivity(intent);
                    }
                });
                finish();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(register.this, "Error " + e.getMessage(), Toast.LENGTH_SHORT).show();
                btnReg.setVisibility(View.VISIBLE);
            }
        });

    }
}