package com.sample.weatherapp_retrofit.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    String key = "API_KEY";

    @GET("weather?appid="+key+"&units=metric")
    Call<Example> getWeatherData(@Query("q") String name);

}
