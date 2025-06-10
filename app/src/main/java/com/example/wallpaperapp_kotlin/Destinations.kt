package com.example.wallpaperapp_kotlin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.tooling.preview.Preview

interface Destination {
    var route:String
}

object Home:Destination {
    override var route = "Home"
}

object Likes:Destination {
    override var route = "Likes"
}

object Profile:Destination {
    override var route = "Profile"
}


//@Composable
//fun homeScreen(list: List<Int>) {
//    LazyVerticalGrid(
//        columns = GridCells.Fixed(2),
//        contentPadding = PaddingValues(5.dp),
//        modifier = Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.spacedBy(8.dp),
//        horizontalArrangement = Arrangement.spacedBy(8.dp)
//    ) {
//        items(list) { item ->
//            GridItem()
//        }
//    }
//}
//
//@Composable
//fun GridItem() {
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .aspectRatio(1f) // Makes it a square
//            .background(Color.Gray)
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun showscreen(){
//    val sampleList = listOf(1,2,3,4,5,6,7,8,9,10)
//    homeScreen(list = sampleList)
//}