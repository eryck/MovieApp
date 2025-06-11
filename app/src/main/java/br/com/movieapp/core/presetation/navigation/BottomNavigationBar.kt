package br.com.movieapp.core.presetation.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import br.com.movieapp.ui.theme.black
import br.com.movieapp.ui.theme.yellow

@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    val items = listOf(
        BottomNavItem.MoviePopular,
        BottomNavItem.MovieSearch,
        BottomNavItem.MovieFavorite
    )

    BottomNavigation(
        contentColor = yellow,
        backgroundColor = black
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { destination ->
            BottomNavigationItem(
                selected = currentRoute == destination.route,
                onClick = {
                    navController.navigate(destination.route) {
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = destination.icon, contentDescription = null
                    )
                },
                label = {
                    Text(text = destination.title)
                }
            )
        }
    }
}

@Composable
fun CurrentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}