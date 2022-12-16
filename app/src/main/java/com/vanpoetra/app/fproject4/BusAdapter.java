package com.vanpoetra.app.fproject4;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseUser;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class BusAdapter extends RecyclerView.Adapter<BusAdapter.ArtistViewHolder> {

    Context mCtx;
    List<Bus> busList;
    ItemClickListener clickListener;

    public BusAdapter(Context mCtx, List<Bus> busList) {
        this.mCtx = mCtx;
        this.busList = busList;
    }

    @NonNull
    @Override
    public ArtistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recycler_bus, parent, false);
        return new ArtistViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ArtistViewHolder holder, int position) {
        Bus bus = busList.get(position);
        String prices = formatRupiah(Double.valueOf(bus.price));

        holder.tvPOBus.setText(bus.poName);
        holder.tvBusNo.setText(bus.busNo);
        holder.tvDateDeparture.setText(bus.dateDeparture);
        holder.tvDateArrival.setText(bus.dateArrival);
        holder.tvCityDeparture.setText(bus.cityDeparture);
        holder.tvCityArrival.setText(bus.cityArrival);
        holder.tvTimeDeparture.setText(bus.timeDeparture);
        holder.tvTimeArrival.setText(bus.timeArrival);
        holder.tvTerminalDeparture.setText(bus.terminalDeparture);
        holder.tvTerminalArrival.setText(bus.terminalArrival);
        holder.tvPrice.setText(prices);
        holder.tvRating.setText(bus.rating+"/5");

    }

    private String formatRupiah(Double number){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(number);
    }

    @Override
    public int getItemCount() {
        return busList.size();
    }
    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    class ArtistViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvPOBus, tvBusNo,tvDateDeparture,tvDateArrival,tvCityDeparture,tvCityArrival,tvTimeDeparture,tvTimeArrival,tvTerminalDeparture,tvTerminalArrival,tvPrice, tvRating;

        public ArtistViewHolder(@NonNull View itemView) {
            super(itemView);

            tvPOBus = itemView.findViewById(R.id.POBus);
            tvBusNo = itemView.findViewById(R.id.BusNo);
            tvDateDeparture = itemView.findViewById(R.id.date_departure);
            tvDateArrival = itemView.findViewById(R.id.date_arrival);
            tvCityDeparture = itemView.findViewById(R.id.city_departure);
            tvCityArrival = itemView.findViewById(R.id.city_arrival);
            tvTimeDeparture = itemView.findViewById(R.id.time_departure);
            tvTimeArrival = itemView.findViewById(R.id.time_arrival);
            tvTerminalDeparture = itemView.findViewById(R.id.terminal_departure);
            tvTerminalArrival = itemView.findViewById(R.id.terminal_arrival);
            tvPrice = itemView.findViewById(R.id.price);
            tvRating = itemView.findViewById(R.id.rating);

            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }

}

