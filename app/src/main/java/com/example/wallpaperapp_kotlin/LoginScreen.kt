package com.example.wallpaperapp_kotlin

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginUpper() {
    Column (){
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "demo",
            modifier = Modifier.fillMaxSize()
        )
    }
}


@Composable
fun LoginLower () {

    var userName by remember {
        mutableStateOf("")
    }

    var userPassword by remember {
        mutableStateOf("")
    }

    Column (
        modifier = Modifier.fillMaxSize().padding(10.dp).padding(horizontal = 10.dp)

    ){
        Text(text = "Pixel perfect wallpapers.",
            modifier = Modifier.fillMaxWidth().padding(vertical = 0.dp),
            fontSize = 30.sp)

        Text(text = "For android.",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
                .padding(vertical = 10.dp),
            fontSize = 30.sp,
            )

        Text(text = "Username", fontSize = 12.sp,
            modifier = Modifier.padding(bottom = 10.dp))
        OutlinedTextField(
            value = userName,
            onValueChange = { userName = it },
            placeholder = { Text("")},
            singleLine = true,
            shape = RoundedCornerShape(10.dp),
            textStyle = TextStyle(
                textAlign = TextAlign.Start,
                fontSize = 18.sp
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Text(text = "Password", fontSize = 12.sp,
            modifier = Modifier.padding(bottom = 10.dp, top = 10.dp))
        OutlinedTextField(
            value = userPassword,
            onValueChange = { userPassword = it },
            placeholder = { Text("")},
            singleLine = true,
            shape = RoundedCornerShape(10.dp),
            textStyle = TextStyle(
                textAlign = TextAlign.Start,
                fontSize = 18.sp
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {}, modifier = Modifier.fillMaxWidth().
            padding(top = 50.dp).height(50.dp),
            shape = RoundedCornerShape(10.dp)) {
            Text(text = "Continue")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenWithHalves() {
    Column(modifier = Modifier.fillMaxSize()) {
        // Upper half
        Box(modifier = Modifier.weight(1f)) {
            LoginUpper()
        }

        // Lower half
        Box(modifier = Modifier.weight(1f)) {
            LoginLower()
        }
    }
}