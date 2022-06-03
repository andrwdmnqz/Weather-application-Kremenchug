package com.example.weatherappforecast.Model;

import java.util.List;

public class WeatherForecastResult extends Object {
    public String cod;
    public int message;
    public int cnt;
    public List<MyList> list;
    public City city;
}
