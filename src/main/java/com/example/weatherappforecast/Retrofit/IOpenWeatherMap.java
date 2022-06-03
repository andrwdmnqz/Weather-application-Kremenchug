package com.example.weatherappforecast.Retrofit;
import io.reactivex.Observable;
import com.example.weatherappforecast.Model.WeatherForecastResult;
import com.example.weatherappforecast.Model.WeatherResult;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IOpenWeatherMap {
    @GET("weather")
    Observable<WeatherResult> getWeatherByLatLon(@Query("lat") String lat,
                                                 @Query("lon") String lon,
                                                 @Query("appid") String appid,
                                                 @Query("units") String units);

    @GET("forecast")
    Observable<WeatherForecastResult> getForecastWeatherByLatLon(@Query("lat") String lat,
                                                                 @Query("lon") String lon,
                                                                 @Query("appid") String appid,
                                                                 @Query("units") String units);
}
