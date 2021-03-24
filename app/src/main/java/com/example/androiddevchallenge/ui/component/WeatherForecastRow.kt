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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.androiddevchallenge.data.WeatherForeCast
import com.example.androiddevchallenge.data.WeatherType

@Composable
fun WeatherForeCastRow(weatherForeCast: WeatherForeCast, index: Int, selection: MutableState<Int>,) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .height(80.dp)
            .padding(5.dp)
            .background(color = MaterialTheme.colors.surface, shape = RoundedCornerShape(4.dp)).clickable { selection.value = index }
    ) {
        Temperature(
            text = weatherForeCast.temperature,
            style = MaterialTheme.typography.h2,
            color = MaterialTheme.colors.onBackground, painter = {}
        )

        when (weatherForeCast.weatherType) {
            WeatherType.CLEAR.name -> {
                Icon(
                    tint = MaterialTheme.colors.onSurface,
                    modifier = Modifier.size(18.dp),
                    painter = painterResource(id = R.drawable.ic_sun),
                    contentDescription = null
                )
            }
            WeatherType.SNOW.name -> {
                Icon(
                    tint = MaterialTheme.colors.onSurface,
                    modifier = Modifier.size(18.dp),
                    painter = painterResource(id = R.drawable.ic_snow),
                    contentDescription = null
                )
            }
            WeatherType.CLOUDS.name -> {
                Icon(
                    tint = MaterialTheme.colors.onSurface,
                    modifier = Modifier.size(18.dp),
                    painter = painterResource(id = R.drawable.ic_baseline_cloud_queue_24),
                    contentDescription = null
                )
            }
            WeatherType.RAIN.name -> {
                Icon(
                    tint = MaterialTheme.colors.onSurface,
                    modifier = Modifier.size(18.dp),
                    painter = painterResource(id = R.drawable.ic_water),
                    contentDescription = null
                )
            }
        }
        Text(
            text = weatherForeCast.time,
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.onSurface,
            modifier = Modifier.padding(5.dp)
        )
    }
}
