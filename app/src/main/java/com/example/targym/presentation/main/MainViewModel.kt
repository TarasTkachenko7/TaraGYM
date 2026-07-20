package com.example.targym.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.targym.domain.model.MuscleGroup
import com.example.targym.domain.model.WorkoutDay
import com.example.targym.domain.repository.WorkoutRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.collections.first

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModel(
    private val repository: WorkoutRepository
) : ViewModel() {
    private val _selectedDayId = MutableStateFlow<Long?>(null)

    private val _isMuscleBottomSheetOpen = MutableStateFlow(false)
    private val _manuallyAddedMuscleGroups = MutableStateFlow<Set<MuscleGroup>>(emptySet())
    private val _activeMuscleMenuGroup = MutableStateFlow<MuscleGroup?>(null)

    val screenState: StateFlow<MainScreenState> = combine(
        repository.getWorkoutDays(),
        _selectedDayId,
        _isMuscleBottomSheetOpen,
        _manuallyAddedMuscleGroups,
        _activeMuscleMenuGroup
    ) { days, selectedId, isSheetOpen, manualGroups, activeMenu ->
        listOf(days, selectedId, isSheetOpen, manualGroups, activeMenu)
    }.flatMapLatest { targetList ->
        val days = targetList[0] as List<WorkoutDay>
        val selectedId = targetList[1] as Long?
        val isSheetOpen = targetList[2] as Boolean
        val manualGroups = targetList[3] as Set<MuscleGroup>
        val activeMenu = targetList[4] as MuscleGroup?

        if (days.isEmpty()) {
            flowOf(MainScreenState.Empty)
        } else {
            val activeDayId = selectedId?.takeIf { id -> days.any { it.id == id } }
                ?: days.first().id

            repository.getExercisesByWorkoutDay(activeDayId).map { domainExercises ->
                val hasActiveWorkout = domainExercises.any { exercise ->
                    exercise.repetitions.any { it.isDone }
                }

                val uiExercises = domainExercises.map { exercise ->
                    ExerciseUiModel(
                        id = exercise.id,
                        name = exercise.name,
                        note = exercise.note,
                        muscleGroup = exercise.muscleGroup,
                        repetitions = exercise.repetitions.mapIndexed { index, rep ->
                            RepetitionUiModel(
                                id = rep.id,
                                indexText = "${index + 1}.",
                                weightText = formatWeight(rep.weight),
                                quantityText = rep.quantity.toString(),
                                isDone = rep.isDone
                            )
                        }
                    )
                }

                val baseGrouped = uiExercises.groupBy { it.muscleGroup }

                val mutableGrouped = baseGrouped.toMutableMap()

                manualGroups.forEach { manualGroup ->
                    if (manualGroup !in mutableGrouped) {
                        mutableGrouped[manualGroup] = emptyList()
                    }
                }

                val availableGroups = MuscleGroup.entries.filter { it !in mutableGrouped.keys }

                MainScreenState.Success(
                    uiState = MainUiState(
                        workoutDays = days,
                        selectedDayId = activeDayId,
                        groupedExercises = mutableGrouped,
                        isExercisesLoading = false,
                        hasActiveWorkout = hasActiveWorkout,
                        isMuscleBottomSheetOpen = isSheetOpen,
                        availableMuscleGroups = availableGroups,
                        activeMuscleMenuGroup = activeMenu
                    )
                )
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = MainScreenState.Loading
    )

    private fun formatWeight(weight: Double): String {
        return if (weight % 1 == 0.0) {
            weight.toInt().toString()
        } else {
            weight.toString()
        }
    }

    fun selectDay(dayId: Long) {
        _manuallyAddedMuscleGroups.value = emptySet()
        _selectedDayId.value = dayId
    }

    fun openMuscleBottomSheet() {
        _isMuscleBottomSheetOpen.value = true
    }

    fun closeMuscleBottomSheet() {
        _isMuscleBottomSheetOpen.value = false
    }

    fun addMuscleGroup(muscleGroup: MuscleGroup) {
        _manuallyAddedMuscleGroups.update { it + muscleGroup }
        closeMuscleBottomSheet()
    }

    fun openMuscleMenu(muscleGroup: MuscleGroup) {
        _activeMuscleMenuGroup.value = muscleGroup
    }

    fun closeMuscleMenu() {
        _activeMuscleMenuGroup.value = null
    }

    fun removeMuscleGroup(muscleGroup: MuscleGroup) {
        _manuallyAddedMuscleGroups.update { it - muscleGroup }
        closeMuscleMenu()
        // TODO: В будущем добавить логику удаления упражнений этой группы из репозитория
    }

    fun toggleRepetitionCheck(exerciseId: Long, repetitionId: Long) {
        viewModelScope.launch {
            repository.toggleRepetitionDone(exerciseId, repetitionId)
        }
    }

    fun finishCurrentWorkout(workoutDayId: Long) {
        viewModelScope.launch {
            repository.resetAllDoneFlags(workoutDayId)
        }
    }

}