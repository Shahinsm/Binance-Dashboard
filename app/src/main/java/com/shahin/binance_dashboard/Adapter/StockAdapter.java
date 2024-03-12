package com.shahin.binance_dashboard.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.majorik.sparklinelibrary.SparkLineLayout;
import com.shahin.binance_dashboard.Domain.domain;
import com.shahin.binance_dashboard.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class StockAdapter extends RecyclerView.Adapter<StockAdapter.Viewholer> {
    ArrayList<domain> dataList;
    DecimalFormat format;

    public StockAdapter(ArrayList<domain> dataList) {
        this.dataList = dataList;
        format = new DecimalFormat("###,###,###,###,##");
    }

    @NonNull
    @Override
    public StockAdapter.Viewholer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_stock,parent,false);
        return new Viewholer(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull StockAdapter.Viewholer holder, int position) {

        holder.nameTxt.setText(dataList.get(position).getName());
        holder.priceTxt.setText("$"+format.format(dataList.get(position).getPrice()));
        holder.changePercentTxt.setText(dataList.get(position).getChangePercent()+"%");
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



        if (position==0){

            holder.mainLayout.setBackgroundResource(R.drawable.purple_bg);
            holder.nameTxt.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.white));
            holder.priceTxt.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.white));
            holder.changePercentTxt.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.white));

        }

        String picName="";
        switch (dataList.get(position).getName()){
            case "NASDAQ100":{
                picName = "stock_1";
                break;
            }

            case "S&P 500":{
                picName = "stock_2";
                break;
            }

            case "Dow Jones":{
                picName = "stock_1";
                break;
            }

        }

        int drawableResourceId = holder.itemView.getContext().getResources()
                .getIdentifier(picName,"drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.logo);


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class Viewholer extends RecyclerView.ViewHolder{
        TextView nameTxt,priceTxt,changePercentTxt;
        ImageView logo;
        SparkLineLayout lineChat;
        ConstraintLayout mainLayout;
        public Viewholer(@NonNull View itemView) {
            super(itemView);

            nameTxt = itemView.findViewById(R.id.cryptoTitle);
            priceTxt = itemView.findViewById(R.id.cryptoPrice);
            changePercentTxt = itemView.findViewById(R.id.changeParcent);
            logo = itemView.findViewById(R.id.logoImg);
            lineChat = itemView.findViewById(R.id.cryptoChart);
            mainLayout = itemView.findViewById(R.id.mainLayout);

        }
    }
}
