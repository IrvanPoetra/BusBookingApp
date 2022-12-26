package com.vanpoetra.app.fproject4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class order extends AppCompatActivity implements ItemClickListener {

    Toolbar toolbar;
    private RecyclerView recyclerView;
    private OrderAdapter adapter;
    private List<Ticket> ticketList;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        toolbar = findViewById(R.id.tbToolbar);
        recyclerView = findViewById(R.id.recyclerTicket);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        ticketList = new ArrayList<>();
        adapter = new OrderAdapter(this, ticketList);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        FirebaseUser user = mAuth.getCurrentUser();
        FirebaseDatabase.getInstance().getReference("Users").child(user.getUid()).child("Order")
                .orderByChild("time")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                Ticket ticket =snapshot.getValue(Ticket.class);
                                ticketList.add(ticket);
                            }
                        }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(order.this, "Firebase Database Error", Toast.LENGTH_SHORT).show();

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
    }

    public void onClick(View view, int position) {
        Ticket ticket = ticketList.get(position);
        String orderID = ticket.getOrderID();
        String userName = ticket.getUserName();
        String userPhone = ticket.getUserPhone();
        String seatCount = ticket.getSeatCount();
        String poName = ticket.getPoName();
        String busNo = ticket.getBusNo();
        String cityDeparture = ticket.getCityDeparture();
        String cityArrival = ticket.getCityArrival();
        String terminalDeparture = ticket.getTerminalDeparture();
        String terminalArrival = ticket.getTerminalArrival();
        String dateDeparture = ticket.getDateDeparture();
        String dateArrival = ticket.getDateArrival();
        String timeDeparture = ticket.getTimeDeparture();
        String timeArrival = ticket.getTimeArrival();
        String travelTime = ticket.getTravelTime();
        String totalPrice = ticket.getTotalPrice();

        Intent intent = new Intent(order.this,ticket_detail.class);
        intent.putExtra("ORDER_ID", orderID);
        intent.putExtra("USER_NAME", userName);
        intent.putExtra("USER_PHONE", userPhone);
        intent.putExtra("SEAT_COUNT", seatCount);
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
        intent.putExtra("TRAVELTIME", travelTime);
        intent.putExtra("PRICE", totalPrice);
        startActivity(intent);
    }

    public void ACCOUNT(View view) {
        startActivity(new Intent(order.this, Account.class));

    }

    public void HOME(View view) {
        startActivity(new Intent(order.this, Home.class));

    }

    public void ADVERTISEMENT(View view) {
        startActivity(new Intent(order.this, Advertisement.class));

    }

}