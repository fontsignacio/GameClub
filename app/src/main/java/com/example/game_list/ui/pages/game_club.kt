package com.example.game_list.ui.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.game_list.router.Route
import com.example.game_list.router.pageList
import com.example.game_list.ui.drawer.drawerList
import kotlinx.coroutines.launch

@Composable
fun GameClub() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(navController)
        }
    ) {
        ScaffoldWithTopBar(navController){
            scope.launch {
                drawerState.open()
            }
        }
    }
}

@Composable
private fun DrawerContent(navController: NavHostController) {
    ModalDrawerSheet(
        modifier = Modifier.width(250.dp)
    ){
        Spacer(modifier = Modifier.height(10.dp))
        Text("GameClub",
            fontSize = 30.sp,
            modifier = Modifier.padding(25.dp),
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(30.dp))
        HorizontalDivider()
        drawerList.forEach{
            NavigationDrawerItem(
                modifier = Modifier.padding(horizontal = 12.dp),
                label = { Text(text = it.title) },
                selected = false,
                icon = { Icon(it.icon, contentDescription = null) },
                onClick = { navController.navigate(it.route) }
            )
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScaffoldWithTopBar(navController: NavHostController, openDrawer: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "GameClub",
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                navigationIcon = {
                    IconButton(onClick = openDrawer) {
                        Icon(Icons.Filled.Menu,
                            tint = MaterialTheme.colorScheme.primary,
                            contentDescription = null)
                    }
                }
            )
        }, content = {
           Routers(navController, paddingValues = it)
        }
    )
}

@Composable
private fun Routers(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = Route.Home.route,
    ) {
        pageList(paddingValues = paddingValues).forEach { (route, content) ->
            composable(route = route) { content() }
        }
    }
}
