package com.fuxia.w.view9;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


/**
 * Created by Bob on 2016/12/30.
 */

public interface WeatherServer {

    @GET("weather_mini")
    Observable<Weatherbean> getWeatherDate(@Query("city") String city);
}
