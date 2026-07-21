package com.example.targym.presentation.edit

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.example.targym.domain.model.MuscleGroup

@Immutable
data class RepetitionInputState(
    val id: Long,
    val weight: String,
    val quantity: String
)

@Immutable
data class EditUiState(
    val exerciseId: Long = -1L,
    val dayId: Long = -1L,
    val name: String = "",
    val note: String = "",
    val muscleGroup: MuscleGroup? = null,
    val repetitions: List<RepetitionInputState> = emptyList(),
    val isLoading: Boolean = true,
    val isSaved: Boolean = false,
    val isNameError: Boolean = false,
    val isMuscleGroupError: Boolean = false,
    @StringRes val errorMessage: Int? = null,
    val isMenuExpanded: Boolean = false,
    val isRenameDialogOpen: Boolean = false,
    val tempNameInput: String = ""
)