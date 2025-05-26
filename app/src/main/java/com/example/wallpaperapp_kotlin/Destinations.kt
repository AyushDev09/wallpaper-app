package com.example.wallpaperapp_kotlin

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