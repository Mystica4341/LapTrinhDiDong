package com.bt.laptrinhdidong;

public class Weather {
    String place, weather, temp, windSpeed;
    int imgWeather;

    public int getImgWeather() {
        return imgWeather;
    }

    public void setImgWeather(int imgWeather) {
        this.imgWeather = imgWeather;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Weather(String place, String weather, String temp, String windSpeed, int imgWeather) {
        this.place = place;
        this.weather = weather;
        this.temp = temp;
        this.windSpeed = windSpeed;
        this.imgWeather = imgWeather;
    }

    public Weather(){

    }
}
