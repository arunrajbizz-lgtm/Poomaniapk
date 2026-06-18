package com.poomani.tv.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.poomani.tv.ui.screens.*

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object Providers : Screen("providers")
    object LiveTv : Screen("livetv")
    object Movies : Screen("movies")
    object Series : Screen("series")
    object Search : Screen("search")
    object Settings : Screen("settings")
    object Player : Screen("player/{streamUrl}") {
        fun createRoute(streamUrl: String) = "player/$streamUrl"
    }
}

@Composable
fun MainNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) { SplashScreen(navController) }
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.Providers.route) { ProviderScreen(navController) }
        composable(Screen.LiveTv.route) { LiveTvScreen(navController) }
        composable(Screen.Movies.route) { MoviesScreen(navController) }
        composable(Screen.Series.route) { SeriesScreen(navController) }
        composable(Screen.Search.route) { SearchScreen(navController) }
        composable(Screen.Settings.route) { SettingsScreen(navController) }
        composable(Screen.Player.route) { backStackEntry ->
            val url = backStackEntry.arguments?.getString("streamUrl") ?: ""
            PlayerScreen(streamUrl = url, navController = navController)
        }
    }
}
