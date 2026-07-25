package com.example.targym.presentation.edit.state

import androidx.compose.runtime.Immutable

@Immutable
sealed interface EditUiAction {
    // Навигация и меню
    data object NavigateBack : EditUiAction
    data class ToggleMenu(val isOpen: Boolean) : EditUiAction

    // Ввод полей
    data class NameChanged(val name: String) : EditUiAction
    data class NoteChanged(val note: String) : EditUiAction

    // Подходы (repetitions)
    data class RepetitionChanged(val repId: Long, val weight: String, val reps: String) : EditUiAction
    data object AddRepetition : EditUiAction
    data class RemoveRepetition(val repId: Long) : EditUiAction

    // Диалог переименования
    data object OpenRenameDialog : EditUiAction
    data object CloseRenameDialog : EditUiAction
    data class TempNameChanged(val name: String) : EditUiAction
    data object ConfirmRename : EditUiAction

    // Диалог удаления
    data object OpenDeleteConfirmation : EditUiAction
    data object CloseDeleteConfirmation : EditUiAction
    data object ConfirmDelete : EditUiAction

    // Диалог несохраненных изменений
    data object DismissExitDialog : EditUiAction

    // Сохранение
    data object SaveExercise : EditUiAction
}