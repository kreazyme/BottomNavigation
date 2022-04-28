package com.midterm.navigation

sealed class NavItem(var title: String, var icon: Int, var rout_string: String, var appbar_title : String){
    object Home: NavItem(title = "Home", icon = R.drawable.ic_baseline_home_24, rout_string = "home", appbar_title = "Home Application")
    object Music: NavItem(title = "Music", icon = R.drawable.ic_baseline_music_note_24, rout_string = "music", appbar_title = "Play Music")
    object Profile: NavItem(title = "Profile", icon = R.drawable.ic_baseline_person_24, rout_string = "person", appbar_title = "Your Profile")
    object Setting: NavItem(title = "Tips", icon = R.drawable.ic_baseline_highlight_24, rout_string = "setting", appbar_title = "Tips Calculation")
}
