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

import java.text.NumberFormat;
import java.util.Locale;

public class PaymentBNI extends AppCompatActivity {

    Toolbar toolbar;
    TextView tvBNI, tvTotalCost, tvBNICode;
    String price, orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_bni);


        toolbar = findViewById(R.id.tbToolbar);
        tvBNI = findViewById(R.id.tv_BNI);
        tvTotalCost = findViewById(R.id.tv_TotalCostBNI);
        tvBNICode = findViewById(R.id.tv_BNICODE);

        price = getIntent().getStringExtra("PRICE");
        orderId = getIntent().getStringExtra("ORDER_ID");

        String totalCost = formatRupiah(Double.valueOf(price));
        tvTotalCost.setText(totalCost);
        tvBNI.setText(totalCost);
        tvBNICode.setText(orderId);


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

    public void VerifyPayment(View view) {


        Intent intent = new Intent(PaymentBNI.this, Home.class);
        startActivity(intent);
    }
}