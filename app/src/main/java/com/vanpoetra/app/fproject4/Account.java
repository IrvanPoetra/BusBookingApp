package com.vanpoetra.app.fproject4;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Account extends AppCompatActivity {

    TextView tvNama, tvPhone, tvEmail;
    FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    String viewName, viewPhone, viewEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        tvNama = findViewById(R.id.tv_Nama);
        tvPhone = findViewById(R.id.tv_Phone);
        tvEmail = findViewById(R.id.tv_Email);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();
        databaseReference.child("Users").child(user.getUid()).child("UserDetails").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    viewName = snapshot.child("userName").getValue(String.class);
                    viewPhone = snapshot.child("userPhone").getValue(String.class);
                    viewEmail = snapshot.child("userEmail").getValue(String.class);

                    tvNama.setText(viewName);
                    tvPhone.setText(viewPhone);
                    tvEmail.setText(viewEmail);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void LOGOUT(View view) {
        mAuth.signOut();
        startActivity(new Intent(Account.this, MainActivity.class));
    }

    public void HOME(View view) {
        startActivity(new Intent(Account.this, Home.class));

    }

    public void ADVERTISEMENT(View view) {
        startActivity(new Intent(Account.this, Advertisement.class));

    }

    public void TICKETLIST(View view) {
        startActivity(new Intent(Account.this, order.class));

    }
}
