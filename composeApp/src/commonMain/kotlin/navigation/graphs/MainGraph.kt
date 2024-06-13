package navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import main.screens.home.HomeScreen
import main.screens.settings.SettingsScreen
import main.screens.statistics.StatisticsScreen
import navigation.screens.HOME
import navigation.screens.SETTINGS
import navigation.screens.STATISTICS


@Composable
fun MainGraph(
    navController: NavController
){
    NavHost(
        navController = navController as NavHostController,
        startDestination = HOME
    ){
        composable(
            route = HOME
        ){
            HomeScreen(
                navigateToStatisticsScreen = {
                    navController.navigate(STATISTICS)
                },
                navigateToSettingsScreen = {
                    navController.navigate(SETTINGS)
                }
            )
        }
        composable(
            route = STATISTICS
        ){
            StatisticsScreen(
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
        composable(
            route = SETTINGS
        ){
            SettingsScreen(
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}