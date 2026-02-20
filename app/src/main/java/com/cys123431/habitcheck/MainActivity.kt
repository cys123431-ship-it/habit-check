package com.cys123431.habitcheck

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.InsertChart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cys123431.habitcheck.ui.AddHabitDialog
import com.cys123431.habitcheck.ui.TodayHabitsScreen
import com.cys123431.habitcheck.ui.StatsScreen
import com.cys123431.habitcheck.ui.theme.HabitCheckTheme
import com.cys123431.habitcheck.ui.viewmodel.HabitViewModel
import com.cys123431.habitcheck.R

private enum class Tab(val titleRes: Int) {
    Today(R.string.habit_tab_title),
    Stats(R.string.stats_tab_title)
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HabitCheckTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HabitCheckApp()
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HabitCheckApp() {
    val viewModel: HabitViewModel = viewModel()
    val state by viewModel.uiState.collectAsState()
    var currentTab by remember { mutableStateOf(Tab.Today) }
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(
                        if (currentTab == Tab.Today) {
                            stringResource(id = R.string.today_title)
                        } else {
                            stringResource(id = R.string.overall)
                        }
                    )
                }
            )
        },
        floatingActionButton = {
            if (currentTab == Tab.Today) {
                FloatingActionButton(onClick = { showDialog = true }) {
                    Icon(Icons.Default.Add, contentDescription = "Add habit")
                }
            }
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = currentTab == Tab.Today,
                    onClick = { currentTab = Tab.Today },
                    label = { Text(stringResource(id = Tab.Today.titleRes)) },
                    icon = { Icon(Icons.Default.List, contentDescription = stringResource(id = Tab.Today.titleRes)) }
                )
                NavigationBarItem(
                    selected = currentTab == Tab.Stats,
                    onClick = { currentTab = Tab.Stats },
                    label = { Text(stringResource(id = Tab.Stats.titleRes)) },
                    icon = { Icon(Icons.Default.InsertChart, contentDescription = stringResource(id = Tab.Stats.titleRes)) }
                )
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
            when (currentTab) {
                Tab.Today -> TodayHabitsScreen(
                    state = state,
                    onToggleDone = { id -> viewModel.toggleDone(id) },
                    onDeleteHabit = { id -> viewModel.deleteHabitById(id) }
                )
                Tab.Stats -> StatsScreen(state = state)
            }
        }
    }

    if (showDialog) {
        AddHabitDialog(
            onDismiss = { showDialog = false },
            onSave = { title, targetDays ->
                viewModel.addHabit(title = title, targetDays = targetDays)
                showDialog = false
            }
        )
    }
}
