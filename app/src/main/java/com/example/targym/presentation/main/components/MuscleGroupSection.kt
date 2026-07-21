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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.targym.domain.model.MuscleGroup
import com.example.targym.presentation.main.ExerciseUiModel
import com.example.targym.ui.theme.InterFont
import com.example.targym.ui.theme.Second
import com.example.targym.ui.theme.SecondText
import com.example.targym.R

@Composable
fun MuscleGroupSection(
    muscleGroup: MuscleGroup,
    exercises: List<ExerciseUiModel>,
    isMenuExpanded: Boolean,
    onMenuToggle: (Boolean) -> Unit,
    onAddExerciseClick: () -> Unit,
    onDeleteGroupClick: () -> Unit,
    onRepetitionClick: (Long, Long) -> Unit,
    onVideoClick: (Long) -> Unit,
    onEditClick: (Long, MuscleGroup) -> Unit,
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
            onMenuToggle = onMenuToggle,
            onAddExerciseClick = onAddExerciseClick,
            onDeleteGroupClick = onDeleteGroupClick
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
                    style = TextStyle(
                        fontFamily = InterFont,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        color = SecondText.copy(alpha = 0.7f),
                        textAlign = TextAlign.Center
                    )
                )
            }
        } else {
            exercises.forEach { exercise ->
                key(exercise.id) {
                    ExerciseCard(
                        exercise = exercise,
                        onRepetitionClick = onRepetitionClick,
                        onVideoClick = onVideoClick,
                        onEditClick = onEditClick
                    )
                }
            }
        }
    }
}