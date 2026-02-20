package com.cys123431.habitcheck.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cys123431.habitcheck.data.local.entity.Habit
import com.cys123431.habitcheck.data.local.entity.HabitCheckIn
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDao {

    @Query("SELECT * FROM habits ORDER BY createdAt DESC")
    fun getAllHabits(): Flow<List<Habit>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabit(habit: Habit): Long

    @Delete
    suspend fun deleteHabit(habit: Habit)

    @Query("DELETE FROM habits WHERE id = :habitId")
    suspend fun deleteHabitById(habitId: Int)

    @Query("SELECT * FROM habit_checkins WHERE habitId = :habitId AND date = :date LIMIT 1")
    suspend fun getCheckIn(habitId: Int, date: String): HabitCheckIn?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCheckIn(checkIn: HabitCheckIn)

    @Delete
    suspend fun deleteCheckIn(checkIn: HabitCheckIn)

    @Query("SELECT * FROM habit_checkins")
    suspend fun getAllCheckIns(): List<HabitCheckIn>

    @Query("SELECT * FROM habit_checkins WHERE habitId = :habitId")
    suspend fun getCheckInsForHabit(habitId: Int): List<HabitCheckIn>

    @Query("DELETE FROM habit_checkins WHERE habitId = :habitId")
    suspend fun deleteCheckInsForHabit(habitId: Int)
}

