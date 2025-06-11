package br.com.movieapp.core.presetation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import br.com.movieapp.core.presetation.navigation.BottomNavigationBar
import br.com.movieapp.core.presetation.navigation.CurrentRoute
import br.com.movieapp.core.presetation.navigation.DetailScreenNav
import br.com.movieapp.core.presetation.navigation.NavigationGraph

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold(
        bottomBar = {
            if (CurrentRoute(navController = navController) != DetailScreenNav.DetailsScreen.route) {
                BottomNavigationBar(navController)
            }
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier.padding(paddingValues)
            ) {
                NavigationGraph(navController = navController)
            }
        }
    )
}