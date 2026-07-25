package com.example.targym.presentation.edit.state

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.example.targym.domain.model.MuscleGroup
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class EditUiState(
    val exerciseId: Long = -1L,
    val dayId: Long = -1L,
    val name: String = "",
    val note: String = "",
    val muscleGroup: MuscleGroup? = null,
    val repetitions: ImmutableList<RepetitionInputState> = persistentListOf(),
    val isLoading: Boolean = true,
    val isSaved: Boolean = false,
    val isSaveEnabled: Boolean = false,
    val isNewExercise: Boolean = false,
    val isMenuExpanded: Boolean = false,
    val showExitConfirmationDialog: Boolean = false,
    val isDeleteConfirmationOpen: Boolean = false,
    val renameDialog: RenameDialogState = RenameDialogState(),
    @StringRes val errorMessage: Int? = null
)