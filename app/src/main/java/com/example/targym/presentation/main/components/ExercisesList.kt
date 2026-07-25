package com.example.targym.presentation.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.targym.domain.model.MuscleGroup
import com.example.targym.presentation.main.components.buttons.FinishWorkoutButton
import com.example.targym.presentation.main.state.MainUiAction
import com.example.targym.presentation.model.ExerciseUiModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.ImmutableMap

@Composable
fun ExercisesList(
    lazyListState: LazyListState,
    selectedDayId: Long,
    groupedExercises: ImmutableMap<MuscleGroup, ImmutableList<ExerciseUiModel>>,
    activeMuscleMenuGroup: MuscleGroup?,
    hasActiveWorkout: Boolean,
    onAction: (MainUiAction) -> Unit,
    modifier: Modifier = Modifier
) {
    val groupedEntries = remember(groupedExercises) { groupedExercises.entries.toList() }

    LazyColumn(
        state = lazyListState,
        modifier = modifier,
        contentPadding = PaddingValues(bottom = 120.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(
            items = groupedEntries,
            key = { (muscleGroup, _) -> "group_${selectedDayId}_${muscleGroup.name}" }
        ) { (muscleGroup, exercisesForGroup) ->
            MuscleGroupSection(
                selectedDayId = selectedDayId,
                muscleGroup = muscleGroup,
                exercises = exercisesForGroup,
                isMenuExpanded = activeMuscleMenuGroup == muscleGroup,
                onAction = onAction
            )
        }

        if (hasActiveWorkout) {
            item(key = "finish_workout_button") {
                Spacer(modifier = Modifier.height(12.dp))
                FinishWorkoutButton(
                    onClick = { onAction(MainUiAction.FinishWorkout(selectedDayId)) }
                )
            }
        }
    }
}