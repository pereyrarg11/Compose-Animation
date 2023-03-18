package com.pereyrarg11.composeanimation.crossfade

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pereyrarg11.composeanimation.R

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun CrossfadeAnimations() {
    var componentType: ComponentType by rememberSaveable { mutableStateOf(ComponentType.Image) }
    var clickCounter by rememberSaveable { mutableStateOf(0) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(id = R.string.label_crossfade_animation))
        Spacer(modifier = Modifier.size(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Crossfade(targetState = componentType) {
                when (it) {
                    ComponentType.Image -> {
                        Image(
                            painter = painterResource(id = R.drawable.img_cat),
                            contentDescription = "cat",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(150.dp)
                                .clip(RoundedCornerShape(16.dp))
                        )
                    }
                    ComponentType.Text -> {
                        Text(text = stringResource(id = R.string.app_name))
                    }
                    ComponentType.Box -> {
                        Box(
                            modifier = Modifier
                                .size(150.dp)
                                .background(MaterialTheme.colors.secondary)
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.size(16.dp))
        Button(
            onClick = {
                clickCounter++
                val position = clickCounter % 3
                componentType = ComponentType.values()[position]
            }
        ) {
            Text(text = stringResource(id = R.string.action_change_component))
        }
    }
}

enum class ComponentType() {
    Image, Text, Box
}