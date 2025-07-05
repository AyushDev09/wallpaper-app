package com.example.wallpaperapp_kotlin


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.wallpaperapp_kotlin.ui.theme.WallpaperApp_KotlinTheme
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

val supabaseUrl = BuildConfig.SUPABASE_URL
val supabaseKey = BuildConfig.SUPABASE_KEY

val supabase = createSupabaseClient(
    supabaseUrl = supabaseUrl,
    supabaseKey = supabaseKey
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

