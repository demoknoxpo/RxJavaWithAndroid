package com.example.knoxpo.rxjavatest;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by knoxpo on 23/8/17.
 */

public class Weather {

    @SerializedName("city")
    private City mData;

    public class City {
        public String getId() {
            return mId;
        }

        public String getCityName() {
            return mCityName;
        }

        public String getCountry() {
            return mCountry;
        }

        public String getPopulation() {
            return mPopulation;
        }

        @SerializedName("id")
        private String mId;

        @SerializedName("name")
        private String mCityName;

        @SerializedName("coord")
        private Coordinate mCoord;

        @SerializedName("country")
        private String mCountry;

        @SerializedName("population")
        private String mPopulation;

        @SerializedName("list")
        private List<WeatherData> list = new ArrayList<>();

        class Coordinate{
            @SerializedName("lat")
            private double mLat;
            @SerializedName("lon")
            private double mLng;
        }

        class WeatherData{
            @SerializedName("dt")
            private long mDate;

            @SerializedName("temp")
            private Temp temp;

            class Temp{
                @SerializedName("min")
                private float mMin;

                @SerializedName("max")
                private float mMax;
            }

        }

    }



    public String toString(){
        return mData.getCityName() + " " + mData.getCountry();
    }
}
