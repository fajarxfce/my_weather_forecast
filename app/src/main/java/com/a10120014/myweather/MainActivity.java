package com.a10120014.myweather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btn_cityID, btn_getWeatherByID, btn_getWeatherByName;
    EditText etDataInput, etDataOutput;
    ListView lv_weatherReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etDataInput = findViewById(R.id.data_input);

//        btn_cityID = findViewById(R.id.btn_get_city_id);
        btn_getWeatherByID = findViewById(R.id.btn_get_weather_by_id);
//        btn_getWeatherByName = findViewById(R.id.btn_get_weather_by_name);
        lv_weatherReport = findViewById(R.id.lv_weather_report);


        final WeatherDataService weatherDataService = new WeatherDataService(MainActivity.this);

//        btn_cityID.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                weatherDataService.getCityID(etDataInput.getText().toString(), new WeatherDataService.VolleyResponseListener() {
//                    @Override
//                    public void onError(String message) {
//                        Toast.makeText(MainActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onResponse(String cityID) {
//                        Toast.makeText(MainActivity.this, "Returned an ID of " + cityID, Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//            }
//        });

        btn_getWeatherByID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weatherDataService.getCityForecastById(etDataInput.getText().toString(), new WeatherDataService.ForeCastByIdResponse() {
                    @Override
                    public void onError(String message) {
                        etDataOutput.setText(message);
                    }

                    @Override
                    public void onResponse(List<WeatherReportModel> weatherReportModels) {
//                        etDataOutput.setText(weatherReportModels.toString());
                        //masukkan ke listview
                        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, weatherReportModels);
                        lv_weatherReport.setAdapter(arrayAdapter);
                    }
                });

            }
        });

//        btn_getWeatherByName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "You clicked me 3", Toast.LENGTH_SHORT).show();
//            }
//        });

    }


}