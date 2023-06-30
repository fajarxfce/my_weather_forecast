package com.a10120014.myweather;
/*
Nama    : Nurul Fajar
NIM     : 10120014
Kelas   : IF-1
Matkul  : Aplikasi Komputer Bergerak
*/

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WeatherDataService {

    public static final String QUERY_FOR_CITY_ID = "https://lookup.binlist.net/";
    private static final String TAG = "";
    Context context;
    String cityID;
    public WeatherDataService(Context context) {
        this.context = context;
    }



    public interface VolleyResponseListener{
        void onError(String message);
        void onResponse(String message);
    }

    public void getCityID(String cityName, VolleyResponseListener volleyResponseListener){
        String url = QUERY_FOR_CITY_ID + cityName;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                cityID = "";
                try {
                    JSONObject cityInfo = response.getJSONObject("number");
                    cityID = response.getString("scheme");
                    int length = cityInfo.getInt("length");

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                volleyResponseListener.onResponse(cityID);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponseListener.onError("Something Wrong");
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

    public interface ForeCastByIdResponse{
        void onError(String message);
        void onResponse(List<WeatherReportModel> weatherReportModels);
    }

    public void getCityForecastById(String cityId, ForeCastByIdResponse foreCastByIdResponse) {
        String url = "https://api.weatherbit.io/v2.0/forecast/daily?city=" + cityId + "&key=9d7a85598c7442a2aa5bd8cb376b0e1d";
        List<WeatherReportModel> weatherReportModels = new ArrayList<>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
//                Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
                try {

                    JSONArray consoledated = response.getJSONArray("data");

                    for (int i = 0; i < consoledated.length(); i++) {
                        WeatherReportModel one_day_weather = new WeatherReportModel();
                        JSONObject dataInfo = (JSONObject) consoledated.get(i);
                        String dateTime = dataInfo.getString("datetime");
                        String cityName = response.getString("city_name");
                        String countryCode = response.getString("country_code");
                        String clouds = dataInfo.getString("clouds");

                        JSONObject weatherInfo = dataInfo.getJSONObject("weather");
                        String weatherDesc = weatherInfo.getString("description");

                        one_day_weather.setDateTime(dateTime);
                        one_day_weather.setCityName(cityName);
                        one_day_weather.setCountryCode(countryCode);
                        one_day_weather.setWeatherDesc(weatherDesc);
                        one_day_weather.setClouds(Integer.parseInt(clouds));
                        Log.d(TAG, "onResponse: " + clouds);
                        weatherReportModels.add(one_day_weather);

                    }

                    foreCastByIdResponse.onResponse(weatherReportModels);

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }
}
