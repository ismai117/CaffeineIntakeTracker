package main.screens.statistics

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import calender.MonthlyDateLayout
import calender.WeeklyDateLayout
import calender.YearlyDateLayout
import charts.MonthlyChart
import charts.WeeklyChart
import charts.YearlyChart
import kotlinx.coroutines.launch
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.number
import kotlinx.datetime.plus
import kotlinx.datetime.until
import main.screens.input.domain.model.Intake
import main.screens.input.presentation.IntakeViewModel
import org.koin.compose.koinInject


enum class DatesType(val index: Int, val label: String) {
    WEEKLY(index = 0, label = "Weekly"),
    MONTHLY(index = 1, label = "Monthly"),
    YEARLY(index = 2, label = "Yearly"),
}

@Composable
fun StatisticsScreen(
    navigateBack: () -> Unit,
) {

    val intakeViewModel = koinInject<IntakeViewModel>()
    val intakes by intakeViewModel.caffeineIntake.collectAsState()

    StatisticsScreenContent(
        intakes = intakes,
        navigateBack = navigateBack
    )

}

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterial3WindowSizeClassApi::class,
    ExperimentalFoundationApi::class
)
@Composable
fun StatisticsScreenContent(
    modifier: Modifier = Modifier,
    intakes: List<Intake>,
    navigateBack: () -> Unit,
) {

    val windowSizeClass = calculateWindowSizeClass()
    val isCompacted = windowSizeClass.widthSizeClass != WindowWidthSizeClass.Compact

    val scope = rememberCoroutineScope()

    val pagerState = rememberPagerState(initialPage = 0) { 3 }
    var currentDatesType by remember { mutableStateOf(0) }

    var currentWeek by remember { mutableStateOf<List<LocalDate>>(emptyList()) }
    var currentMonth by remember { mutableStateOf<LocalDate?>(null) }
    var currentYear by remember { mutableStateOf<LocalDate?>(null) }

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
            modifier = if (isCompacted) {
                modifier
                    .statusBarsPadding()
                    .navigationBarsPadding()
//                    .border(width = 1.dp, color = Color.Red)
            } else {
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
                ) {}
            }

            if (!isCompacted) {
                Column(
                    modifier = modifier
                        .weight(1f)
                        .fillMaxSize()
                ) {

                    TabRow(
                        selectedTabIndex = currentDatesType,
                        modifier = modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {
                        DatesType.entries.forEach { type ->
                            Box(
                                modifier = modifier
                                    .weight(1f)
                                    .height(45.dp)
                                    .clickable {
                                        currentDatesType = type.index
                                        scope.launch {
                                            pagerState.animateScrollToPage(type.index)
                                        }
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = type.label,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }

                    when (currentDatesType) {
                        0 -> WeeklyDateLayout(currentWeekOnChange = { currentWeek = it })
                        1 -> MonthlyDateLayout(currentMonthOnChange = { currentMonth = it })
                        2 -> YearlyDateLayout(currentYearOnChange = { currentYear = it })
                    }

                    HorizontalPager(
                        state = pagerState,
                        modifier = modifier
                            .fillMaxWidth()
                            .weight(1f),
                        userScrollEnabled = false
                    ) { page ->
                        when (page) {
                            0 -> WeeklyChart(intakes, currentWeek)
                            1 -> MonthlyChart(intakes, currentMonth)
                            2 -> YearlyChart(intakes, currentYear)
                        }
                    }

                }
            } else {
                Box(
                    modifier = modifier
                        .weight(1f)
                        .fillMaxHeight()
                ) {
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


@Composable
fun MonthlyDate(
    modifier: Modifier = Modifier,

    ) {
    Row(
        modifier = modifier
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .height(40.dp)
//                                    .border(width = 1.dp, color = Color.Red)
    ) {
        Box(
            modifier = modifier
                .weight(1f),
//                                        .border(width = 1.dp, color = Color.Red),
            contentAlignment = Alignment.CenterStart
        ) {
            IconButton(
                onClick = {

                }
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = null
                )
            }
        }
        Box(
            modifier = modifier
                .weight(3f)
                .fillMaxHeight(),
//                                        .border(width = 1.dp, color = Color.Red),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "",
                fontWeight = FontWeight.Bold
            )
        }
        Box(
            modifier = modifier
                .weight(1f),
//                                        .border(width = 1.dp, color = Color.Red),
            contentAlignment = Alignment.CenterEnd
        ) {
            IconButton(
                onClick = {

                }
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null
                )
            }
        }
    }
}

