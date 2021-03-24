/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.viewmodel

import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.data.FakeWeatherData
import com.example.androiddevchallenge.data.Weather
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

class WeatherVm() : ViewModel() {

    private val _currentTime = MutableLiveData("")
    val time: LiveData<String> = _currentTime

    private val _weather = MutableLiveData(FakeWeatherData)
    val weather: LiveData<Weather> = _weather

    init {
        getCurrentTime()
    }

    fun getCurrentTime() {
        viewModelScope.launch {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val current = LocalDateTime.now()
                val formatter = DateTimeFormatter.ofPattern(" EEE, dd MMM  HH:mm a")
                val currentTime: String = current.format(formatter)
                _currentTime.value = currentTime
            } else {
                val date = Date()
                val formatter = SimpleDateFormat("EEE, dd MMM  HH:mm a", Locale.US)
                val currentTime: String = formatter.format(date)
                _currentTime.value = currentTime
            }
        }
    }
}
