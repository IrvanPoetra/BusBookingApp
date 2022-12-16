package com.vanpoetra.app.fproject4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class DetailPayment extends AppCompatActivity {

    Toolbar toolbar;
    TextView btnContinuePayment, tvPOBus, tvBusNo,tvDateDeparture,tvDateArrival,tvCityDeparture,tvCityArrival,tvTimeDeparture,tvTimeArrival,tvTerminalDeparture,tvTerminalArrival,tvPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_payment);

        tvPOBus = findViewById(R.id.tv_poBus);
        tvBusNo = findViewById(R.id.tv_BusNo);
        tvDateDeparture = findViewById(R.id.tv_DateDeparture);
        tvDateArrival = findViewById(R.id.tv_DateArrival);
        tvCityDeparture = findViewById(R.id.tv_CityDeparture);
        tvCityArrival = findViewById(R.id.tv_CityArrival);
        tvTimeDeparture = findViewById(R.id.tv_TimeDeparture);
        tvTimeArrival = findViewById(R.id.tv_TimeArrival);
        tvTerminalDeparture = findViewById(R.id.tv_TerminalDeparture);
        tvTerminalArrival = findViewById(R.id.tv_TerminalArrval);

        toolbar = findViewById(R.id.tbToolbar);
        btnContinuePayment = findViewById(R.id.buttonContinuePayment);

        String poBus = getIntent().getStringExtra("PO_NAME");
        String busNo = getIntent().getStringExtra("BUS_NO");
        String cityDeparture = getIntent().getStringExtra("CITY_DEP");
        String cityArrival = getIntent().getStringExtra("CITY_ARR");
        String terminalDeparture = getIntent().getStringExtra("TERM_DEP");
        String terminalArrival = getIntent().getStringExtra("TERM_ARR");
        String dateDeparture = getIntent().getStringExtra("DATE_DEP");
        String dateArrival = getIntent().getStringExtra("DATE_ARR");
        String timeDeparture = getIntent().getStringExtra("TIM_DEP");
        String timeArrival = getIntent().getStringExtra("TIME_ARR");

        tvPOBus.setText(poBus);
        tvBusNo.setText(busNo);
        tvDateDeparture.setText(dateDeparture);
        tvDateArrival.setText(dateArrival);
        tvCityDeparture.setText(cityDeparture);
        tvCityArrival.setText(cityArrival);
        tvTimeDeparture.setText(timeDeparture);
        tvTimeArrival.setText(timeArrival);
        tvTerminalDeparture.setText(terminalDeparture);
        tvTerminalArrival.setText(terminalArrival);

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

    public void ContinuePayment (View view) {

        Intent intent = new Intent(DetailPayment.this, PaymentMethod.class);
        startActivity(intent);
    }

    public void BACK(View view) {
        startActivity(new Intent(DetailPayment.this,scheedule_list.class));

    }

}