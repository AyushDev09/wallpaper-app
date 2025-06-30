package com.example.wallpaperapp_kotlin

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import kotlinx.coroutines.launch

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
fun LoginLower (navController: NavController) {

    var userName by remember {
        mutableStateOf("")
    }

    var userPassword by remember {
        mutableStateOf("")
    }

    val scope = rememberCoroutineScope()

    val context = LocalContext.current

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
            onClick = {
                scope.launch {
                    signIn(userName,userPassword,context,navController)
                }
            }, modifier = Modifier.fillMaxWidth().
            padding(top = 50.dp).height(50.dp),
            shape = RoundedCornerShape(10.dp)) {
            Text(text = "Continue")
        }
    }
}

suspend fun signIn(email: String, password: String, context: Context, navController: NavController) {
    try {
        val session = supabase.auth.signInWith(Email) {
            this.email = email
            this.password = password
        }

        if (session != null) {
            Toast.makeText(context, "Login Success", Toast.LENGTH_SHORT).show()
            navController.navigate("Home") {
                popUpTo("Login") { inclusive = true } // Clears backstack
            }
        } else {
            Toast.makeText(context, "Invalid credentials", Toast.LENGTH_SHORT).show()
        }
    } catch (e: Exception) {
        Log.e("SignIn", "Login failed: ${e.localizedMessage}")
        Toast.makeText(context, "Login Failed: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
    }
}


@Composable
fun ScreenWithHalves(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        // Upper half
        Box(modifier = Modifier.weight(1f)) {
            LoginUpper()
        }

        // Lower half
        Box(modifier = Modifier.weight(1f)) {
            LoginLower(navController)
        }
    }
}