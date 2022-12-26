package com.vanpoetra.app.fproject4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;

public class DetailPayment extends AppCompatActivity {

    Toolbar toolbar;
    TextView btnContinuePayment, tvPOBus, tvBusNo,tvDateDeparture,tvDateArrival,tvCityDeparture,tvCityArrival,tvTimeDeparture,tvTimeArrival,tvTerminalDeparture,tvTerminalArrival,tvPrice;
    TextView tvName, tvPhone, tvSeats, tvCountTicket, tvTotalPrice, tvTravelTime, tvOrderId;
    DatabaseReference databaseReference;
    FirebaseAuth mAuth;
    String orderId, userName, userPhone, totalPrice, travelTime, seatCount, poName, busNo, cityDeparture, cityArrival, terminalDeparture, terminalArrival, dateDeparture, dateArrival, timeDeparture, timeArrival, price, time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_payment);

        tvName = findViewById(R.id.tv_Name);
        tvPhone = findViewById(R.id.tv_PhoneNumber);
        tvSeats = findViewById(R.id.tv_Seats);
        tvOrderId = findViewById(R.id.tv_OrderId);
        tvCountTicket = findViewById(R.id.tv_CountTicket);
        tvTotalPrice = findViewById(R.id.tv_TotalPrice);
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
        tvTravelTime = findViewById(R.id.tv_TravelTime);


        toolbar = findViewById(R.id.tbToolbar);
        btnContinuePayment = findViewById(R.id.buttonContinuePayment);

        databaseReference = FirebaseDatabase.getInstance().getReference("");
        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();
        databaseReference.child("Users").child(user.getUid()).child("UserDetails").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    userName = dataSnapshot.child("userName").getValue(String.class);
                    userPhone = dataSnapshot.child("userPhone").getValue(String.class);

                    tvName.setText(userName);
                    tvPhone.setText(userPhone);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        orderId = UUID.randomUUID().toString().substring(0, 7).toUpperCase();
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
        price = getIntent().getStringExtra("PRICE");
        travelTime = getIntent().getStringExtra("TRAVELTIME");

        String prices = formatRupiah(Double.valueOf(price));

        Double countSeat = Double.valueOf(seatCount);
        Double dprice = Double.valueOf(price);
        Double totalsPrice = countSeat * dprice;
        totalPrice = String.valueOf(totalsPrice);

        String totalPrices = formatRupiah(Double.valueOf(totalsPrice));

        tvOrderId.setText(orderId);
        tvTotalPrice.setText(totalPrices);
        tvCountTicket.setText(seatCount+" x "+prices);
        tvSeats.setText(seatCount);
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
        FirebaseUser user1 = mAuth.getCurrentUser();
        DatabaseReference databaseReferenceA= FirebaseDatabase.getInstance().getReference("Users").child(user1.getUid()).child("Order");
        databaseReferenceA.removeValue();
    }


    public void ContinuePayment (View view) {
        DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.US);
        time = dateFormat.format(Calendar.getInstance().getTime());

        OrderDetail orderDetail = new OrderDetail(orderId, userName, userPhone, seatCount, poName, busNo, cityDeparture, cityArrival, terminalDeparture, terminalArrival, dateDeparture, dateArrival, timeDeparture, timeArrival, travelTime, totalPrice, time);
        FirebaseUser user = mAuth.getCurrentUser();
        databaseReference.child("Users").child(user.getUid()).child("Order").child(orderId).setValue(orderDetail);

        Intent intent = new Intent(DetailPayment.this, PaymentMethod.class);
        intent.putExtra("ORDER_ID", orderId);
        intent.putExtra("PRICE", totalPrice);
        startActivity(intent);
    }

    public void BACK(View view) {
        startActivity(new Intent(DetailPayment.this,scheedule_list.class));

    }

}