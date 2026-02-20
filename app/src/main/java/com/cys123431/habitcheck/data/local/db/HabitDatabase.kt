package com.cys123431.habitcheck.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cys123431.habitcheck.data.local.dao.HabitDao
import com.cys123431.habitcheck.data.local.entity.Habit
import com.cys123431.habitcheck.data.local.entity.HabitCheckIn

@Database(
    entities = [Habit::class, HabitCheckIn::class],
    version = 1,
    exportSchema = false
)
abstract class HabitDatabase : RoomDatabase() {
    abstract fun habitDao(): HabitDao

    companion object {
        private const val DATABASE_NAME = "habit_check_db"
        @Volatile
        private var INSTANCE: HabitDatabase? = null

        fun getInstance(context: Context): HabitDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HabitDatabase::class.java,
                    DATABASE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

