package com.example.game_list.ui.drawer

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector


data class DrawerMenu(val icon: ImageVector, val title: String, val route: String)

val drawerList = arrayOf(
    DrawerMenu(Icons.Filled.Home, "Home", "Home"),
    DrawerMenu(Icons.Filled.Search, "Search", "Search"),
    DrawerMenu(Icons.Filled.AccountBox, "Category", "Category")
)