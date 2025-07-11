# Wallpaper-app

WallpaperApp is an Android application built using Kotlin, which allows users to browse and download high-quality wallpapers. The app offers a smooth and intuitive user experience with a login page, categories for wallpapers, and fast downloading with a simple click. It uses Supabase for authentication and wallpaper storage.



---



## 📥 Download

Don't want to build the project yourself?  
You can download the latest APK directly from the [Releases page](https://github.com/AyushDev09/wallpaper-app/releases/tag/v1.0.0).

📱 [Download APK](https://github.com/AyushDev09/wallpaper-app/releases/download/v1.0.0/app-debug.apk)



---


## Login credentials (Internet required)

- Username: ayush@gmail.com
- Password: ayush@gmail.com



---



## 🚀 Features

This app allows users to:

- **Login Page:** Users can securely log in to their account.
- **Categories:** Select from a wide range of wallpaper categories.
- **Easy UI:** Simple and clean user interface for a smooth experience.
- **Fast Downloads:** Download wallpapers with just a click.
- **Logout:** Easily log out of the app when done.
- **Shimmer Effect:** UI includes a shimmer effect for loading content.



---



## 🎯 Technologies

- **Kotlin:** The primary language used to build the app.
- **Android Studio:** IDE used for development.
- **Ktor:** API fetching and asynchronous network operations.
- **Supabase:** User authentication and wallpaper storage.
- **MVVM Architecture:** Ensures separation of concerns and a clean architecture.
- **Coil:** Image loading library for displaying wallpapers.
- **Gradle:** Build system for the project.



---



## 🛠️ Installation

- **Android Studio (latest version):** Ensure that you have the latest version of Android Studio installed.
- **Supabase Account:** You'll need a Supabase account for the API and authentication.

1. **Clone the repository:**

   ```bash
   git clone <repo-url>
   cd your-project-folder

2. **Open in Android Studio**

3. **Install dependencies (build.gradle.app)**

   ```bash
   plugin {
    kotlin("plugin.serialization") version "2.0.0"
   }
   ```
   
    ```bash
   dependencies {
     implementation(platform(libs.bom))
     implementation(libs.postgrest.kt) // Interacting with the PostgREST API
     implementation(libs.auth.kt) // Handle authentication functionality
     implementation(libs.realtime.kt) // Real-time features
     implementation(libs.gotruekt) // Supabase user authentication
   
     implementation(libs.compose.shimmer) // Shimmer effect in Compose UI
   
     implementation(libs.ktor.client.android) // Asynchronous HTTP client
   
     implementation(libs.coil.compose) // Image loading 
   
     implementation(libs.androidx.lifecycle.viewmodel.compose) // ViewModel in Compose
     implementation(libs.androidx.lifecycle.viewmodel.ktx) // ViewModelScope and coroutines
   }

---

4. **Copy local.properties.sample to your local.properties**

   

---

5. **Build and run the app (Physical device or emulator)**



---



## ⚙️ Usage

- Log in using the provided credentials.
- Browse various wallpaper categories.
- Tap on a wallpaper to view and download it.
- Log out of the app when done.



---



## Contact

Email: ayushdev9090@gmail.com
