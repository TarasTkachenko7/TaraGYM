package com.example.targym.presentation.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.targym.domain.model.MuscleGroup
import com.example.targym.presentation.main.ExerciseUiModel
import com.example.targym.presentation.main.components.buttons.FinishWorkoutButton
import kotlin.collections.component1
import kotlin.collections.component2
import kotlin.collections.forEach

@Composable
fun ExercisesList(
    lazyListState: LazyListState,
    selectedDayId: Long,
    groupedExercises: Map<MuscleGroup, List<ExerciseUiModel>>,
    hasActiveWorkout: Boolean,
    onRepetitionClick: (Long, Long) -> Unit,
    onMuscleMenu: () -> Unit,
    onVideoClick: (Long) -> Unit,
    onEditClick: (Long) -> Unit,
    onFinishWorkoutClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        state = lazyListState,
        modifier = modifier,
        contentPadding = PaddingValues(bottom = 120.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        groupedExercises.forEach { (muscleGroup, exercisesForGroup) ->
            item(key = "group_${selectedDayId}_${muscleGroup.name}") {
                MuscleGroupSection(
                    muscleGroup = muscleGroup,
                    exercises = exercisesForGroup,
                    onRepetitionClick = onRepetitionClick,
                    onMuscleMenu = onMuscleMenu,
                    onVideoClick = onVideoClick,
                    onEditClick = onEditClick
                )
            }
        }

        if (hasActiveWorkout) {
            item(key = "finish_workout_button") {
                Spacer(modifier = Modifier.height(12.dp))
                FinishWorkoutButton(
                    onClick = { onFinishWorkoutClick(selectedDayId) }
                )
            }
        }
    }
}