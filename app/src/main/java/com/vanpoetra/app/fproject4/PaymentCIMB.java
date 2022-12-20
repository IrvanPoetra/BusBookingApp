package com.vanpoetra.app.fproject4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PaymentCIMB extends AppCompatActivity {

    Toolbar toolbar;
    TextView tvCIMB;
    DatabaseReference databaseReference;
    FirebaseAuth mAuth;
    String uniqueId, userName, userPhone, totalPrice, travelTime, seatCount, poName, busNo, cityDeparture, cityArrival, terminalDeparture, terminalArrival, dateDeparture, dateArrival, timeDeparture, timeArrival;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_cimb);

        toolbar = findViewById(R.id.tbToolbar);
        tvCIMB = findViewById(R.id.tv_CIMB);

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

    public void VerifyPayment(View view) {


        Intent intent = new Intent(PaymentCIMB.this, order.class);
        startActivity(intent);
    }
}