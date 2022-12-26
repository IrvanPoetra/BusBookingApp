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

import java.text.NumberFormat;
import java.util.Locale;
import java.util.UUID;

public class ticket_detail extends AppCompatActivity {

    Toolbar toolbar;
    ImageButton btnQRCode;
    Dialog nDialog;
    TextView tvPOBus,tvBusNo,tvDateDeparture,tvDateArrival,tvCityDeparture,tvCityArrival,tvTimeDeparture,tvTimeArrival,tvTerminalDeparture,tvTerminalArrival;
    TextView tvOrderId,tvName, tvPhone, tvSeats, tvTotalPrice, tvTravelTime, tvDateTicket;
    String orderId, userName, userPhone, totalPrice, travelTime, seatCount, poName, busNo, cityDeparture, cityArrival, terminalDeparture, terminalArrival, dateDeparture, dateArrival, timeDeparture, timeArrival;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_detail);

        toolbar = findViewById(R.id.tbToolbar);
        btnQRCode = findViewById(R.id.buttonQRCode);
        nDialog = new Dialog(this);

        tvDateTicket = findViewById(R.id.tv_DateTicket);
        tvOrderId = findViewById(R.id.tv_TicketCode);
        tvName = findViewById(R.id.tv_NameTicket);
        tvPhone = findViewById(R.id.tv_PhoneTicket);
        tvSeats = findViewById(R.id.tv_SeatsTicket);
        tvTotalPrice = findViewById(R.id.tv_TotalCostTicket);
        tvPOBus = findViewById(R.id.tv_poBusTicket);
        tvBusNo = findViewById(R.id.tv_BusNoTicket);
        tvDateDeparture = findViewById(R.id.tv_DateDepartureTicket);
        tvDateArrival = findViewById(R.id.tv_DateArrivalTicket);
        tvCityDeparture = findViewById(R.id.tv_CityDepartureTicket);
        tvCityArrival = findViewById(R.id.tv_CityArrivalTicket);
        tvTimeDeparture = findViewById(R.id.tv_TimeDepartureTicket);
        tvTimeArrival = findViewById(R.id.tv_TimeArrivalTicket);
        tvTerminalDeparture = findViewById(R.id.tv_TerminalDepartureTicket);
        tvTerminalArrival = findViewById(R.id.tv_TerminalArrivalTicket);
        tvTravelTime = findViewById(R.id.tv_TravelTimeTicket);

        orderId = getIntent().getStringExtra("ORDER_ID");
        userName = getIntent().getStringExtra("USER_NAME");
        userPhone = getIntent().getStringExtra("USER_PHONE");
        seatCount= getIntent().getStringExtra("SEAT_COUNT");
        poName = getIntent().getStringExtra("PO_NAME");
        busNo = getIntent().getStringExtra("BUS_NO");
        cityDeparture = getIntent().getStringExtra("CITY_DEP");
        cityArrival = getIntent().getStringExtra("CITY_ARR");
        terminalDeparture = getIntent().getStringExtra("TERM_DEP");
        terminalArrival = getIntent().getStringExtra("TERM_ARR");
        dateDeparture = getIntent().getStringExtra("DATE_DEP");
        dateArrival = getIntent().getStringExtra("DATE_ARR");
        timeDeparture = getIntent().getStringExtra("TIM_DEP");
        timeArrival = getIntent().getStringExtra("TIME_ARR");
        totalPrice = getIntent().getStringExtra("PRICE");
        travelTime = getIntent().getStringExtra("TRAVELTIME");

        String totalPrices = formatRupiah(Double.valueOf(totalPrice));

        tvDateTicket.setText(dateDeparture +"  "+ timeDeparture);
        tvOrderId.setText("Book Code: "+orderId);
        tvName.setText(userName);
        tvPhone.setText(userPhone);
        tvSeats.setText(seatCount);
        tvTotalPrice.setText(totalPrices);
        tvPOBus.setText(poName);
        tvBusNo.setText(busNo);
        tvDateDeparture.setText(dateDeparture);
        tvDateArrival.setText(dateArrival);
        tvCityDeparture.setText(cityDeparture);
        tvCityArrival.setText(cityArrival);
        tvTimeDeparture.setText(timeDeparture);
        tvTimeArrival.setText(timeArrival);
        tvTerminalDeparture.setText(terminalDeparture);
        tvTerminalArrival.setText(terminalArrival);
        tvTravelTime.setText(travelTime);

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