package com.example.game_list.router

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import com.example.game_list.ui.pages.category.CategoryPage
import com.example.game_list.ui.pages.home.HomePage

sealed class Route(val route: String) {
    data object Home: Route("Home")
    data object Category: Route("Category")
}

fun pageList(paddingValues: PaddingValues):  List<Pair<String, @Composable () -> Unit>>{
     return listOf(
        Route.Home.route to { HomePage(paddingValues) },
        Route.Category.route to { CategoryPage(paddingValues) },
    )
}