package com.example.targym.domain.repository

import com.example.targym.domain.model.Advice
import com.example.targym.domain.model.Exercise
import com.example.targym.domain.model.WorkoutDay
import kotlinx.coroutines.flow.Flow

interface WorkoutRepository {

    fun getWorkoutDays(): Flow<List<WorkoutDay>>
    suspend fun addWorkoutDay(name: String)
    suspend fun deleteWorkoutDay(workoutDayId: Long)

    fun getExercisesByWorkoutDay(workoutId: Long): Flow<List<Exercise>>
    suspend fun saveExercise(exercise: Exercise)
    suspend fun deleteExercise(exerciseId: Long)

    suspend fun toggleRepetitionDone(exerciseId: Long, repetitionId: Long)
    suspend fun resetAllDoneFlags(dayId: Long)

    fun getAdvicesForExercise(exerciseId: Long): Flow<List<Advice>>
    suspend fun addAdvice(advice: Advice)
    suspend fun deleteAdvice(adviceId: Long)
}
