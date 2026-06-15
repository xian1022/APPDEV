package com.example.fishingharboralert.api;

import com.example.fishingharboralert.model.WeatherInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApiService {
    @GET("weather")
    Call<WeatherInfo> getWeather(@Query("harbor") String harborName, @Query("apiKey") String apiKey);
}
