package main.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalViewConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import main.screens.input.presentation.IntakeViewModel
import main.screens.input.domain.model.Intake
import io.ktor.http.headers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import main.components.remainingIndicator
import main.screens.input.presentation.IntakeEvent
import org.koin.compose.koinInject


@Composable
fun HomeScreen(
    navigateToStatisticsScreen: () -> Unit,
    navigateToSettingsScreen: () -> Unit,
) {

    val intakeViewModel = koinInject<IntakeViewModel>()
    val caffeineIntakes by intakeViewModel.caffeineIntake.collectAsState()


    HomeScreenContent(
        caffeineIntakes = caffeineIntakes,
        onEvent = intakeViewModel::onEvent,
        navigateToStatisticsScreen = navigateToStatisticsScreen,
        navigateToSettingsScreen = navigateToSettingsScreen
    )

}

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class,
    ExperimentalMaterial3WindowSizeClassApi::class
)
@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    caffeineIntakes: List<Intake>,
    onEvent: (IntakeEvent) -> Unit,
    navigateToStatisticsScreen: () -> Unit,
    navigateToSettingsScreen: () -> Unit,
) {

    val windowSizeClass = calculateWindowSizeClass()
    val isCompacted = windowSizeClass.widthSizeClass != WindowWidthSizeClass.Compact
    var enableInputDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            if (!isCompacted) {
                TopAppBar(
                    title = {},
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                navigateToStatisticsScreen()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.BarChart,
                                contentDescription = null
                            )
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = {
                                navigateToSettingsScreen()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Settings,
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
        floatingActionButton = {
            if (!isCompacted) {
                FloatingActionButton(
                    onClick = {
                        enableInputDialog = true
                    },
                    modifier = modifier.padding(bottom = 24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null
                    )
                }
            }
        },
        modifier = modifier
            .fillMaxSize()
//            .border(width = 1.dp, color = Color.White)
    ) { paddingValues ->


        Row(
            modifier = if (isCompacted){
                modifier
                    .padding(start = 24.dp, top = 24.dp, end = 50.dp, bottom = 24.dp)
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
                        FloatingActionButton(
                            onClick = {
                                enableInputDialog = true
                            },
                            modifier = modifier.padding(top = 12.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = null
                            )
                        }
                    },
                    content = {
                        Column(
                            modifier = Modifier.fillMaxHeight(),
                            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Bottom)
                        ) {
                            IconButton(
                                onClick = {
                                    navigateToStatisticsScreen()
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.BarChart,
                                    contentDescription = null
                                )
                            }
                            IconButton(
                                onClick = {
                                    navigateToSettingsScreen()
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Settings,
                                    contentDescription = null
                                )
                            }
                        }
                    },
                    modifier = Modifier
                        .offset(x = (-1).dp),
//                        .border(width = 1.dp, color = Color.Red),
                    windowInsets = WindowInsets(0.dp)
                )
            }

            if (!isCompacted) {
                Column(
                    modifier = modifier
                        .weight(1f)
                        .fillMaxSize()
//                        .border(width = 1.dp, color = Color.Red)
                ) {

                    Column(
                        modifier = modifier
                            .align(Alignment.CenterHorizontally)
                    ) {
                        remainingIndicator(
                            intake = caffeineIntakes.sumOf { it.mg }
                        )
                    }


                    LazyColumn(
                        modifier = modifier
                            .padding(top = 24.dp)
                            .fillMaxWidth()
                            .weight(1f),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {

                        stickyHeader {
                            Box(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .height(40.dp)
                                    .background(MaterialTheme.colorScheme.background)
                            ) {
                                Text(
                                    text = "History",
                                    style = TextStyle(
                                        color = MaterialTheme.colorScheme.primary,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    modifier = modifier
                                        .padding(ButtonDefaults.TextButtonContentPadding)
                                        .align(Alignment.CenterStart)
                                )
                            }
                        }

                        items(
                            items = caffeineIntakes
                        ) { item ->

                            IntakeItem(intake = item)

                        }

                    }


                }
            }
            else {
                Box(
                    modifier = modifier
                        .weight(1f)
                        .fillMaxHeight()
//                        .border(width = 1.dp, color = Color.Red)
                ) {

                    Row(
                        modifier = modifier
                            .fillMaxSize()
//                            .border(width = 1.dp, color = Color.White)
                    ) {

                        Box(
                            modifier = modifier
                                .weight(1f)
                                .fillMaxHeight(),
                            contentAlignment = Alignment.Center
                        ) {
                            remainingIndicator(
                                intake = caffeineIntakes.sumOf { it.mg }
                            )
                        }


                        LazyColumn(
                            modifier = modifier
                                .weight(1f)
                                .fillMaxHeight(),
                            contentPadding = PaddingValues(16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {

                            stickyHeader {
                                Box(
                                    modifier = modifier
                                        .fillMaxWidth()
                                        .height(40.dp)
                                        .background(MaterialTheme.colorScheme.background)
                                ) {
                                    Text(
                                        text = "History",
                                        style = TextStyle(
                                            color = MaterialTheme.colorScheme.primary,
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold
                                        ),
                                        modifier = modifier
                                            .padding(ButtonDefaults.TextButtonContentPadding)
                                            .align(Alignment.CenterStart)
                                    )
                                }
                            }

                            items(
                                items = caffeineIntakes
                            ) { item ->

                                IntakeItem(intake = item)

                            }

                        }

                    }

                }
            }

        }

        if (enableInputDialog) {
            InputDialog(
                onEvent = {
                    onEvent(it)
                    enableInputDialog = false
                }
            )
        }


    }


}

@Composable
fun IntakeItem(
    modifier: Modifier = Modifier,
    intake: Intake
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .height(70.dp),
    ) {
        Row(
            modifier = modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = modifier
                    .padding(start = 16.dp)
                    .weight(1f),
//                    .border(width = 1.dp, color = Color.Red),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = intake.name,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = intake.date,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            Text(
                text = "${intake.mg} mg",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = modifier
                    .padding(end = 16.dp)
            )
        }
    }
}

@Composable
fun InputDialog(
    modifier: Modifier = Modifier,
    onEvent: (IntakeEvent) -> Unit,
) {

    val focusManager = LocalFocusManager.current

    var itemName by remember { mutableStateOf("") }
    var mgAmount by remember { mutableIntStateOf(0) }

    val incrementInteractionSource = remember { MutableInteractionSource() }
    val deIncrementInteractionSource = remember { MutableInteractionSource() }

    val viewConfiguration = LocalViewConfiguration.current

    LaunchedEffect(incrementInteractionSource) {
        var isLongClick = false
        incrementInteractionSource.interactions.collectLatest {
            when (it) {
                is PressInteraction.Press -> {
                    isLongClick = false
                    delay(viewConfiguration.longPressTimeoutMillis)
                    isLongClick = true
                    while (isLongClick && mgAmount < 400) {
                        mgAmount++
                        delay(100)
                    }
                }

                is PressInteraction.Release -> {
                    if (isLongClick.not()) {
                        if (mgAmount < 400) {
                            mgAmount++
                        }
                    }
                    isLongClick = false
                }

                is PressInteraction.Cancel -> {
                    isLongClick = false
                }
            }
        }
    }

    LaunchedEffect(deIncrementInteractionSource) {
        var isLongClick = false
        deIncrementInteractionSource.interactions.collectLatest {
            when (it) {
                is PressInteraction.Press -> {
                    isLongClick = false
                    delay(viewConfiguration.longPressTimeoutMillis)
                    focusManager.clearFocus()
                    isLongClick = true
                    while (isLongClick && mgAmount > 0) {
                        mgAmount--
                        delay(100)
                    }
                }

                is PressInteraction.Release -> {
                    focusManager.clearFocus()
                    if (isLongClick.not()) {
                        if (mgAmount > 0) {
                            mgAmount--
                        }
                    }
                    isLongClick = false
                }

                is PressInteraction.Cancel -> {
                    isLongClick = false
                }
            }
        }
    }

    Dialog(
        onDismissRequest = {}
    ) {
        Card {
            Column(
                modifier = modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Text(
                    text = "Caffeine Intake",
                    style = TextStyle(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = modifier
                        .padding(top = 16.dp)
                        .align(Alignment.CenterHorizontally)
                )

                Column(
                    modifier = modifier.padding(top = 16.dp, bottom = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .height(56.dp)
//                        .border(width = 1.dp, color = Color.Black)
                    ) {
                        Box(
                            modifier = modifier
                                .weight(1f)
                                .fillMaxHeight(),
//                            .border(width = 1.dp, color = Color.Black),
                            contentAlignment = Alignment.Center
                        ) {
                            TextField(
                                value = itemName,
                                onValueChange = {
                                    itemName = it
                                },
                                placeholder = {
                                    Text(
                                        text = "Item Name"
                                    )
                                },
                                modifier = modifier
                                    .fillMaxWidth(),
                                keyboardOptions = KeyboardOptions(
                                    imeAction = ImeAction.Done
                                ),
                                keyboardActions = KeyboardActions(onDone = {
                                    focusManager.clearFocus()
                                }),
                                colors = TextFieldDefaults.colors(
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent
                                )
                            )
                        }
                        Row(
                            modifier = modifier
                                .width(140.dp)
                                .fillMaxHeight()
//                            .border(width = 1.dp, color = Color.Black)
                        ) {
                            Box(
                                modifier = modifier
                                    .weight(1f)
                                    .fillMaxHeight()
//                                .border(width = 1.dp, color = Color.Black)
                            ) {
                                Surface(
                                    onClick = {},
                                    interactionSource = deIncrementInteractionSource,
                                    content = {
                                        Box(
                                            modifier = modifier.fillMaxSize(),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = "-",
                                                fontSize = 22.sp,
                                                fontWeight = FontWeight.Bold,
                                            )
                                        }
                                    }
                                )
                            }
                            Box(
                                modifier = modifier
                                    .weight(1.5f)
                                    .fillMaxHeight(),
//                                .border(width = 1.dp, color = Color.Black),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "$mgAmount",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Box(
                                modifier = modifier
                                    .weight(1f)
                                    .fillMaxHeight()
//                                .border(width = 1.dp, color = Color.Black)
                            ) {
                                Surface(
                                    onClick = {},
                                    interactionSource = incrementInteractionSource,
                                    content = {
                                        Box(
                                            modifier = modifier.fillMaxSize(),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = "+",
                                                fontSize = 22.sp,
                                                fontWeight = FontWeight.Bold,
                                            )
                                        }
                                    }
                                )
                            }
                        }
                    }
                    Button(
                        onClick = {
                            if (mgAmount in 1..399) {
                                onEvent(
                                    IntakeEvent.INSERT(
                                        Intake(
                                            id = null,
                                            name = itemName,
                                            mg = mgAmount.toDouble(),
                                            date = currentDate(),
                                            time = currentTime()
                                        )
                                    )
                                )
                            }
                        },
                        modifier = modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text(
                            text = "Add Amount"
                        )
                    }
                }

            }
        }
    }
}

fun currentDate(): String {
    val currentDateTime = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault())
    val day = if (currentDateTime.dayOfMonth < 10)  "0${currentDateTime.dayOfMonth}" else "${currentDateTime.dayOfMonth}"
    val month = if (currentDateTime.monthNumber < 10)  "0${currentDateTime.monthNumber}" else "${currentDateTime.monthNumber}"
    return "$day/$month/${currentDateTime.year}"
}

fun currentTime(): Long {
    return Clock.System.now().toEpochMilliseconds()
}