package com.example.wallpaperapp_kotlin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun HomeScreen (viewModel: WallpaperViewModel = viewModel()) {

    val wallpapers by viewModel.wallpapers

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(5.dp),
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(wallpapers) { wallpaper  ->
            GridItem(wallpaper)
        }
    }
}

@Composable
fun GridItem(wallpaper: Wallpapers) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.LightGray)
    ) {
        AsyncImage(
            model = wallpaper.url,
            contentDescription = wallpaper.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun BottomNav () {
    val navController = rememberNavController()

    Scaffold (

        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(0.dp)
            ) {
                Row ( modifier = Modifier.padding(10.dp).fillMaxWidth().fillMaxHeight(), horizontalArrangement = Arrangement.SpaceEvenly){
                    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                        IconButton(onClick = {navController.navigate(Home.route)}, modifier = Modifier.height(38.dp)) {
                            Icon(imageVector = Icons.Filled.Home, contentDescription = "Home")
                        }
                        Text(
                            text = "Home",
                            fontSize = 10.sp
                        )
                    }

                    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                        IconButton(onClick = {navController.navigate(Likes.route)},modifier = Modifier.height(38.dp)) {
                            Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Likes")
                        }
                        Text(
                            text = "Likes",
                            fontSize = 10.sp
                        )
                    }
                    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                        IconButton(onClick = {navController.navigate(Profile.route)},modifier = Modifier.height(38.dp)) {
                            Icon(imageVector = Icons.Filled.Person, contentDescription = "Profile")
                        }
                        Text(
                            text = "Profile",
                            fontSize = 10.sp
                        )
                    }
                }
            }
        }

    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Home.route,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(Home.route){
                HomeScreen()
            }
            composable(Likes.route){
                LikesScreen(navController = navController)
            }
            composable(Profile.route){
                ProfileScreen(navController = navController)
            }
        }
    }
}
