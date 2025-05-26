package com.example.wallpaperapp_kotlin

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController

@Composable
fun LikesScreen (navController: NavController) {
    Column (modifier = Modifier.fillMaxSize())
    {
        Image(painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null)
    }
}