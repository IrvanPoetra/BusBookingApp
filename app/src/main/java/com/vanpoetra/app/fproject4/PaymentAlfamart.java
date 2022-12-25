package com.vanpoetra.app.fproject4;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
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

public class PaymentAlfamart extends AppCompatActivity {

    Toolbar toolbar;
    TextView tvAlfamart, tvAlfamartCode;
    ImageButton btnQRCode;
    Dialog nDialog;
    String price, orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_alfamart);

        toolbar = findViewById(R.id.tbToolbar);
        tvAlfamart = findViewById(R.id.tv_Alfamart);
        tvAlfamartCode = findViewById(R.id.tv_AlfamartCode);
        btnQRCode = findViewById(R.id.buttonQRCode);
        nDialog = new Dialog(this);

        price = getIntent().getStringExtra("PRICE");
        orderId = getIntent().getStringExtra("ORDER_ID");

        String totalCost = formatRupiah(Double.valueOf(price));
        tvAlfamartCode.setText(orderId);
        tvAlfamart.setText(totalCost);

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

    public void SHOWQR(View view) {
        TextView btnQRBack;

        nDialog.setContentView(R.layout.alfamart_qr_popup);
        nDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        btnQRBack = nDialog.findViewById(R.id.buttonQRBack);

        btnQRBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nDialog.dismiss();
            }
        });

        nDialog.show();
    }

    public void VerifyPayment(View view) {


        Intent intent = new Intent(PaymentAlfamart.this, order.class);
        startActivity(intent);
    }
}