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
package com.example.androiddevchallenge.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.FakeHourlyForecast
import com.example.androiddevchallenge.data.Weather
import com.example.androiddevchallenge.data.WeatherForeCast

@Composable
fun WeatherInfo(weather: Weather, currentWeather: WeatherForeCast, selection: MutableState<Int>) {
    println("==SELECTION" + selection.value)

    Column(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Temperature(
                text = currentWeather.temperature,
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.onBackground, painter = {}
            )
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .height(40.dp)
            ) {
                Temperature(
                    text = weather.minTemp,
                    isDrawable = true,
                    painter = {
                        Icon(
                            modifier = Modifier.size(18.dp),
                            painter = painterResource(id = R.drawable.ic_down),
                            contentDescription = null
                        )
                    },
                    style = MaterialTheme.typography.h2,
                    color = MaterialTheme.colors.onBackground
                )
                Temperature(
                    isDrawable = true,

                    text = weather.maxTemp,
                    style = MaterialTheme.typography.h2,
                    color = MaterialTheme.colors.onBackground,
                    painter = {
                        Icon(
                            modifier = Modifier.size(18.dp),
                            painter = painterResource(id = R.drawable.ic_up),
                            contentDescription = null
                        )
                    },

                )
            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp)
                .height(2.dp)
                .background(MaterialTheme.colors.primary)
        )
        Text(
            text = currentWeather.weatherType,
            style = MaterialTheme.typography.h3,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.padding(5.dp)
        )
        LazyRow(
            content = {
                items(FakeHourlyForecast.size) { index ->
                    WeatherForeCastRow(FakeHourlyForecast[index], index, selection)
                }
            }
        )
    }
}
