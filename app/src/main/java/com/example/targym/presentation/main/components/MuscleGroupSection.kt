package com.example.targym.presentation.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.targym.R
import com.example.targym.domain.model.MuscleGroup
import com.example.targym.presentation.main.state.MainUiAction
import com.example.targym.presentation.mapper.titleRes
import com.example.targym.presentation.model.ExerciseUiModel
import com.example.targym.ui.theme.HintTextStyle
import com.example.targym.ui.theme.Second
import kotlinx.collections.immutable.ImmutableList

@Composable
fun MuscleGroupSection(
    selectedDayId: Long,
    muscleGroup: MuscleGroup,
    exercises: ImmutableList<ExerciseUiModel>,
    isMenuExpanded: Boolean,
    onAction: (MainUiAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Second)
            .padding(vertical = 16.dp, horizontal = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        MuscleGroupSectionHeader(
            title = stringResource(muscleGroup.titleRes),
            isMenuExpanded = isMenuExpanded,
            onMenuToggle = { open -> onAction(MainUiAction.ToggleMuscleMenu(muscleGroup, open)) },
            onAddExerciseClick = { onAction(MainUiAction.AddExercise(selectedDayId, muscleGroup)) },
            onDeleteGroupClick = { onAction(MainUiAction.RequestDeleteMuscleGroup(muscleGroup)) }
        )

        if (exercises.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.add_first_exercise),
                    style = HintTextStyle
                )
            }
        } else {
            exercises.forEach { exercise ->
                key(exercise.id) {
                    ExerciseCard(
                        exercise = exercise,
                        onAction = onAction,
                        selectedDayId = selectedDayId
                    )
                }
            }
        }
    }
}