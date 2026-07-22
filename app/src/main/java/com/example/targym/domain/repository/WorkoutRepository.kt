package com.example.targym.domain.repository

import com.example.targym.domain.model.Exercise
import com.example.targym.domain.model.MuscleGroup
import com.example.targym.domain.model.WorkoutDay
import kotlinx.coroutines.flow.Flow

interface WorkoutRepository {

    fun getWorkoutDays(): Flow<List<WorkoutDay>>
    suspend fun addWorkoutDay(name: String): Long
    suspend fun deleteWorkoutDay(workoutDayId: Long)
    suspend fun updateWorkoutDayName(id: Long, newName: String)

    fun getExercisesByWorkoutDay(workoutDayId: Long): Flow<List<Exercise>>
    suspend fun saveExercise(exercise: Exercise)
    suspend fun deleteExercise(exerciseId: Long)
    suspend fun deleteExercisesByMuscleGroup(workoutDayId: Long, muscleGroup: MuscleGroup)

    suspend fun toggleRepetitionDone(exerciseId: Long, repetitionId: Long)
    suspend fun resetAllDoneFlags(workoutDayId: Long)
}
