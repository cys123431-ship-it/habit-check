package com.cys123431.habitcheck.data.local.entity

import androidx.room.Entity

@Entity(
    tableName = "habit_checkins",
    primaryKeys = ["habitId", "date"]
)
data class HabitCheckIn(
    val habitId: Int,
    val date: String
)

