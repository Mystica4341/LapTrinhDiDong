package com.bt.laptrinhdidong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class WeatherDetailActivity extends AppCompatActivity {

    ImageView imgWeather;
    TextView tvPlace, tvTemp, tvWindS, tvCondition;

    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_detail);
        addControl();
        addEvent();
    }

    private void addControl(){
        imgWeather = (ImageView) findViewById(R.id.imgWeatherDetail);
        tvPlace = (TextView) findViewById(R.id.tvPlaceDetail);
        tvTemp = (TextView) findViewById(R.id.tvTemp);
        tvWindS = (TextView) findViewById(R.id.tvWindS);
        tvCondition = (TextView) findViewById(R.id.tvCondition);
        btnBack = (Button) findViewById(R.id.btnBack);
    }

    private void addEvent(){
        Intent intent = new Intent();
        String Place = intent.getStringExtra("Place");
        String WindSpeed = intent.getStringExtra("Wind");
        String Condition = intent.getStringExtra("Condition");
        String Temp = intent.getStringExtra("Temp");
        int Img = intent.getIntExtra("Img", 0);
        imgWeather.setImageResource(Img);
        tvPlace.setText(Place);
        tvWindS.setText(WindSpeed);
        tvCondition.setText(Condition);
        tvTemp.setText(Temp);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(WeatherDetailActivity.this, WeatherActivity.class);
                startActivity(intent2);
            }
        });
    }
}