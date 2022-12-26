package com.vanpoetra.app.fproject4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ArtistViewHolder> {

    Context mCtx;
    List<Ticket> ticketList;
    ItemClickListener clickListener;

    public OrderAdapter(Context mCtx, List<Ticket> ticketList) {
        this.mCtx = mCtx;
        this.ticketList = ticketList;
    }

    @NonNull
    @Override
    public ArtistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recycler_ticket, parent, false);
        return new ArtistViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ArtistViewHolder holder, int position) {
        Ticket ticket = ticketList.get(position);

        holder.tvPOBus.setText(ticket.poName);
        holder.tvBusNo.setText(ticket.busNo);
        holder.tvDateDeparture.setText(ticket.dateDeparture);
        holder.tvCityDeparture.setText(ticket.cityDeparture);
        holder.tvTimeDeparture.setText(ticket.timeDeparture);
        holder.tvTerminalDeparture.setText(ticket.terminalDeparture);
        holder.tvOrderCode.setText("Book Code: "+ticket.orderID);

    }


    @Override
    public int getItemCount() {
        return ticketList.size();
    }
    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    class ArtistViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvPOBus,tvBusNo,tvDateDeparture,tvCityDeparture,tvTimeDeparture,tvTerminalDeparture,tvOrderCode;

        public ArtistViewHolder(@NonNull View itemView) {
            super(itemView);

            tvPOBus = itemView.findViewById(R.id.tv_POBusOrder);
            tvBusNo = itemView.findViewById(R.id.tv_BusNoOrder);
            tvDateDeparture = itemView.findViewById(R.id.tv_DateDepartureOrder);
            tvCityDeparture = itemView.findViewById(R.id.tv_CityDepartureOrder);
            tvTimeDeparture = itemView.findViewById(R.id.tv_TimeDepartureOrder);
            tvTerminalDeparture = itemView.findViewById(R.id.tv_TerminalDepartureOrder);
            tvOrderCode = itemView.findViewById(R.id.tv_OrderCode);

            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }

}

