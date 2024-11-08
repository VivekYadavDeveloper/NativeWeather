package com.create.nativeweather.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val baseUrl = "https://api.weatherapi.com"


    private fun getInstance(): Retrofit {
        /*Retrofit.Builder() is used to create a new instance of the Retrofit class.*/
        /*baseUrl(baseUrl) is used to set the base URL for the API.*/
        /*addConverterFactory(GsonConverterFactory.create()) is used to set the converter factory for the Retrofit instance.*/
        /*build() is used to create the Retrofit instance.*/
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    /*Create an instance of the WeatherApi interface using the getInstance() method.*/

    val weatherApi: WeatherApi = getInstance().create(WeatherApi::class.java)

}