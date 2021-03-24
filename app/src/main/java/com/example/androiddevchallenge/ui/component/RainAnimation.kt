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
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import kotlin.random.Random

@Composable
fun WeatherEffect(
    width: Dp,
    height: Dp,
    modifier: Modifier = Modifier,
    drop: @Composable () -> Unit,
    denseFactor: Int = 5,
    isVertical: Boolean = true,
    dropDimension: DropDimension = DropDefaults.dimensions(),
    dropSpeed: DropSpeed = DropDefaults.movementSpeed(),
) {
    val infiniteTransition = rememberInfiniteTransition()

    val sections = (width.value / denseFactor).toInt()

    val duration = remember {
        generateRandomList(
            size = sections,
            from = dropSpeed.minSpeedMillis,
            to = dropSpeed.maxSpeedMillis
        )
    }

    val dropWidths = remember {
        generateRandomList(
            size = sections,
            from = dropDimension.dropMinWidth.value.toInt(),
            to = dropDimension.dropMaxWidth.value.toInt()
        )
    }

    val dropHeights = remember {
        generateRandomList(
            size = sections,
            from = dropDimension.dropMinHeight.value.toInt(),
            to = dropDimension.dropMaxHeight.value.toInt()
        )
    }

    if (isVertical) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(height = height),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            for (i in 0 until sections) {

                val position by infiniteTransition.animateFloat(
                    initialValue = 1f,
                    targetValue = 0f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(durationMillis = duration[i], easing = LinearEasing)
                    )
                )

                Box(
                    modifier = Modifier
                        .offset(y = height - (height * position))
                        .width(dropWidths[i].dp)
                        .height(dropHeights[i].dp)
                ) {
                    drop()
                }
            }
        }
    } else {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .height(height = height),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            for (i in 0 until sections) {

                val position by infiniteTransition.animateFloat(
                    initialValue = 1f,
                    targetValue = 0f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(durationMillis = duration[i], easing = LinearEasing)
                    )
                )

                Box(
                    modifier = Modifier
                        .offset(x = width - (width * position))
                        .width(dropWidths[i].dp)
                        .height(dropHeights[i].dp)
                ) {
                    drop()
                }
            }
        }
    }
}

@Composable
fun RainDrop(
    modifier: Modifier = Modifier
) {
    Icon(
        painter = painterResource(id = R.drawable.ic_drop),
        contentDescription = null,
        modifier = modifier,
        tint = Color(0xFFE8FAFF)
    )
}

@Composable
fun SnowFall(
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current
) {
    Icon(
        painter = painterResource(id = R.drawable.ic_snow),
        contentDescription = null,
        modifier = modifier.size(16.dp),
        tint = Color(0xFFE8FAFF)
    )
}

object DropDefaults {
    private val dropMinWidth = 8.dp
    private val dropMaxWidth = 20.dp
    private val dropMinHeight = 8.dp
    private val dropMaxHeight = 20.dp

    fun dimensions(): DropDimension = DropDimension(
        dropMinWidth = dropMinWidth,
        dropMinHeight = dropMinHeight,
        dropMaxWidth = dropMaxWidth,
        dropMaxHeight = dropMaxHeight
    )

    fun movementSpeed(): DropSpeed = DropSpeed(
        minSpeedMillis = 1500,
        maxSpeedMillis = 2500
    )
}

data class DropDimension(
    val dropMinWidth: Dp,
    val dropMaxWidth: Dp,
    val dropMinHeight: Dp,
    val dropMaxHeight: Dp,
    val width: Dp = dropMinWidth,
    val height: Dp = dropMinHeight,
)

data class DropSpeed(
    val minSpeedMillis: Int,
    val maxSpeedMillis: Int
)

fun generateRandomList(size: Int, from: Int, to: Int): List<Int> {
    val list = ArrayList<Int>(size)
    for (i in 0 until size) {
        list.add(random(from = from, to = to))
    }
    return list
}

fun random(from: Int, to: Int): Int {
    return Random.nextInt(to - from) + from
}
