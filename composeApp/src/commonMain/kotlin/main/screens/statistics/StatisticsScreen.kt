package main.screens.statistics

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import main.screens.input.domain.model.Intake
import main.screens.input.presentation.IntakeViewModel
import org.koin.compose.koinInject


@Composable
fun StatisticsScreen (
    navigateBack: () -> Unit
){

    val intakeViewModel = koinInject<IntakeViewModel>()
    val intakes by intakeViewModel.caffeineIntake.collectAsState()

    StatisticsScreenContent(
        intakes = intakes,
        navigateBack = navigateBack
    )

}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun StatisticsScreenContent(
    modifier: Modifier = Modifier,
    intakes: List<Intake>,
    navigateBack: () -> Unit
){

    val windowSizeClass = calculateWindowSizeClass()
    val isCompacted = windowSizeClass.widthSizeClass != WindowWidthSizeClass.Compact

    Scaffold(
        topBar = {
            if (!isCompacted) {
                TopAppBar(
                    title = {},
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                navigateBack()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = null
                            )
                        }
                    },
                    modifier = modifier
                        .statusBarsPadding()
//                        .border(width = 1.dp, color = Color.Red)
                )
            }
        },
//        modifier = modifier.border(width = 1.dp, color = Color.White)
    ) { paddingValues ->

        Row(
            modifier = if (isCompacted){
                modifier
                    .statusBarsPadding()
                    .navigationBarsPadding()
//                    .border(width = 1.dp, color = Color.Red)
            }else{
                modifier
                    .padding(paddingValues)
//                    .border(width = 1.dp, color = Color.Red)
            }
        ) {

            if (isCompacted) {
                NavigationRail(
                    header = {
                        IconButton(
                            onClick = {
                                navigateBack()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = null
                            )
                        }
                    },
                    modifier = Modifier
                        .offset(x = (-1).dp)
//                        .border(width = 1.dp, color = Color.Red)
                ){}
            }

            if (!isCompacted) {
                Column(
                    modifier = modifier
                        .weight(1f)
                        .fillMaxSize()
                ) {



                }
            } else {
                Box(
                    modifier = modifier
                        .weight(1f)
                        .fillMaxHeight()
                ){
                    Column(
                        modifier = modifier
                            .fillMaxSize()
                    ) {



                    }
                }
            }

        }


    }

}
