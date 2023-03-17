package com.pereyrarg11.composeanimation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun ColorAnimations() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(id = R.string.label_color_animation))
        BasicColorAnimation()
        Spacer(modifier = Modifier.size(32.dp))
        AdvancedColorAnimation()
    }
}

@Composable
fun BasicColorAnimation() {
    var isSecondaryColor by rememberSaveable {
        mutableStateOf(false)
    }
    val backgroundColor by animateColorAsState(
        targetValue = if (isSecondaryColor) MaterialTheme.colors.secondary else MaterialTheme.colors.primary
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(id = R.string.label_basic_animation))
        Spacer(modifier = Modifier.size(16.dp))
        Box(
            modifier = Modifier
                .size(150.dp)
                .background(backgroundColor)
        )
        Spacer(modifier = Modifier.size(16.dp))
        Button(onClick = { isSecondaryColor = !isSecondaryColor }) {
            Text(text = stringResource(id = R.string.action_change_color))
        }
    }
}

@Composable
fun AdvancedColorAnimation() {
    var isSecondaryColor by rememberSaveable { mutableStateOf(false) }
    var isButtonEnabled by rememberSaveable { mutableStateOf(true) }
    val backgroundColor by animateColorAsState(
        targetValue = if (isSecondaryColor) {
            MaterialTheme.colors.secondary
        } else {
            MaterialTheme.colors.primary
        },
        animationSpec = tween(durationMillis = 1000, delayMillis = 250),
        finishedListener = {
            isButtonEnabled = !isButtonEnabled
        })

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(id = R.string.label_advanced_animation))
        Spacer(modifier = Modifier.size(16.dp))
        Box(
            modifier = Modifier
                .size(150.dp)
                .background(backgroundColor)
        )
        Spacer(modifier = Modifier.size(16.dp))
        Button(
            enabled = isButtonEnabled,
            onClick = {
                isSecondaryColor = !isSecondaryColor
                isButtonEnabled = !isButtonEnabled
            }
        ) {
            Text(text = stringResource(id = R.string.action_change_color))
        }
    }
}