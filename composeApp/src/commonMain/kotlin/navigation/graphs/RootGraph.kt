package navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import main.MainScreen
import navigation.screens.MAIN


@Composable
fun RootGraph(
    navController: NavController
){
    NavHost(
        navController = navController as NavHostController,
        startDestination = MAIN
    ){
        composable(
            route = MAIN
        ){
            MainScreen()
        }
    }
}