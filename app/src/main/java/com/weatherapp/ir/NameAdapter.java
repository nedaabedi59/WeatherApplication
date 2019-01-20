package com.weatherapp.ir;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.weatherapp.ir.model.Forecast;

import java.util.List;

public class NameAdapter extends RecyclerView.Adapter<NameAdapter.MyViewHolder> {
    List<Forecast> list;
    Context context;

    public NameAdapter(List<Forecast> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.exmple_list_item,viewGroup,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myviewHolder, int i) {
        Forecast forecast=list.get(i);
        myviewHolder.tvDay.setText(forecast.getDay());
        myviewHolder.tvHigh.setText("High: "+forecast.getHigh().toString()+" ˄   --");
        myviewHolder.tvLow.setText("Low: "+forecast.getLow().toString()+" ˅");
        myviewHolder.imgWeatherState.setImageDrawable(context.getResources()
                .getDrawable(WeatherPhoto.getPhotoWeather(forecast.getText())));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvDay;
        TextView tvHigh;
        TextView tvLow;
        ImageView imgWeatherState;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDay=itemView.findViewById(R.id.tvDayId);
            tvHigh=itemView.findViewById(R.id.tvHighId);
            tvLow=itemView.findViewById(R.id.tvLowId);
            imgWeatherState=itemView.findViewById(R.id.imgWeatherId);
        }
    }
}
