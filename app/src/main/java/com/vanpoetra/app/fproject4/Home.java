package com.vanpoetra.app.fproject4;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Home extends AppCompatActivity {

    ImageButton btnSearchBus;

    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormatter;
    TextView etSelDate, btnPassenger;
    Spinner spinFrom, spinTo;
    FirebaseAuth mAuth;
    DatabaseReference databaseReference;



    //Menginisialisasi Menu Item pada Variable Array
    private String[] Item = {"", "Aceh Tengah", "Badung", "Balikpapan", "Banda Aceh", "Bandar Lampung",
            "Bandung", "Bangkalan", "Banjar", "Banjarnegara", "Bantaeng", "Bantul", "Banyuasin", "Banyumas",
            "Banyuwangi", "Barru", "Batang", "Batu", "Bekasi", "Belu", "Bengkulu", "Bima", "Bitung", "Blitar",
            "Blora", "Bogor", "Bojonegoro", "Bolaang Mongondow", "Bondowoso", "Bone", "Boyolali", "Brebes",
            "Bukittinggi", "Bungo", "Ciamis", "Cilacap", "Cilegon", "Cirebon", "Demak", "Depok", "Dumai",
            "Ende", "Flores Timur", "Garut", "Gorontalo", "Gowa", "Gresik", "Grobogan", "Gunung Kidul",
            "Indragiri Hulu", "Indramayu", "Jakarta Barat", "Jakarta Pusat", "Jakarta Selatan",
            "Jakarta Timur", "Jakarta Utara", "Jambi", "Jayapura", "Jember", "Jeneponto", "Kampar",
            "Karanganyar", "Karawang", "Kebumen", "Kediri", "Kendari", "Klaten", "Kubu Raya", "Kudus",
            "Kulon Progo", "Kuningan", "Kupang", "Labuhanbatu", "Lahat", "Lamongan", "Lampung Tengah",
            "Langsa", "Lebak", "Lhokseumawe", "Lubuklinggau", "Lumajang", "Luwu", "Madiun", "Magelang",
            "Magetan", "Makassar", "Malang", "Mamuju", "Manado", "Manggarai", "Manggarai Barat", "Maros",
            "Mataram", "Medan", "Merangin", "Meulaboh", "Minahasa", "Minahasa Utara", "Mojokerto", "Ngada",
            "Nganjuk", "Ngawi", "Ogan Komering Ilir", "Pacitan", "Padang", "Palangkaraya", "Palembang",
            "Palopo", "Palu", "Pamekasan", "Pandeglang", "Pangandaran", "Pangkajene Kepulauan", "Pare-Pare",
            "Pariaman", "Pasuruan", "Pekalongan", "Pekanbaru", "Pemalang", "Pematang Siantar", "Pinrang",
            "Polewali Mandar", "Ponorogo", "Poso", "Probolinggo", "Purbalingga", "Purworejo", "Rejang Lebong",
            "Salatiga", "Samarinda", "Sampang", "Sarolangun", "Semarang", "Serang", "Sibolga",
            "Sidenreng Rappang", "Sidoarjo", "Sijunjung", "Sikka", "Singkawang", "Sinjai", "Situbondo",
            "Sleman", "Solok", "Soppeng", "Sragen", "Subang", "Sukabumi", "Sukoharjo", "Sumba Barat",
            "Sumba Barat Daya", "Sumba Timur", "Sumbawa", "Sumedang", "Sumenep", "Surabaya", "Surakarta",
            "Takalar", "Tangerang", "Tangerang Selatan", "Tapanuli Utara", "Tasikmalaya", "Tegal",
            "Temanggung", "Timor Tengah Selatan", "Timor Tengah Utara", "Trenggalek", "Tuban", "Tulungagung",
            "Wajo", "Wonogiri", "Wonosobo", "Yogyakarta"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dateFormatter = new SimpleDateFormat("dd MMM yyyy", Locale.US);
        etSelDate = findViewById(R.id.etSelectDate);
        btnPassenger = findViewById(R.id.btn_passanger);
        btnSearchBus = findViewById(R.id.buttonSearchBus);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        String countSeat = getIntent().getStringExtra("SEAT_COUNT");
        btnPassenger.setText(countSeat);



        spinFrom = findViewById(R.id.Asal);
        spinTo = findViewById(R.id.Tujuan);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, Item);

        spinFrom.setAdapter(adapter);
        spinTo.setAdapter(adapter);
        spinFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView adapterView, View view, int i, long l) {
            }

            @Override
            public void onNothingSelected(AdapterView adapterView) {

            }
        });
    }

    public void SEARCH(View view) {
        String cityDeparture = spinFrom.getSelectedItem().toString().trim();
        String cityArrival = spinTo.getSelectedItem().toString().trim();
        String dateDeparture = etSelDate.getText().toString().trim();
        String seatCount = btnPassenger.getText().toString().trim();

        if (TextUtils.equals(cityDeparture,"")) {
            //email is empty
            Toast.makeText(this, "Please select departure place", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.equals(cityArrival,"")) {
            //password is empty
            Toast.makeText(this, "Please select destination place", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.equals(seatCount,"")) {
            //password is empty
            Toast.makeText(this, "Please select seat count", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(dateDeparture)) {
            //password is empty
            Toast.makeText(this, "Please select journey date", Toast.LENGTH_SHORT).show();
            return;
        }

        BookingDetail bookingDetail = new BookingDetail(cityDeparture, cityArrival, seatCount, dateDeparture);
        FirebaseUser user1 = mAuth.getCurrentUser();
        databaseReference.child("Users").child(user1.getUid()).child("BookingDetails").setValue(bookingDetail);

        Intent intent = new Intent(Home.this, scheedule_list.class);
        intent.putExtra("FROM_BUS",cityDeparture);
        intent.putExtra("TO_BUS",cityArrival);
        intent.putExtra("SEAT_COUNT", seatCount);
        intent.putExtra("DATE_BUS",dateDeparture);
        startActivity(intent);

    }

    public void PASSANGER(View view) {
        startActivity(new Intent(Home.this, passanger_picker.class));

    }

    public void SELECTDATE(View view) {
        Calendar newCalendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfyear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfyear, dayOfMonth);

                etSelDate.setText(dateFormatter.format(newDate.getTime()));
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }


    public void TICKETLIST1(View view) {
        startActivity(new Intent(Home.this, order.class));
    }

    public void TICKETLIST(View view) {
        startActivity(new Intent(Home.this, order.class));
    }

    public void ADVERTISEMENT(View view) {
        startActivity(new Intent(Home.this, Advertisement.class));
    }

    public void ACCOUNT(View view) {
        startActivity(new Intent(Home.this, Account.class));
    }

    public void ACCOUNT1(View view) {
        startActivity(new Intent(Home.this, Account.class));
    }

}