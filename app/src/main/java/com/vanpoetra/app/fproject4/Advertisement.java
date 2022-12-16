package com.vanpoetra.app.fproject4;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Advertisement extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertisement);

        ImageView imageView = (ImageView) findViewById(R.id.REDDOR);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.reddoorz.com/"));
                startActivity(intent);
            }
        });

        imageView = (ImageView) findViewById(R.id.PEGIPEGI);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.pegipegi.com/"));
                startActivity(intent);
            }
        });

        imageView = (ImageView) findViewById(R.id.TIKETDOTCOM);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.tiket.com/"));
                startActivity(intent);
            }
        });
    }


    public void TICKETLIST(View view) {
        startActivity(new Intent(Advertisement.this, order.class));
    }

    public void ACCOUNT(View view) {
        startActivity(new Intent(Advertisement.this, Account.class));
    }

    public void HOME(View view) {
        startActivity(new Intent(Advertisement.this, Home.class));
    }
}