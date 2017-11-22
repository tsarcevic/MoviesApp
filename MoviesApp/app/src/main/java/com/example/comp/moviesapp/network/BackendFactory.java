package com.example.comp.moviesapp.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by COMP on 16.11.2017..
 */

public class BackendFactory {

    private static String BASE_URL = "https://api.themoviedb.org/3";

    private static Retrofit retrofit;

    private static ApiService apiService;

    private static Retrofit getRetrofitInstance() {
        if (retrofit == null) {

            OkHttpClient httpClient = new OkHttpClient.Builder().build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

    public static ApiService getApiServiceInstance() {
        if (apiService == null) {
            apiService = getRetrofitInstance().create(ApiService.class);
        }

        return apiService;
    }

}
