package com.example.targym.domain.repository

import com.example.targym.domain.model.WorkoutDay
import kotlinx.coroutines.flow.Flow

interface WorkoutDayRepository {
    fun getWorkoutDays(): Flow<List<WorkoutDay>>
    suspend fun addWorkoutDay(name: String): Long
    suspend fun deleteWorkoutDay(workoutDayId: Long)
    suspend fun updateWorkoutDayName(id: Long, newName: String)
}