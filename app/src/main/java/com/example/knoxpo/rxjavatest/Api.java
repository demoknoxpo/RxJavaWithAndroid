package com.example.knoxpo.rxjavatest;

import android.net.Uri;

/**
 * Created by knoxpo on 23/8/17.
 */

public class Api {

    private static final String
            BASE_URL = "http://api.openweathermap.org/data/2.5/forecast/",
            QUERY_PARAM = "q",
            FORMAT_PARAM = "mode",
            UNITS_PARAM = "units",
            DAYS_PARAM = "cnt",
            APPID_PARAM = "APPID";

    public static String getUri() {
        return Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(QUERY_PARAM, "Surat")
                .appendQueryParameter(FORMAT_PARAM, "JSON")
                .appendQueryParameter(UNITS_PARAM, "metric")
                .appendQueryParameter(DAYS_PARAM, "7")
                .build()
                .toString();
    }

}
