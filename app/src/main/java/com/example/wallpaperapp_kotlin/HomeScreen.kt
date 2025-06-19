package com.example.wallpaperapp_kotlin

import android.annotation.SuppressLint
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
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
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.automirrored.filled.Article
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.automirrored.filled.TrendingDown
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun HomeScreen (viewModel: WallpaperViewModel = viewModel()) {

    val wallpapers by viewModel.wallpapers

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(5.dp),
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalItemSpacing = 12.dp
    ) {
        items(wallpapers) { wallpaper  ->
            GridItem(wallpaper)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GridItem(wallpaper: Wallpapers) {

    val sheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()
    var showSheet by remember { mutableStateOf(false) }

    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = {coroutineScope.launch {
                sheetState.hide()
                showSheet = false }},
            sheetState = sheetState
        ) {
            Column {

                Row (
                    modifier = Modifier.fillMaxWidth().padding(top = 10.dp))
                {
                    TextButton(onClick = {},
                        modifier = Modifier.fillMaxWidth()) {

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(15.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Favorite,
                                contentDescription = "Arrow"
                            )
                            Text(text = "Add to likes", fontSize = 18.sp)
                        }
                    }
                }

                Row (
                    modifier = Modifier.fillMaxWidth().padding(top = 10.dp))
                {
                    TextButton(onClick = {},
                        modifier = Modifier.fillMaxWidth()) {

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(15.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Download,
                                contentDescription = "Arrow"
                            )
                            Text(text = "Download", fontSize = 18.sp)
                        }
                    }
                }
            }
        }
    }


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color.LightGray)
    ) {
        AsyncImage(
            model = wallpaper.url,
            contentDescription = wallpaper.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize().
            clickable {
                coroutineScope.launch {
                    showSheet = true
                    sheetState.show() } }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EmptyScreenWithTopAppBar() {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .padding(0.dp)
                    .height(80.dp),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { /* No title */ },
                actions = {}
            )
        }
    ) {
        // No content inside Scaffold
    }
}


@Composable
fun BottomNav () {
    val navController = rememberNavController()

    Scaffold (

        bottomBar = {
            BottomAppBar(
                containerColor = Color.Black,
                contentColor = Color.White,
                modifier = Modifier.padding(0.dp).height(70.dp)
            ) {
                Row ( modifier = Modifier.padding(10.dp).fillMaxWidth().fillMaxHeight(), horizontalArrangement = Arrangement.SpaceEvenly){
                    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                        IconButton(onClick = {navController.navigate(Home.route)}, modifier = Modifier.height(38.dp)) {
                            Icon(imageVector = Icons.Filled.Home, contentDescription = "Home")
                        }

                    }

                    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                        IconButton(onClick = {navController.navigate(Likes.route)},modifier = Modifier.height(38.dp)) {
                            Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Likes")
                        }

                    }
                    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                        IconButton(onClick = {navController.navigate(Profile.route)},modifier = Modifier.height(38.dp)) {
                            Icon(imageVector = Icons.Filled.Person, contentDescription = "Profile")
                        }

                    }
                }
            }
        }

    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Home.route,
            modifier = Modifier.padding(innerPadding),
            enterTransition = { EnterTransition.None},
            exitTransition = { ExitTransition.None}
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




