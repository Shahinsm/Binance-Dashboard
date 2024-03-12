package com.shahin.binance_dashboard.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.majorik.sparklinelibrary.SparkLineLayout;
import com.shahin.binance_dashboard.Domain.domain;
import com.shahin.binance_dashboard.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CryptoAdapter extends RecyclerView.Adapter<CryptoAdapter.Viewholer> {
    ArrayList<domain> dataList;
    DecimalFormat format;

    public CryptoAdapter(ArrayList<domain> dataList) {
        this.dataList = dataList;
        format = new DecimalFormat("###,###,###,###,##");
    }

    @NonNull
    @Override
    public CryptoAdapter.Viewholer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_crypto,parent,false);
        return new Viewholer(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CryptoAdapter.Viewholer holder, int position) {

        holder.nameTxt.setText(dataList.get(position).getName());
        holder.priceTxt.setText("$"+format.format(dataList.get(position).getPrice()));
        holder.changePercentTxt.setText(dataList.get(position).getChangePercent()+"%");
        holder.propertySizeTxt.setText(dataList.get(position).getPropertySize()+dataList.get(position).getSymbol());
        holder.propertyAmountTxt.setText("$"+format.format(dataList.get(position).getPropertyAmount()));
        holder.lineChat.setData(dataList.get(position).getLineData());


        if (dataList.get(position).getChangePercent()>0){

            holder.changePercentTxt.setTextColor(Color.parseColor("#12c737"));
            holder.lineChat.setSparkLineColor(Color.parseColor("#12c737"));

        }else if (dataList.get(position).getChangePercent()<0){

            holder.changePercentTxt.setTextColor(Color.parseColor("#Fc0000"));
            holder.lineChat.setSparkLineColor(Color.parseColor("#Fc0000"));

        }else {

            holder.changePercentTxt.setTextColor(Color.parseColor("#ffffff"));
            holder.lineChat.setSparkLineColor(Color.parseColor("#ffffff"));

        }

        int drawableResourceId = holder.itemView.getContext().getResources()
                .getIdentifier(dataList.get(position).getName(),"drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.logo);


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class Viewholer extends RecyclerView.ViewHolder{
        TextView nameTxt,priceTxt,changePercentTxt,propertySizeTxt,propertyAmountTxt;
        ImageView logo;
        SparkLineLayout lineChat;
        public Viewholer(@NonNull View itemView) {
            super(itemView);

            nameTxt = itemView.findViewById(R.id.cryptoTitle);
            priceTxt = itemView.findViewById(R.id.cryptoPrice);
            changePercentTxt = itemView.findViewById(R.id.changeParcent);
            propertySizeTxt = itemView.findViewById(R.id.propertySize);
            propertyAmountTxt = itemView.findViewById(R.id.propertyAmount);
            logo = itemView.findViewById(R.id.logoImg);
            lineChat = itemView.findViewById(R.id.cryptoChart);

        }
    }
}
