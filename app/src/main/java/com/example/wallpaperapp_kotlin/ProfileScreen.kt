package com.example.wallpaperapp_kotlin

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import io.github.jan.supabase.auth.auth
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen (navController: NavController) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Column (modifier = Modifier.fillMaxSize()) {

        Column (modifier = Modifier.weight(1f).padding(10.dp),verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Row (verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth())
            {
                Image(painter = painterResource(id = R.drawable.user), contentDescription = null,
                    modifier = Modifier.size(width = 150.dp, height = 150.dp))
            }

            Row (verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth().padding(top = 20.dp))
            {
                Text( text = "Username", fontSize = 20.sp)
            }
        }

        Column (modifier = Modifier.weight(1f)) {

            Row (
                modifier = Modifier.fillMaxWidth().padding(top = 10.dp))
            {
                TextButton(onClick = {
                    scope.launch {
                        logout(navController,context)
                    }
                },
                    modifier = Modifier.fillMaxWidth()) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Log out", fontSize = 18.sp)
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Arrow"
                        )
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
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "About app", fontSize = 18.sp)
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Arrow"
                        )
                    }
                }
            }
        }

    }

}

// Function to handle logout
suspend fun logout(navController: NavController, context: Context) {
    try {

        val response = supabase.auth.signOut()

        // If logout successful, navigate to Login screen
        if (response != null) {
            Toast.makeText(context, "Logged out successfully", Toast.LENGTH_SHORT).show()
            navController.navigate(Login.route) {
                // Remove all previous screens from the backstack
                popUpTo(Login.route) { inclusive = true }
                launchSingleTop = true
                restoreState = true
            }
        }
    } catch (e: Exception) {
        // Handle any errors during logout
        Toast.makeText(context, "Error logging out: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
    }
}