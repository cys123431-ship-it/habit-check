package com.cys123431.habitcheck.data.util

import com.cys123431.habitcheck.data.local.entity.HabitCheckIn
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object HabitStats {
    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE

    private fun toDateSet(checkIns: List<HabitCheckIn>): Set<LocalDate> {
        return checkIns.mapNotNull { entry ->
            runCatching { LocalDate.parse(entry.date, formatter) }.getOrNull()
        }.toSet()
    }

    fun isCheckedToday(checkIns: List<HabitCheckIn>, today: LocalDate = LocalDate.now()): Boolean {
        return checkIns.any { it.date == today.format(formatter) }
    }

    fun currentStreak(checkIns: List<HabitCheckIn>, today: LocalDate = LocalDate.now()): Int {
        val doneDays = toDateSet(checkIns)
        var cursor = today
        var streak = 0
        while (doneDays.contains(cursor)) {
            streak += 1
            cursor = cursor.minusDays(1)
        }
        return streak
    }

    fun weeklyRate(checkIns: List<HabitCheckIn>, today: LocalDate = LocalDate.now()): Int {
        val doneDays = toDateSet(checkIns)
        var count = 0
        repeat(7) { offset ->
            val day = today.minusDays(offset.toLong())
            if (doneDays.contains(day)) {
                count++
            }
        }
        return (count * 100) / 7
    }
}

