package com.example.wallpaperapp_kotlin

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch


@Serializable
data class Wallpapers (
    val id: Int,
    val title: String,
    val url: String,
    val category: String
)

class WallpaperViewModel : ViewModel() {

    private val _wallpapers = mutableStateOf<List<Wallpapers>>(emptyList())
    val wallpapers: State<List<Wallpapers>> = _wallpapers

    init {
        fetchWallpapers()
    }

    private fun fetchWallpapers() {
        viewModelScope.launch {
            try {
                val results = withContext(Dispatchers.IO) {
                    supabase.from("wallpapertable").select().decodeList<Wallpapers>()
                }
                _wallpapers.value = results
            } catch (e: Exception) {
                Log.e("WallpaperViewModel", "Failed to fetch wallpapers", e)
            }
        }
    }
}