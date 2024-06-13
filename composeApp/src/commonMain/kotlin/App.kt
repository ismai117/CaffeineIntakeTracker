import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import database.AppDatabase
import database.getAppDatabase
import main.screens.input.presentation.intakeModule
import org.jetbrains.compose.ui.tooling.preview.Preview
import navigation.graphs.RootGraph
import org.koin.compose.KoinApplication
import org.koin.dsl.module


@Composable
@Preview
fun App() = AppTheme {

    val navController = rememberNavController()

    KoinApplication(
        application = {
            modules(
                module {
                    single<AppDatabase> { getAppDatabase() }
                },
                intakeModule
            )
        }
    ){
        RootGraph(
            navController = navController
        )
    }

}