package com.bt.laptrinhdidong;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

    WeatherCustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        lvWeather = (ListView) findViewById(R.id.lvWeather);
        addEvent();
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
              Intent intent = new Intent(WeatherActivity.this, WeatherActivity.class);
                intent.putExtra("Place", arrayListWeather.get(position).getPlace());
                intent.putExtra("Img", arrayListWeather.get(position).getImgWeather());
                intent.putExtra("Condition", arrayListWeather.get(position).getWeather());
                intent.putExtra("Wind", arrayListWeather.get(position).getWindSpeed());
                intent.putExtra("Temp", arrayListWeather.get(position).getTemp());
                startActivity(intent);
            }
        });
        lvWeather.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog alertDialog = createAlertDialog(position, customAdapter);
                alertDialog.show();
                return false;
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
            String condition = ob.getString("Condition");
            weather.setWeather(condition);
            String temp = ob.getString("Temperature");
            weather.setTemp(temp);
            String windSpeed = ob.getString("WindS");
            weather.setWindSpeed(windSpeed);
            arrayListWeather.add(weather);
        }

        return arrayListWeather;
    }

    AlertDialog createAlertDialog(int position, ArrayAdapter<String> adapter)
    {
        AlertDialog.Builder builder= new AlertDialog.Builder(WeatherActivity.this);
        builder.setTitle("Delete Item!");
        builder.setMessage("Are you sure you want to delete this item?");
        //Xử lý cho nút Yes
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        //Xử lý cho nút No
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        return builder.create();
    }
}