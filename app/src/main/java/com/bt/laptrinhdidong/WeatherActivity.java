package com.bt.laptrinhdidong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class WeatherActivity extends AppCompatActivity {

    ListView lvWeather;

    ArrayList<Weather> arrayListWeather = new ArrayList<>();

    WeatherCustomAdapter weatherCustomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        lvWeather = (ListView) findViewById(R.id.lvWeather);
    }

    private void addEvent(){
        try {
            arrayListWeather = readFileJson();
            WeatherCustomAdapter adapter =
                    new WeatherCustomAdapter(WeatherActivity.this,
                            R.layout.item_weather_layout, arrayListWeather);
            lvWeather.setAdapter(adapter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        lvWeather.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(WeatherActivity.this,)
            }
        });
    }

    public ArrayList<Weather> readFileJson() throws IOException, JSONException {
        ArrayList<Weather> arrayListWeather = new ArrayList<>();
        InputStream inputStream = getResources().getAssets().open("Weather.json");
        int size = inputStream.available();
        byte[] data = new byte[size];
        inputStream.read(data);
        inputStream.close();
        String result = new String(data, "UTF-8");
        JSONObject jsonObject = new JSONObject(result);
        JSONArray jsonArray = jsonObject.getJSONArray("Weather");
        for(int i = 0; i < jsonArray.length(); i++){
            Weather weather = new Weather();
            JSONObject ob = jsonArray.getJSONObject(i);
            int image = getResources()
                    .getIdentifier(ob.getString("Img"), "drawable", "com.bt.laptrinhdidong");
            weather.setImgWeather(image);
            String place = ob.getString("Place");
            weather.setPlace(place);
            String weathers = ob.getString("Weather");
            weather.setWeather(weathers);
            String temp = ob.getString("Temperature");
            weather.setTemp(temp);
            String windSpeed = ob.getString("WindS");
            weather.setWindSpeed(windSpeed);
            arrayListWeather.add(weather);
        }

        return arrayListWeather;
    }
}