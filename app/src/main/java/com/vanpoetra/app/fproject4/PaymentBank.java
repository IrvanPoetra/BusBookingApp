package com.vanpoetra.app.fproject4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class PaymentBank extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_bank);

        toolbar = findViewById(R.id.tbToolbar);

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