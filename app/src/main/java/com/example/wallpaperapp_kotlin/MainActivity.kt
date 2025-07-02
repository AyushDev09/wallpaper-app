package com.example.wallpaperapp_kotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.wallpaperapp_kotlin.ui.theme.WallpaperApp_KotlinTheme
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

val supabase = createSupabaseClient(
    supabaseUrl = "https://vjgdmxevzgpikalvjxlv.supabase.co",
    supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InZqZ2RteGV2emdwaWthbHZqeGx2Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDg1MTgwNTksImV4cCI6MjA2NDA5NDA1OX0.nMBBpEAu5eG67bH2THRP-E2H49Hx8TPQ7aNZlvJQ_GQ"
) {
    install(Auth)
    install(Postgrest)
}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WallpaperApp_KotlinTheme {
                MainScreen()
                }
            }
        }
    }

