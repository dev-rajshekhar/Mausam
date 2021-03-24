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
package com.example.androiddevchallenge.data

data class Weather(
    val location: String,
    val humidity: String,
    val minTemp: String,
    val maxTemp: String,
    val weatherType: String,
    val windSpeed: String,
    val airQuality: String,
    val temperature: String,
    val sunRise: String,
    val sunSet: String,
    val hourlyForeCast: List<WeatherForeCast> = emptyList()
)

data class WeatherForeCast(
    val temperature: String,
    val weatherType: String,
    val time: String
)

enum class WeatherType {
    CLEAR,
    CLOUDS,
    RAIN,
    SNOW,
}

val FakeHourlyForecast = listOf<WeatherForeCast>(
    WeatherForeCast(
        weatherType = WeatherType.CLEAR.name,
        temperature = "16",
        time = "06:00 am",
    ),
    WeatherForeCast(

        weatherType = WeatherType.CLEAR.name,
        temperature = "14",
        time = "07:00 am",
    ),
    WeatherForeCast(

        weatherType = WeatherType.CLOUDS.name,
        temperature = "12",
        time = "08:00 am",
    ),

    WeatherForeCast(

        weatherType = WeatherType.RAIN.name,
        temperature = "13",
        time = "09:00 am",
    ),

    WeatherForeCast(

        weatherType = WeatherType.CLOUDS.name,
        temperature = "18",
        time = "11:00 am",
    ),

    WeatherForeCast(

        weatherType = WeatherType.SNOW.name,
        temperature = "12",
        time = "12:00 am",
    ),
    WeatherForeCast(

        weatherType = WeatherType.SNOW.name,
        temperature = "18",
        time = "01:00 pm",
    ),
    WeatherForeCast(

        weatherType = WeatherType.CLOUDS.name,
        temperature = "22",
        time = "02:00 pm",
    ),
    WeatherForeCast(

        weatherType = WeatherType.CLEAR.name,
        temperature = "21",
        time = "3:00 pm",
    ),
    WeatherForeCast(

        weatherType = WeatherType.CLOUDS.name,
        temperature = "18",
        time = "4:00 pm",
    ),
    WeatherForeCast(

        weatherType = WeatherType.CLOUDS.name,
        temperature = "5",
        time = "5:00 pm",
    ),
    WeatherForeCast(

        weatherType = WeatherType.RAIN.name,
        temperature = "5",
        time = "6:00 pm",
    ),
    WeatherForeCast(
        weatherType = WeatherType.SNOW.name,
        temperature = "25",
        time = "7:00 pm",
    ),
    WeatherForeCast(

        weatherType = WeatherType.RAIN.name,
        temperature = "19",
        time = "8:00 pm",
    ),
    WeatherForeCast(
        weatherType = WeatherType.CLOUDS.name,
        temperature = "12",
        time = "8:00 pm",
    ),
    WeatherForeCast(
        weatherType = WeatherType.CLEAR.name,
        temperature = "12",
        time = "9:00 pm",
    ),
)

val FakeWeatherData = Weather(
    location = "Lucknow",
    humidity = "",
    maxTemp = "39",
    minTemp = "24",
    weatherType = WeatherType.CLEAR.name,
    windSpeed = "20",
    airQuality = "12",
    sunRise = "5:06 am",
    sunSet = "6:12 pm",
    temperature = "36",
    hourlyForeCast = FakeHourlyForecast

)
