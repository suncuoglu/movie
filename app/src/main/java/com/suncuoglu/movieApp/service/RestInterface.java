package com.suncuoglu.movieApp.service;

import com.suncuoglu.movieApp.model.MovieModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestInterface {

    @GET(".")
    Call<MovieModel> listMovie(@Query("apikey")String apikey,
                               @Query("s")String title);
}
