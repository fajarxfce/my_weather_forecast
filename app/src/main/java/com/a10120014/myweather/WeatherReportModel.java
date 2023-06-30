package com.a10120014.myweather;
/*
Nama    : Nurul Fajar
NIM     : 10120014
Kelas   : IF-1
Matkul  : Aplikasi Komputer Bergerak
*/

public class WeatherReportModel {

    private String cityName;
    private String countryCode;
    private String dateTime;
    private String weatherDesc;
    private int clouds;


    public WeatherReportModel(String cityName, String countryCode, String validDate, int clouds) {
        this.cityName = cityName;
        this.countryCode = countryCode;
        this.dateTime = dateTime;
        this.weatherDesc = weatherDesc;
        this.clouds = clouds;
    }



    public WeatherReportModel() {

    }

    @Override
    public String toString() {
        return "City Name          : " + cityName + "\n" +
                "Country Code    : " + countryCode + "\n" +
                "Country Code    : " + dateTime + "\n" +
                "Clouds                : " + clouds + "\n" +
                "Description        : " + weatherDesc;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getWeatherDesc() {
        return weatherDesc;
    }

    public void setWeatherDesc(String weatherDesc) {
        this.weatherDesc = weatherDesc;
    }

    public int getClouds() {
        return clouds;
    }

    public void setClouds(int clouds) {
        this.clouds = clouds;
    }
}
