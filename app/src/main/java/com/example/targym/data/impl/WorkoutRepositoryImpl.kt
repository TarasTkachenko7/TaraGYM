package com.example.targym.data.impl

import com.example.targym.data.Storage
import com.example.targym.domain.model.Advice
import com.example.targym.domain.model.Exercise
import com.example.targym.domain.model.WorkoutDay
import com.example.targym.domain.repository.WorkoutRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class WorkoutRepositoryImpl : WorkoutRepository {

    private val _days = MutableStateFlow<List<WorkoutDay>>(Storage.mockDays)
    private val _exercises = MutableStateFlow<List<Exercise>>(Storage.mockExercises)
    private val _advices = MutableStateFlow<List<Advice>>(Storage.mockAdvice)

    override fun getWorkoutDays(): Flow<List<WorkoutDay>> {
        return _days
    }

    override suspend fun addWorkoutDay(name: String) {
        _days.update { allDays ->
            val exists = allDays.any { it.name == name }
            if (exists) {
                allDays
            } else {
                val newId = System.currentTimeMillis()
                val newDay = WorkoutDay(newId, name)
                allDays + newDay
            }
        }
    }

    override suspend fun deleteWorkoutDay(workoutDayId: Long) {
        val exerciseIdsToDelete = _exercises.value
            .filter { it.workoutDayId == workoutDayId }
            .map { it.id }

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

        _advices.update { allAdvices ->
            allAdvices.filter { advice ->
                advice.exerciseId !in exerciseIdsToDelete
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

        _advices.update { allAdvices ->
            allAdvices.filter { it.exerciseId != exerciseId }
        }
    }

    override fun getAdvicesForExercise(exerciseId: Long): Flow<List<Advice>> {
        return _advices.map { allAdvices -> allAdvices.filter { it.exerciseId == exerciseId } }
    }

    override suspend fun addAdvice(advice: Advice) {
        _advices.update { allAdvices ->
            val newId = System.currentTimeMillis()
            allAdvices + advice.copy(id = newId)
        }
    }

    override suspend fun deleteAdvice(adviceId: Long) {
        _advices.update { allAdvices ->
            allAdvices.filter { it.id != adviceId }
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