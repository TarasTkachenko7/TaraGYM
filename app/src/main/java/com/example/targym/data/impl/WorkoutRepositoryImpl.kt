package com.example.targym.data.impl

import com.example.targym.data.Storage
import com.example.targym.domain.model.Exercise
import com.example.targym.domain.model.MuscleGroup
import com.example.targym.domain.model.WorkoutDay
import com.example.targym.domain.repository.WorkoutRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class WorkoutRepositoryImpl : WorkoutRepository {

    private val _days = MutableStateFlow<List<WorkoutDay>>(Storage.mockDays)
    private val _exercises = MutableStateFlow<List<Exercise>>(Storage.mockExercises)

    override fun getWorkoutDays(): Flow<List<WorkoutDay>> {
        return _days
    }

    override suspend fun addWorkoutDay(name: String): Long {
        val newId = System.currentTimeMillis()
        _days.update { allDays ->
            val exists = allDays.any { it.name == name }
            if (exists) {
                allDays
            } else {
                allDays + WorkoutDay(newId, name)
            }
        }
        return newId
    }

    override suspend fun deleteWorkoutDay(workoutDayId: Long) {
        _days.update { allDays ->
            allDays.filter { day ->
                day.id != workoutDayId
            }
        }

        _exercises.update { allExercises ->
            allExercises.filter { exercise ->
                exercise.workoutDayId != workoutDayId
            }
        }
    }

    override suspend fun updateWorkoutDayName(id: Long, newName: String) {
        _days.update { allDays ->
            allDays.map { item ->
                if (item.id == id) {
                    item.copy(name = newName)
                } else {
                    item
                }
            }
        }
    }

    override fun getExercisesByWorkoutDay(workoutDayId: Long): Flow<List<Exercise>> {
        return _exercises.map { allExercises -> allExercises.filter { it.workoutDayId ==  workoutDayId} }
    }

    override suspend fun saveExercise(exercise: Exercise) {
        _exercises.update { allExercises ->
            val exists = allExercises.any { it.id == exercise.id }
            if (exists) {
                allExercises.map {
                    if (it.id == exercise.id) exercise else it
                }
            } else {
                val newId = System.currentTimeMillis()
                val newExercise = exercise.copy(
                    id = newId,
                    repetitions = exercise.repetitions.map { repetition ->
                        repetition.copy(exerciseId = newId)
                    }
                )
                allExercises + newExercise
            }
        }
    }

    override suspend fun deleteExercise(exerciseId: Long) {
        _exercises.update { allExercises ->
            allExercises.filter { it.id != exerciseId }
        }
    }

    override suspend fun deleteExercisesByMuscleGroup(workoutDayId: Long, muscleGroup: MuscleGroup) {
        _exercises.update { allExercises ->
            allExercises.filterNot { it.workoutDayId == workoutDayId && it.muscleGroup == muscleGroup }
        }
    }

    override suspend fun resetAllDoneFlags(workoutDayId: Long) {
        _exercises.update { allExercises ->
            allExercises.map { exercise ->
                if (exercise.workoutDayId == workoutDayId) {
                    exercise.copy(
                        repetitions = exercise.repetitions.map { repetition ->
                            repetition.copy(isDone = false)
                        }
                    )
                } else {
                    exercise
                }
            }
        }
    }

    override suspend fun toggleRepetitionDone(exerciseId: Long, repetitionId: Long) {
        _exercises.update { allExercises ->
            allExercises.map { exercise ->
                if (exercise.id == exerciseId) {
                    exercise.copy(
                        repetitions = exercise.repetitions.map { repetition ->
                            if (repetition.id == repetitionId) {
                                repetition.copy (
                                    isDone = !repetition.isDone
                                )
                            } else {
                                repetition
                            }
                        }
                    )
                } else {
                    exercise
                }
            }
        }
    }

}