package com.example.targym.data.impl

import com.example.targym.data.Storage
import com.example.targym.data.util.IdGenerator
import com.example.targym.domain.model.Exercise
import com.example.targym.domain.model.MuscleGroup
import com.example.targym.domain.model.WorkoutDay
import com.example.targym.domain.repository.WorkoutDayRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlin.collections.copy
import kotlin.collections.filter
import kotlin.collections.filterNot
import kotlin.collections.map
import kotlin.plus

class WorkoutDayRepositoryImpl : WorkoutDayRepository {
    private val _days = MutableStateFlow<List<WorkoutDay>>(Storage.mockDays)

    override fun getWorkoutDays(): Flow<List<WorkoutDay>> {
        return _days.asStateFlow()
    }

    override suspend fun addWorkoutDay(name: String): Long {
        val newId = IdGenerator.generateId()
        _days.update { allDays ->
            allDays + WorkoutDay(newId, name)
        }
        return newId
    }

    override suspend fun deleteWorkoutDay(workoutDayId: Long) {
        _days.update { allDays ->
            allDays.filterNot { it.id == workoutDayId }
        }
    }

    override suspend fun updateWorkoutDayName(id: Long, newName: String) {
        _days.update { allDays ->
            allDays.map { day ->
                if (day.id == id) day.copy(name = newName) else day
            }
        }
    }
}