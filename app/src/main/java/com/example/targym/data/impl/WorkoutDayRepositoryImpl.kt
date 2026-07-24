package com.example.targym.data.impl

import com.example.targym.data.Storage
import com.example.targym.domain.model.WorkoutDay
import com.example.targym.domain.repository.WorkoutDayRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class WorkoutDayRepositoryImpl : WorkoutDayRepository {
    private val _days = MutableStateFlow<List<WorkoutDay>>(Storage.mockDays)

    override fun getWorkoutDays(): Flow<List<WorkoutDay>> = _days

    override suspend fun addWorkoutDay(name: String): Long {
        val newId = System.currentTimeMillis()
        _days.update { allDays ->
            if (allDays.any { it.name == name }) allDays
            else allDays + WorkoutDay(newId, name)
        }
        return newId
    }

    override suspend fun deleteWorkoutDay(workoutDayId: Long) {
        _days.update { allDays -> allDays.filter { it.id != workoutDayId } }
    }

    override suspend fun updateWorkoutDayName(id: Long, newName: String) {
        _days.update { allDays ->
            allDays.map { if (it.id == id) it.copy(name = newName) else it }
        }
    }
}