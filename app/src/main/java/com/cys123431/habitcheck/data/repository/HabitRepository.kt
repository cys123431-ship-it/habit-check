package com.cys123431.habitcheck.data.repository

import android.content.Context
import com.cys123431.habitcheck.data.local.dao.HabitDao
import com.cys123431.habitcheck.data.local.db.HabitDatabase
import com.cys123431.habitcheck.data.local.entity.Habit
import com.cys123431.habitcheck.data.local.entity.HabitCheckIn
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class HabitRepository(context: Context) {
    private val dao: HabitDao = HabitDatabase.getInstance(context).habitDao()
    private val dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE

    val habits: Flow<List<Habit>> = dao.getAllHabits()

    suspend fun addHabit(title: String, targetDays: Int) {
        val safeTitle = title.trim()
        if (safeTitle.isBlank()) return
        dao.insertHabit(Habit(title = safeTitle, targetDays = targetDays.coerceIn(1, 365)))
    }

    suspend fun deleteHabitById(habitId: Int) {
        dao.deleteHabitById(habitId)
        dao.deleteCheckInsForHabit(habitId)
    }

    suspend fun toggleDoneToday(habitId: Int, today: LocalDate = LocalDate.now()) {
        val date = today.format(dateFormatter)
        val existing = dao.getCheckIn(habitId, date)
        if (existing == null) {
            dao.insertCheckIn(HabitCheckIn(habitId = habitId, date = date))
        } else {
            dao.deleteCheckIn(existing)
        }
    }

    suspend fun getAllCheckIns(): List<HabitCheckIn> {
        return dao.getAllCheckIns()
    }
}

