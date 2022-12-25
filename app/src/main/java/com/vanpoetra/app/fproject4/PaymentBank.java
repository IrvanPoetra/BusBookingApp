package com.vanpoetra.app.fproject4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.Locale;

public class PaymentBank extends AppCompatActivity {

    Toolbar toolbar;
    String price, orderId;
    TextView tvTotalCost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_bank);

        toolbar = findViewById(R.id.tbToolbar);
        tvTotalCost = findViewById(R.id.tv_TotalCostBank);

        price = getIntent().getStringExtra("PRICE");
        orderId = getIntent().getStringExtra("ORDER_ID");

        String totalCost = formatRupiah(Double.valueOf(price));
        tvTotalCost.setText(totalCost);


        setToolbar();
    }

    private String formatRupiah(Double number){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(number);
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
        intent.putExtra("ORDER_ID", orderId);
        intent.putExtra("PRICE", price);
        startActivity(intent);
    }

    public void SELECTCIMB(View view) {

        Intent intent = new Intent(PaymentBank.this, PaymentCIMB.class);
        intent.putExtra("ORDER_ID", orderId);
        intent.putExtra("PRICE", price);
        startActivity(intent);
    }
}