package com.example.knoxpo.rxjavatest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private RecyclerView mItemsRV;
    ItemAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mItemsRV = (RecyclerView)findViewById(R.id.rv_content);

        adapter = new ItemAdapter(MainActivity.this,null);
        mItemsRV.setAdapter(adapter);

        mItemsRV.setLayoutManager(new LinearLayoutManager(this));

        getWeatherData();

        callWeather();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getWeatherData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(retrofitUtils.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitUtils retrofitApi = retrofit.create(retrofitUtils.class);


        compositeDisposable.add(
                retrofitApi.listWeatherData()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(getWeatherObserver())
        );
    }

    private DisposableSingleObserver<Weather.City> getWeatherObserver() {

        return new DisposableSingleObserver<Weather.City>() {
            @Override
            public void onSuccess(@NonNull Weather.City weatherDatas) {
                if (weatherDatas != null) {
                    Log.d("Data", weatherDatas.getCityName() + " ");
                    //adapter.addItem(new ArrayList<Weather.City>(weatherDatas));
                   // adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("Error ", e.toString());
            }
        };
    }

    private void callWeather(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(retrofitUtils.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitUtils retrofitApi = retrofit.create(retrofitUtils.class);

        Call<Weather> res = retrofitApi.getWeather();

        res.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                Log.d("response " , response.body().toString());
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {

            }
        });
    }
}
