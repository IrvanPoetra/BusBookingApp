package com.vanpoetra.app.fproject4;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class order_detail extends AppCompatActivity {

    Toolbar toolbar;
    ImageButton btnQRCode;
    Dialog nDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        toolbar = findViewById(R.id.tbToolbar);
        btnQRCode = findViewById(R.id.buttonQRCode);
        nDialog = new Dialog(this);

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

    public void SHOWQR(View view) {
        TextView btnQRBack;

        nDialog.setContentView(R.layout.ticket_qr_popup);
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
}