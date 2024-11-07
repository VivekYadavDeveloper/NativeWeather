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

    private val weatherApi = RetrofitInstance.weatherApi
    private val _weatherResult = MutableLiveData<NetworkResponse<WeatherModel>>()
    val weatherResult: LiveData<NetworkResponse<WeatherModel>> = _weatherResult

    fun getData(city: String) {
        _weatherResult.value = NetworkResponse.Loading

        Log.i("WeatherViewModel", "getData called with city: $city")
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