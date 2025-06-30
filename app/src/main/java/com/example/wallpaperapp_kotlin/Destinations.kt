package com.example.wallpaperapp_kotlin


interface Destination {
    var route:String
}

object Login:Destination {
    override var route = "Login"
}

object Home:Destination {
    override var route = "Home"
}

object Profile:Destination {
    override var route = "Profile"
}

