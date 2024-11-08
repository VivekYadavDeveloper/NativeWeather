package com.create.nativeweather.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    /*@GET annotation is used to specify the HTTP request method and URL for the API call.*/
    @GET("/v1/current.json")
    /*suspend function is used to execute the API call asynchronously.*/
    suspend fun getCurrentWeather(

        /*@Query annotation is used to specify the query parameters for the API call.*/
        @Query("key") apiKey: String,
        @Query("q") city: String,
        /*Response<WeatherModel> is used to specify the expected response type from the API call.*/
    ): Response<WeatherModel>

}