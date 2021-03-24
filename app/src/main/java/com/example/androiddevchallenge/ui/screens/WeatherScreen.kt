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
package com.example.androiddevchallenge.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.Weather
import com.example.androiddevchallenge.data.WeatherForeCast
import com.example.androiddevchallenge.data.WeatherType
import com.example.androiddevchallenge.ui.component.RainDrop
import com.example.androiddevchallenge.ui.component.SnowFall
import com.example.androiddevchallenge.ui.component.StarEffect
import com.example.androiddevchallenge.ui.component.WeatherEffect
import com.example.androiddevchallenge.ui.component.WeatherInfo

@Composable
fun WeatherScreen(weather: Weather, time: String) {
    val selection = remember { mutableStateOf(0) }
    println("==SELECTION" + selection.value)

    val currentWeather: WeatherForeCast = weather.hourlyForeCast[selection.value]
    val backgroundImg = when (currentWeather.weatherType) {
        WeatherType.RAIN.name -> {
            if (!MaterialTheme.colors.isLight) painterResource(id = R.drawable.rain) else painterResource(
                id = R.drawable.rain
            )
        }
        WeatherType.SNOW.name -> {
            if (!MaterialTheme.colors.isLight) painterResource(id = R.drawable.snow) else painterResource(
                id = R.drawable.night
            )
        }
        WeatherType.CLOUDS.name -> {
            if (MaterialTheme.colors.isLight) painterResource(id = R.drawable.cloud_day) else painterResource(
                id = R.drawable.night_cloud
            )
        }
        WeatherType.CLEAR.name -> {
            if (MaterialTheme.colors.isLight) painterResource(id = R.drawable.night) else painterResource(
                id = R.drawable.night
            )
        }
        else -> {
            if (!MaterialTheme.colors.isLight) painterResource(id = R.drawable.night) else painterResource(
                id = R.drawable.sun
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)

    ) {
        Surface(
            modifier = Modifier.fillMaxHeight(.6f)

        ) {
            Box(
                modifier = Modifier.fillMaxSize()

            ) {
                BoxWithConstraints() {

                    Image(
                        modifier = Modifier
                            .fillMaxWidth(),
                        painter = backgroundImg,
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds
                    )

                    AppBar(time)
                    when (currentWeather.weatherType) {
                        WeatherType.RAIN.name -> {
                            WeatherEffect(
                                modifier = Modifier.rotate(20f),
                                width = maxWidth,
                                height = maxHeight,
                                drop = { RainDrop(modifier = Modifier.size(8.dp)) }
                            )
                        }
                        WeatherType.SNOW.name -> {
                            WeatherEffect(
                                modifier = Modifier.rotate(18f),
                                width = maxWidth,
                                height = maxHeight,
                                drop = { SnowFall(modifier = Modifier.size(8.dp)) }
                            )
                        }
                        WeatherType.CLOUDS.name -> {
                        }
                        WeatherType.CLEAR.name -> {

                            StarEffect(width = maxWidth, height = 200.dp)
                        }
                    }
                }
            }
        }

        WeatherInfo(weather, currentWeather, selection)
    }
}

@Composable
fun AppBar(time: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.semantics(mergeDescendants = true) {}
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = time,
                style = MaterialTheme.typography.body1,
                color = Color.White,

            )

            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                Icon(
                    modifier = Modifier.size(18.dp),
                    tint = Color.White,
                    painter = painterResource(id = R.drawable.ic_baseline_location_on_24),
                    contentDescription = null
                )
                Text(text = "Lucknow", style = MaterialTheme.typography.body2, color = Color.White)
            }
        }
    }
}
