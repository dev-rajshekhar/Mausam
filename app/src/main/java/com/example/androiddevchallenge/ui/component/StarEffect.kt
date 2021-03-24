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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R

@Composable
fun StarEffect(
    width: Dp,
    height: Dp,
    denseFactor: Int = 20,

) {
    val infiniteTransition = rememberInfiniteTransition()

    val sections = (width.value / denseFactor).toInt()
    val xOffset = remember {
        generateList(
            size = sections,
            from = 10,
            to = width.value.toInt()
        )
    }
    val duration = remember {
        generateRandomList(
            size = sections,
            from = 3000,
            to = 10000
        )
    }
    val yOffset = remember {
        generateList(
            size = sections,
            from = 10,
            to = height.value.toInt()
        )
    }
    Row() {
        for (i in 0 until sections) {
            val opacity by infiniteTransition.animateFloat(
                initialValue = 1f,
                targetValue = .1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = duration[i], easing = LinearEasing)
                )
            )
            Box(
                modifier = Modifier
                    .offset(x = xOffset[i].dp, y = yOffset[i].dp)

            ) {
                Star(alpha = opacity)
            }
        }
    }
}

@Composable
fun Star(
    modifier: Modifier = Modifier,
    alpha: Float,

) {
    Icon(
        painter = painterResource(id = R.drawable.ic_star),
        contentDescription = null,
        modifier = modifier.size(20.dp),
        tint = Color(0xFFFFFFFF).copy(alpha = alpha)
    )
}

fun generateList(size: Int, from: Int, to: Int): List<Int> {
    val list = ArrayList<Int>(size)
    for (i in 0 until size) {
        list.add(random(from = from, to = to))
    }
    return list
}
