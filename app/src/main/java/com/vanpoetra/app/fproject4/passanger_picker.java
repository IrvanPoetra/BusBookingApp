package com.vanpoetra.app.fproject4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class passanger_picker extends AppCompatActivity
{
    SeekBar seekBar;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passanger_picker);

        seekBar = (SeekBar) findViewById(R.id.seekBarID);
        textView = (TextView)findViewById(R.id.textViewID);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b)
            {
                textView.setText("" + String.valueOf(progress));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void TAKESEAT(View view) {
        String countSeat = textView.getText().toString().trim();

        Intent intent = new Intent(passanger_picker.this, Home.class);
        intent.putExtra("SEAT_COUNT", countSeat);
        startActivity(intent);

    }

    public void CANCEL(View view) {
        startActivity(new Intent(passanger_picker.this, Home.class));
    }
}