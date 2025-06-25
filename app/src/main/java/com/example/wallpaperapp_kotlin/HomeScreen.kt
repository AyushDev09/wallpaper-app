package com.example.wallpaperapp_kotlin

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
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
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


@Composable
fun CategoryChipsBar(
    categories: List<String>,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 12.dp)
    ) {
        items(categories) { category ->
            FilterChip(
                selected = selectedCategory == category,
                onClick = { onCategorySelected(category) },
                label = { Text(category) }
            )
        }
    }
}



@Composable
fun HomeScreen(viewModel: WallpaperViewModel = viewModel()) {
    val wallpapers by viewModel.wallpapers
    val selectedCategory = viewModel.selectedCategory

    val categories = listOf("All","pixel", "anime", "game", "future")

    Column {
        CategoryChipsBar(
            categories = categories,
            selectedCategory = selectedCategory,
            onCategorySelected = { viewModel.fetchWallpapers(it) }
        )

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(5.dp),
            verticalItemSpacing = 12.dp,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(wallpapers) { wallpaper ->
                GridItem(wallpaper)
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GridItem(wallpaper: Wallpapers) {

    val context = LocalContext.current
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
                    TextButton(onClick = {
                        coroutineScope.launch {
                            downloadImage(context, wallpaper.url)
                            sheetState.hide()
                            showSheet = false
                        }},
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
                        IconButton(onClick = {
                            navController.navigate(Home.route) {
                                popUpTo(navController.graph.startDestinationId)
                                {
                                saveState = true
                            }
                                launchSingleTop = true
                                restoreState = true
                            }},

                            modifier = Modifier.height(38.dp)) {
                            Icon(imageVector = Icons.Filled.Home, contentDescription = "Home")
                        }

                    }

                    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                        IconButton(onClick = {
                            navController.navigate(Profile.route) {
                                popUpTo(navController.graph.startDestinationId)
                                {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }},

                            modifier = Modifier.height(38.dp)) {
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
            composable(Profile.route){
                ProfileScreen(navController = navController)
            }
        }
    }
}


suspend fun downloadImage (context: Context, imageUrl: String) {

    val loader = ImageLoader(context)
    val request = ImageRequest.Builder(context).data(imageUrl).build()

    val result = (loader.execute(request) as? SuccessResult)?.drawable
    val bitmap = (result as BitmapDrawable).bitmap

    val filename = "wallpaper_${System.currentTimeMillis()}.jpg"

    val contentValues = ContentValues().apply {
        put(MediaStore.Images.Media.DISPLAY_NAME, filename)
        put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/Wallpapers")
        put(MediaStore.Images.Media.IS_PENDING, 1)
    }

    val contentResolver = context.contentResolver
    val imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

    imageUri?.let { uri ->
        contentResolver.openOutputStream(uri)?.use { outputStream ->
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        }

        contentValues.clear()
        contentValues.put(MediaStore.Images.Media.IS_PENDING, 0)
        contentResolver.update(uri, contentValues, null, null)

        Toast.makeText(context, "Image downloaded", Toast.LENGTH_SHORT).show()
    }

}



