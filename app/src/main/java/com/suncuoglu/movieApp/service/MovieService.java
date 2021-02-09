package com.suncuoglu.movieApp.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieService {

    public static String BASE_URL = "http://www.omdbapi.com/";

    public static Retrofit retrofit =
            new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build();

}