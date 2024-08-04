package com.example.game_list.ui.pages

import android.annotation.SuppressLint
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
import com.example.game_list.ui.drawer.drawerList
import com.example.game_list.ui.pages.home.HomePage
import kotlinx.coroutines.launch

@Composable
fun GameClub() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent()
        }
    ) {
        ScaffoldWithTopBar {
            scope.launch {
                drawerState.open()
            }
        }
    }
}

@Composable
fun DrawerContent() {
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
                onClick = { /*TODO*/ }
            )
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldWithTopBar(openDrawer: () -> Unit) {
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
            HomePage(paddingValues = it)
        }
    )
}
