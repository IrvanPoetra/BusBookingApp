package com.vanpoetra.app.fproject4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class scheedule_list extends AppCompatActivity implements ItemClickListener{

    Toolbar toolbar;
    TextView tvCityDeparture, tvCityArrival, tvDateDeparture, tvSeatCount;
    private RecyclerView recyclerView;
    private BusAdapter adapter;
    private List<BookingDetail> bookingDetails;
    private List<Bus> busList;
    private List<UserDetail> userDetails;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheedule_list);

        tvCityDeparture = findViewById(R.id.tv_CityDeparture);
        tvCityArrival = findViewById(R.id.tv_CityArrival);
        tvDateDeparture = findViewById(R.id.tv_DateDeparture);
        tvSeatCount = findViewById(R.id.tv_SeatCount);
        toolbar = findViewById(R.id.tbToolbar);
        recyclerView = findViewById(R.id.recyclerBus);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        busList = new ArrayList<>();
        bookingDetails = new ArrayList<>();
        userDetails = new ArrayList<>();
        adapter = new BusAdapter(this, busList);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        String fromBus = getIntent().getStringExtra("FROM_BUS");
        String toBus = getIntent().getStringExtra("TO_BUS");
        String countSeat = getIntent().getStringExtra("SEAT_COUNT");
        String dateBus = getIntent().getStringExtra("DATE_BUS");
        String scheduleBus = fromBus+"-"+toBus+"-"+dateBus;

        tvCityDeparture.setText(fromBus.toUpperCase());
        tvCityArrival.setText(toBus.toUpperCase());
        tvDateDeparture.setText(dateBus.toUpperCase());
        tvSeatCount.setText("SEAT "+countSeat);

        FirebaseDatabase.getInstance().getReference("busList")
                .orderByChild("scheduleBus").equalTo(scheduleBus)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                Bus bus =snapshot.getValue(Bus.class);
                                busList.add(bus);
                            }
                        }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(scheedule_list.this, "Firebase Database Error", Toast.LENGTH_SHORT).show();

                    }
                });

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
        FirebaseUser user = mAuth.getCurrentUser();
        DatabaseReference databaseReferenceA= FirebaseDatabase.getInstance().getReference().child("Users").child(user.getUid()).child("BookingDetails");
        databaseReferenceA.removeValue();
    }

    @Override
    public void onClick(View view, int position) {
        Bus bus = busList.get(position);
        String poName = bus.getPoName();
        String busNo = bus.getBusNo();
        String cityDeparture = bus.getCityDeparture();
        String cityArrival = bus.getCityArrival();
        String terminalDeparture = bus.getTerminalDeparture();
        String terminalArrival = bus.getTerminalArrival();
        String dateDeparture = bus.getDateDeparture();
        String dateArrival = bus.getDateArrival();
        String timeDeparture = bus.getTimeDeparture();
        String timeArrival = bus.getTimeArrival();
        String price = bus.getPrice();

        Intent intent = new Intent(scheedule_list.this,DetailPayment.class);
        intent.putExtra("PO_NAME", poName);
        intent.putExtra("BUS_NO", busNo);
        intent.putExtra("CITY_DEP", cityDeparture);
        intent.putExtra("CITY_ARR", cityArrival);
        intent.putExtra("TERM_DEP", terminalDeparture);
        intent.putExtra("TERM_ARR", terminalArrival);
        intent.putExtra("DATE_DEP", dateDeparture);
        intent.putExtra("DATE_ARR", dateArrival);
        intent.putExtra("TIM_DEP", timeDeparture);
        intent.putExtra("TIME_ARR", timeArrival);
        intent.putExtra("PRICE", price);
        startActivity(intent);
    }
}