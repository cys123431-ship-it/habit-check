package com.cys123431.habitcheck.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.cys123431.habitcheck.data.local.entity.Habit
import com.cys123431.habitcheck.data.repository.HabitRepository
import com.cys123431.habitcheck.data.util.HabitStats
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDate

class HabitViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = HabitRepository(application)
    private val _uiState = MutableStateFlow(HabitUiState())
    val uiState: StateFlow<HabitUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.habits
                .catch { _uiState.value = HabitUiState(isLoading = false, items = emptyList()) }
                .collectLatest { habits ->
                    val allCheckIns = repository.getAllCheckIns()
                    val today = LocalDate.now()
                    val rows = habits.map { habit ->
                        val checks = allCheckIns.filter { it.habitId == habit.id }
                        HabitListItem(
                            id = habit.id,
                            title = habit.title,
                            targetDays = habit.targetDays,
                            checkedToday = HabitStats.isCheckedToday(checks, today),
                            streak = HabitStats.currentStreak(checks, today),
                            weeklyRate = HabitStats.weeklyRate(checks, today)
                        )
                    }
                    _uiState.value = HabitUiState(isLoading = false, items = rows)
                }
        }
    }

    fun addHabit(title: String, targetDays: Int) {
        viewModelScope.launch {
            repository.addHabit(title = title, targetDays = targetDays)
        }
    }

    fun toggleDone(habitId: Int) {
        viewModelScope.launch {
            repository.toggleDoneToday(habitId = habitId)
        }
    }

    fun deleteHabit(habit: Habit) {
        viewModelScope.launch {
            repository.deleteHabitById(habit.id)
        }
    }

    fun deleteHabitById(habitId: Int) {
        viewModelScope.launch {
            repository.deleteHabitById(habitId)
        }
    }
}

