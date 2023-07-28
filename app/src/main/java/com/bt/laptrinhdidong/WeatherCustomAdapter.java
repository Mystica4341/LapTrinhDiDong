package com.bt.laptrinhdidong;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class WeatherCustomAdapter extends ArrayAdapter {

    Context context;

    int layoutItem;

    ArrayList<Weather> arrayListWeather;

    public WeatherCustomAdapter( Context context, int layoutItem, ArrayList<Weather> arrayListWeather) {
        super(context, layoutItem, arrayListWeather);
        this.context = context;
        this.layoutItem = layoutItem;
        this.arrayListWeather = arrayListWeather;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Weather thoitiet = arrayListWeather.get(position);
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(layoutItem,null);
        }
        ImageView imgWeather = (ImageView)  convertView.findViewById(R.id.imgWeather);
        imgWeather.setImageResource(thoitiet.getImgWeather());

        TextView tvPlace = (TextView) convertView.findViewById(R.id.tvPlace);
        tvPlace.setText(thoitiet.getPlace());

        return convertView;
    }
}
