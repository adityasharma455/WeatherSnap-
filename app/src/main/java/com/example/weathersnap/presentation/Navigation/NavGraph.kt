package com.example.weathersnap.presentation.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weathersnap.presentation.screen.CameraScreen.CameraScreen
import com.example.weathersnap.presentation.screen.CreateReportScreen.CreateReportScreen
import com.example.weathersnap.presentation.screen.ReportsScreen.ReportsScreen
import com.example.weathersnap.presentation.screen.WeatherScreen.WeatherScreen

@Composable
fun NavGraph(startDestination: String = "weather") {
    val navController = rememberNavController()
    NavHost(navController, startDestination = startDestination) {
        composable("weather") {
            WeatherScreen(navController)
        }
        composable(
            route = "create_report/{cityName}/{lat}/{lon}",
            arguments = listOf(
                navArgument("cityName") { type = NavType.StringType },
                navArgument("lat") { type = NavType.StringType },
                navArgument("lon") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val cityName = backStackEntry.arguments?.getString("cityName")!!
            val lat = backStackEntry.arguments?.getString("lat")!!.toDouble()
            val lon = backStackEntry.arguments?.getString("lon")!!.toDouble()
            CreateReportScreen(navController, cityName, lat, lon)
        }
        composable("camera") {
            CameraScreen(navController)
        }
        composable("reports") {
            ReportsScreen(navController)
        }
    }
}
