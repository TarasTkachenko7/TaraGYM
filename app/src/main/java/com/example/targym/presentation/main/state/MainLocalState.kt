package com.example.targym.presentation.main.state

import androidx.compose.runtime.Immutable
import com.example.targym.domain.model.MuscleGroup

@Immutable
data class MainLocalState(
    val selectedDayId: Long? = null,
    val isMuscleBottomSheetOpen: Boolean = false,
    val manuallyAddedMuscleGroups: Map<Long, Set<MuscleGroup>> = emptyMap(),
    val activeMuscleMenuGroup: MuscleGroup? = null,
    val muscleGroupPendingDeletion: MuscleGroup? = null
)