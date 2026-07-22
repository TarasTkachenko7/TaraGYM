package com.example.targym.presentation.gif.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.targym.R
import com.example.targym.domain.model.Exercise
import com.example.targym.domain.model.MuscleGroup
import com.example.targym.domain.model.Repetition
import com.example.targym.ui.theme.Accent
import com.example.targym.ui.theme.Background
import com.example.targym.ui.theme.Black
import com.example.targym.ui.theme.Border
import com.example.targym.ui.theme.ButtonsTextStyle
import com.example.targym.ui.theme.FirstText
import com.example.targym.ui.theme.InterFont
import com.example.targym.ui.theme.Second
import com.example.targym.ui.theme.SecondText
import com.example.targym.ui.theme.TarGYMTheme

@Composable
fun GifSuccess(
    exercise: Exercise,
    onGotItClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(RoundedCornerShape(12.dp))
                .background(Second)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = exercise.name,
                style = TextStyle(
                    fontSize = 24.sp,
                    color = FirstText,
                    fontFamily = InterFont,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier.padding(top = 20.dp, bottom = 20.dp)
            )

            Box(
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(SecondText)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = onGotItClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Accent,
                    contentColor = Black
                ),
                elevation = null
            ) {
                Text(
                    text = stringResource(R.string.understand).uppercase(),
                    style = ButtonsTextStyle,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun GifSuccessPreview() {
    TarGYMTheme {
        GifSuccess(
            exercise = Exercise(
                id = 1L,
                workoutDayId = 1L,
                muscleGroup = MuscleGroup.BICEPS,
                name = "Тяга сидя",
                repetitions = listOf(
                    Repetition(
                        id = 1L,
                        exerciseId = 1L,
                        weight = 50.0,
                        quantity = 12,
                        isDone = false
                    )
                ),
                note = "Локти держим близко к корпусу"
            ),
            onGotItClick = {}
        )
    }
}