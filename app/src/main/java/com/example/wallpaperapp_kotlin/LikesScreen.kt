package com.example.wallpaperapp_kotlin

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Composable
fun LikesScreen (navController: NavController) {
    Column (modifier = Modifier.fillMaxSize())
    {
        Image(painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null)
    }
}

@Preview(showBackground = true)
@Composable
fun Counter () {

    var value by remember {
        mutableIntStateOf(0)
    }

    Column (modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = value.toString())

        Button(onClick = {value += 1 }) { }
    }
}