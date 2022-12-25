package com.vanpoetra.app.fproject4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.NumberFormat;
import java.util.Locale;

public class PaymentMethod extends AppCompatActivity {

    Toolbar toolbar;
    TextView btnBank, btnAlfamart, tvTotalCost;
    String price, orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);

        tvTotalCost = findViewById(R.id.tv_TotalCost);
        toolbar = findViewById(R.id.tbToolbar);
        btnBank = findViewById(R.id.selectBank);
        btnAlfamart = findViewById(R.id.selectRetail);

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

    public void SELECTCC(View view) {
        Intent intent = new Intent(PaymentMethod.this, PaymentCC.class);
        intent.putExtra("ORDER_ID", orderId);
        intent.putExtra("PRICE", price);
        startActivity(intent);
    }

    public void SELECTBANK(View view) {
        Intent intent = new Intent(PaymentMethod.this, PaymentBank.class);
        intent.putExtra("ORDER_ID", orderId);
        intent.putExtra("PRICE", price);
        startActivity(intent);
    }

    public void SELECTALFAMART(View view) {
        Intent intent = new Intent(PaymentMethod.this, PaymentAlfamart.class);
        intent.putExtra("ORDER_ID", orderId);
        intent.putExtra("PRICE", price);
        startActivity(intent);
    }
}