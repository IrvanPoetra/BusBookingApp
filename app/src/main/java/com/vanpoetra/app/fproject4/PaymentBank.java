package com.vanpoetra.app.fproject4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PaymentBank extends AppCompatActivity {

    Toolbar toolbar;
    DatabaseReference databaseReference;
    FirebaseAuth mAuth;
    String uniqueId, userName, userPhone, totalPrice, travelTime, seatCount, poName, busNo, cityDeparture, cityArrival, terminalDeparture, terminalArrival, dateDeparture, dateArrival, timeDeparture, timeArrival;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_bank);

        toolbar = findViewById(R.id.tbToolbar);

        databaseReference = FirebaseDatabase.getInstance().getReference("");
        mAuth = FirebaseAuth.getInstance();

        setToolbar();
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        if (this.getSupportActionBar() != null) {
            ActionBar btnback = this.getSupportActionBar();

            if(btnback != null) {
                btnback.setDisplayHomeAsUpEnabled(true);
            }

            if(btnback != null) {
                btnback.setDisplayShowTitleEnabled(false);
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void SELECTBNI(View view) {

        Intent intent = new Intent(PaymentBank.this, PaymentBNI.class);
        startActivity(intent);
    }

    public void SELECTCIMB(View view) {

        Intent intent = new Intent(PaymentBank.this, PaymentCIMB.class);
        startActivity(intent);
    }
}