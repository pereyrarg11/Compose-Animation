package com.pereyrarg11.composeanimation.size

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
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
import com.pereyrarg11.composeanimation.R

@Preview(
    showSystemUi = true, showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun VisibilityAnimations() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(id = R.string.label_visibility_animation))
        BasicVisibilityAnimation()
        Spacer(modifier = Modifier.size(32.dp))
        AdvancedVisibilityAnimation()
    }
}

@Composable
fun BasicVisibilityAnimation() {
    var isVisible by rememberSaveable { mutableStateOf(true) }
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(id = R.string.label_basic_animation))
        Spacer(modifier = Modifier.size(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            AnimatedVisibility(visible = isVisible) {
                Box(
                    modifier = Modifier
                        .size(150.dp)
                        .background(color = MaterialTheme.colors.secondary)
                )
            }
        }
        Spacer(modifier = Modifier.size(16.dp))
        Button(onClick = { isVisible = !isVisible }) {
            Text(text = stringResource(id = R.string.action_change_visibility))
        }
    }
}

@Composable
fun AdvancedVisibilityAnimation() {
    var isVisible by rememberSaveable { mutableStateOf(true) }
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(id = R.string.label_advanced_animation))
        Spacer(modifier = Modifier.size(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            AnimatedVisibility(
                visible = isVisible,
                enter = slideInHorizontally(),
                exit = slideOutHorizontally()
            ) {
                Box(
                    modifier = Modifier
                        .size(150.dp)
                        .background(color = MaterialTheme.colors.secondary)

                )
            }
        }
        Spacer(modifier = Modifier.size(16.dp))
        Button(onClick = { isVisible = !isVisible }) {
            Text(text = stringResource(id = R.string.action_change_visibility))
        }
    }
}