package main.screens.settings

import LocalThemeIsDark
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun SettingsScreen(
    navigateBack: () -> Unit,
) {

    SettingsScreenContent(
        navigateBack = navigateBack
    )

}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun SettingsScreenContent(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
) {

    val windowSizeClass = calculateWindowSizeClass()
    val isCompacted = windowSizeClass.widthSizeClass != WindowWidthSizeClass.Compact

    var themeDialogEnabled by remember { mutableStateOf(false) }
    var isDark by LocalThemeIsDark.current

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

                    Card(
                        modifier = modifier
                            .padding(top = 40.dp, start = 16.dp, end = 16.dp)
                            .fillMaxWidth()
                            .height(70.dp)
                            .clickable {
                                themeDialogEnabled = true
                            }
                    ) {
                        Box(
                            modifier = modifier.fillMaxSize()
                        ) {
                            Text(
                                text = "Theme",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                ),
                                modifier = modifier
                                    .padding(start = 12.dp)
                                    .align(Alignment.CenterStart)
                            )
                            IconButton(
                                onClick = {
                                    isDark = !isDark
                                },
                                modifier = modifier.align(Alignment.CenterEnd)
                            ) {
                                Icon(
                                    imageVector = if (isDark) Icons.Default.LightMode else Icons.Default.DarkMode,
                                    contentDescription = null
                                )
                            }

                        }
                    }


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

                        Card(
                            modifier = modifier
                                .padding(top = 40.dp, start = 16.dp, end = 16.dp)
                                .fillMaxWidth()
                                .height(70.dp)
                                .clickable {
                                    themeDialogEnabled = true
                                }
                        ) {
                            Box(
                                modifier = modifier.fillMaxSize()
                            ) {
                                Text(
                                    text = "Theme",
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    modifier = modifier
                                        .padding(start = 12.dp)
                                        .align(Alignment.CenterStart)
                                )
                                IconButton(
                                    onClick = {
                                        isDark = !isDark
                                    },
                                    modifier = modifier.align(Alignment.CenterEnd)
                                ) {
                                    Icon(
                                        imageVector = if (isDark) Icons.Default.LightMode else Icons.Default.DarkMode,
                                        contentDescription = null
                                    )
                                }

                            }
                        }


                    }
                }
            }

        }


    }

}


