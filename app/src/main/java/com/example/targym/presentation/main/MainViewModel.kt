package com.example.targym.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.targym.domain.model.Exercise
import com.example.targym.domain.model.MuscleGroup
import com.example.targym.domain.model.WorkoutDay
import com.example.targym.domain.usecase.exercise.DeleteExercisesByMuscleGroupUseCase
import com.example.targym.domain.usecase.exercise.GetExercisesByDayUseCase
import com.example.targym.domain.usecase.workout.FinishWorkoutUseCase
import com.example.targym.domain.usecase.workout.ToggleRepetitionDoneUseCase
import com.example.targym.domain.usecase.workoutday.GetWorkoutDaysUseCase
import com.example.targym.presentation.main.state.MainLocalState
import com.example.targym.presentation.main.state.MainScreenState
import com.example.targym.presentation.main.state.MainUiAction
import com.example.targym.presentation.main.state.MainUiState
import com.example.targym.presentation.model.ExerciseUiModel
import com.example.targym.presentation.model.RepetitionUiModel
import com.example.targym.presentation.model.WorkoutDayUiModel
import com.example.targym.presentation.util.formatWeight
import kotlinx.collections.immutable.toImmutableList
import kotlinx.collections.immutable.toImmutableMap
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

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModel(
    getWorkoutDaysUseCase: GetWorkoutDaysUseCase,
    private val getExercisesByDayUseCase: GetExercisesByDayUseCase,
    private val toggleRepetitionDoneUseCase: ToggleRepetitionDoneUseCase,
    private val finishWorkoutUseCase: FinishWorkoutUseCase,
    private val deleteExercisesByMuscleGroupUseCase: DeleteExercisesByMuscleGroupUseCase
) : ViewModel() {

    private val _localState = MutableStateFlow(MainLocalState())

    private data class ScreenFlowParams(
        val days: List<WorkoutDay>,
        val local: MainLocalState
    )

    val screenState: StateFlow<MainScreenState> = combine(
        getWorkoutDaysUseCase(),
        _localState
    ) { days, local ->
        ScreenFlowParams(days, local)
    }.flatMapLatest { (days, local) ->
        if (days.isEmpty()) {
            flowOf(MainScreenState.Empty)
        } else {
            val activeDayId = local.selectedDayId?.takeIf { id -> days.any { it.id == id } }
                ?: days.first().id

            getExercisesByDayUseCase(activeDayId).map { domainExercises ->
                val uiState = buildMainUiState(
                    days = days,
                    activeDayId = activeDayId,
                    domainExercises = domainExercises,
                    local = local
                )
                MainScreenState.Success(uiState)
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = MainScreenState.Loading
    )

    fun onAction(action: MainUiAction) {
        when (action) {
            is MainUiAction.SelectDay -> {
                _localState.update {
                    it.copy(selectedDayId = action.dayId)
                }
            }
            is MainUiAction.ToggleRepetition -> toggleRepetition(action.exerciseId, action.repetitionId)
            is MainUiAction.FinishWorkout -> finishWorkout(action.workoutDayId)

            is MainUiAction.OpenMuscleBottomSheet -> _localState.update { it.copy(isMuscleBottomSheetOpen = true) }
            is MainUiAction.CloseMuscleBottomSheet -> _localState.update { it.copy(isMuscleBottomSheetOpen = false) }

            is MainUiAction.AddMuscleGroup -> {
                val currentDayId = (screenState.value as? MainScreenState.Success)?.uiState?.selectedDayId ?: return
                _localState.update { state ->
                    val currentDayGroups = state.manuallyAddedMuscleGroups[currentDayId] ?: emptySet()
                    val updatedMap = state.manuallyAddedMuscleGroups + (currentDayId to (currentDayGroups + action.muscleGroup))
                    state.copy(
                        manuallyAddedMuscleGroups = updatedMap,
                        isMuscleBottomSheetOpen = false
                    )
                }
            }
            is MainUiAction.ToggleMuscleMenu -> {
                _localState.update {
                    it.copy(activeMuscleMenuGroup = if (action.isOpen) action.muscleGroup else null)
                }
            }
            is MainUiAction.RequestDeleteMuscleGroup -> {
                _localState.update {
                    it.copy(activeMuscleMenuGroup = null, muscleGroupPendingDeletion = action.muscleGroup)
                }
            }
            is MainUiAction.ConfirmDeleteMuscleGroup -> confirmDeleteMuscleGroup()

            is MainUiAction.DismissDeleteMuscleGroupDialog -> {
                _localState.update { it.copy(muscleGroupPendingDeletion = null) }
            }

            is MainUiAction.OpenManageDays,
            is MainUiAction.AddExercise,
            is MainUiAction.OpenEditExercise,
            is MainUiAction.OpenVideo -> {  }
        }
    }

    private fun confirmDeleteMuscleGroup() {
        val groupToDelete = _localState.value.muscleGroupPendingDeletion ?: return
        val currentSuccessState = screenState.value as? MainScreenState.Success ?: return
        val activeDayId = currentSuccessState.uiState.selectedDayId

        viewModelScope.launch {
            deleteExercisesByMuscleGroupUseCase(activeDayId, groupToDelete)

            _localState.update { state ->
                val currentDayGroups = state.manuallyAddedMuscleGroups[activeDayId] ?: emptySet()
                val updatedDayGroups = currentDayGroups - groupToDelete
                val updatedMap = if (updatedDayGroups.isEmpty()) {
                    state.manuallyAddedMuscleGroups - activeDayId
                } else {
                    state.manuallyAddedMuscleGroups + (activeDayId to updatedDayGroups)
                }

                state.copy(
                    manuallyAddedMuscleGroups = updatedMap,
                    muscleGroupPendingDeletion = null
                )
            }
        }
    }

    private fun toggleRepetition(exerciseId: Long, repetitionId: Long) {
        viewModelScope.launch {
            toggleRepetitionDoneUseCase(exerciseId, repetitionId)
        }
    }

    private fun finishWorkout(workoutDayId: Long) {
        viewModelScope.launch {
            finishWorkoutUseCase(workoutDayId)
        }
    }

    private fun buildMainUiState(
        days: List<WorkoutDay>,
        activeDayId: Long,
        domainExercises: List<Exercise>,
        local: MainLocalState
    ): MainUiState {
        val daysUi = days.map { day ->
            WorkoutDayUiModel(
                id = day.id,
                name = day.name,
                isSelected = day.id == activeDayId
            )
        }.toImmutableList()

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
                        weightText = rep.weight.formatWeight(),
                        quantityText = rep.quantity.toString(),
                        isDone = rep.isDone
                    )
                }.toImmutableList()
            )
        }

        val rawGroupedMap = uiExercises.groupBy { it.muscleGroup }.toMutableMap()

        val manualGroupsForActiveDay = local.manuallyAddedMuscleGroups[activeDayId] ?: emptySet()

        manualGroupsForActiveDay.forEach { manualGroup ->
            if (manualGroup !in rawGroupedMap) {
                rawGroupedMap[manualGroup] = emptyList()
            }
        }

        val immutableGroupedMap = rawGroupedMap.mapValues { (_, list) ->
            list.toImmutableList()
        }.toImmutableMap()

        val availableGroups = MuscleGroup.entries
            .filter { it !in rawGroupedMap.keys }
            .toImmutableList()

        return MainUiState(
            workoutDays = daysUi,
            selectedDayId = activeDayId,
            groupedExercises = immutableGroupedMap,
            hasActiveWorkout = hasActiveWorkout,
            isMuscleBottomSheetOpen = local.isMuscleBottomSheetOpen,
            availableMuscleGroups = availableGroups,
            activeMuscleMenuGroup = local.activeMuscleMenuGroup,
            muscleGroupPendingDeletion = local.muscleGroupPendingDeletion
        )
    }
}