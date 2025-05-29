package com.example.wallpaperapp_kotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ListItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.example.wallpaperapp_kotlin.ui.theme.WallpaperApp_KotlinTheme
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text

val supabase = createSupabaseClient(
    supabaseUrl = "https://vjgdmxevzgpikalvjxlv.supabase.co",
    supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InZqZ2RteGV2emdwaWthbHZqeGx2Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDg1MTgwNTksImV4cCI6MjA2NDA5NDA1OX0.nMBBpEAu5eG67bH2THRP-E2H49Hx8TPQ7aNZlvJQ_GQ"
) {
    install(Postgrest)
}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WallpaperApp_KotlinTheme {
                //BottomNav()
                TestList()
                }
            }
        }
    }

@Serializable
data class Note (
    val id: Int,
    val body: String
)

@Composable
fun TestList() {
    val notes = remember { mutableStateListOf<Note>()}
    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            val results = supabase.from("test").select().decodeList<Note>()
            notes.addAll(results)
        }
    }

    LazyColumn {
        items(notes) {
            note -> ListItem(headlineContent = { Text(text = note.body)})
        }
    }
}
