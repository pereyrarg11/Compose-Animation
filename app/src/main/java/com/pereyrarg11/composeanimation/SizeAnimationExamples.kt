package com.pereyrarg11.composeanimation

import androidx.compose.animation.core.animateDpAsState
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

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SizeAnimations() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(id = R.string.label_size_animation))
        BasicSizeAnimation()
        Spacer(modifier = Modifier.size(32.dp))
        AdvancedSizeAnimation()
    }
}

@Composable
fun BasicSizeAnimation() {
    var isSmall by rememberSaveable { mutableStateOf(false) }

    val size by animateDpAsState(
        targetValue = if (isSmall) 50.dp else 150.dp
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(id = R.string.label_basic_animation))
        Spacer(modifier = Modifier.size(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(size)
                    .background(MaterialTheme.colors.secondary)
                    .align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.size(16.dp))
        Button(onClick = { isSmall = !isSmall }) {
            Text(text = stringResource(id = R.string.action_change_size))
        }
    }
}

@Composable
fun AdvancedSizeAnimation() {
    var isSmall by rememberSaveable { mutableStateOf(false) }
    var isTextVisible by rememberSaveable { mutableStateOf(true) }

    val size by animateDpAsState(
        targetValue = if (isSmall) 50.dp else 150.dp,
        animationSpec = tween(durationMillis = 1000, delayMillis = 250),
        finishedListener = {
            isTextVisible = !isTextVisible
        }
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(id = R.string.label_advanced_animation))
        Spacer(modifier = Modifier.size(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(size)
                    .background(MaterialTheme.colors.secondary)
                    .align(Alignment.Center)
            ) {
                if (isTextVisible) {
                    Text(
                        text = "Hello",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.size(16.dp))
        Button(onClick = { isSmall = !isSmall }) {
            Text(text = stringResource(id = R.string.action_change_size))
        }
    }
}