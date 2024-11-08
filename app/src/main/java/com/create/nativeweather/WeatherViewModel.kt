package com.create.nativeweather

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.create.nativeweather.api.Constant
import com.create.nativeweather.api.NetworkResponse
import com.create.nativeweather.api.RetrofitInstance
import com.create.nativeweather.api.WeatherModel
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    /*Create a Retrofit Instance Here For API Calls*/
    private val weatherApi = RetrofitInstance.weatherApi
    /*MutableLiveData to hold the result of the API call*/
    private val _weatherResult = MutableLiveData<NetworkResponse<WeatherModel>>()
    /*LiveData to expose the result of the API call*/
    val weatherResult: LiveData<NetworkResponse<WeatherModel>> = _weatherResult

    /*Function to fetch data from the API*/
    fun getData(city: String) {
        /*Set the result to loading before making the API call*/
        _weatherResult.value = NetworkResponse.Loading

        Log.i("WeatherViewModel", "getData called with city: $city")
        /*Make the API call using a coroutine*/
        /*Coroutine use for asynchronous tasks*/
        viewModelScope.launch {
            try {
                val response = weatherApi.getCurrentWeather(Constant.apiKey, city)
                if (response.isSuccessful) {
                    Log.i("Response1", "Response: ${response.body().toString()}")
                    response.body()?.let {
                        _weatherResult.value = NetworkResponse.Success(it)
                    }

                } else {
                    _weatherResult.value = NetworkResponse.Error("Error: ${response.message()}")
                    Log.e("Error Response", "Error: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("Error Response", "Error $e")
            }


        }


    }

}