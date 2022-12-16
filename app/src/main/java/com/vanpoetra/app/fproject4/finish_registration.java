package com.vanpoetra.app.fproject4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class finish_registration extends AppCompatActivity {

    FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    String saveName, saveEmail, savePhone, savePass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_registration);

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("");

        saveName = getIntent().getStringExtra("NAME_USER");
        saveEmail = getIntent().getStringExtra("EMAIL_USER");
        savePhone = getIntent().getStringExtra("PHONE_USER");
        savePass = getIntent().getStringExtra("PASS_USER");

        UserDetail userDetail = new UserDetail(saveName, saveEmail, savePhone);
        FirebaseUser user = mAuth.getCurrentUser();
        databaseReference.child("Users").child(user.getUid()).child("UserDetails").setValue(userDetail);
    }
    public void TAKE(View view) {
        Intent intent = new Intent(finish_registration.this, Home.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        FirebaseUser user1 = mAuth.getCurrentUser();
        DatabaseReference databaseReferenceA= FirebaseDatabase.getInstance().getReference("Users").child(user1.getUid()).child("UserDetails");
        databaseReferenceA.removeValue();
        deleteuser();

        Intent intent = new Intent(finish_registration.this, register.class);
        startActivity(intent);
    }

    private void deleteuser() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        AuthCredential credential = EmailAuthProvider.getCredential(saveEmail, savePass);

        if (user != null) {
            user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    user.delete();
                }
            });
        }

    }
    public void BACK(View view) {
        startActivity(new Intent(finish_registration.this,register.class));

    }

}