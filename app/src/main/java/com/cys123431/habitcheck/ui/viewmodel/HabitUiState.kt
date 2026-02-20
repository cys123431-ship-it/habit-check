package com.cys123431.habitcheck.ui.viewmodel

data class HabitUiState(
    val isLoading: Boolean = true,
    val items: List<HabitListItem> = emptyList()
)

data class HabitListItem(
    val id: Int,
    val title: String,
    val targetDays: Int,
    val checkedToday: Boolean,
    val streak: Int,
    val weeklyRate: Int
)

