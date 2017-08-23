package com.example.knoxpo.rxjavatest;

import java.util.List;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by knoxpo on 23/8/17.
 */

public interface retrofitUtils {

    String BASE_URL = Api.getUri();


    @GET("daily?q=surat&mode=JSON&units=matrics&cnt=7&APPID=c1c67bcc13f19d7471eef2c9487b6993")
    Single<Weather.City> listWeatherData();

    @GET("daily?q=surat&mode=JSON&units=matrics&cnt=7&APPID=c1c67bcc13f19d7471eef2c9487b6993")
    Call<Weather> getWeather();
}
