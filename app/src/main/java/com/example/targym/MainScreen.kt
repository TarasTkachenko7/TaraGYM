package com.example.targym

import android.R.attr.contentDescription
import android.R.attr.text
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.Radio
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.FlashOn
import androidx.compose.material.icons.outlined.Videocam
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.targym.domain.model.Exercise
import com.example.targym.domain.model.MuscleGroup
import com.example.targym.domain.model.Repetition
import com.example.targym.ui.theme.Accent
import com.example.targym.ui.theme.Background
import com.example.targym.ui.theme.Black
import com.example.targym.ui.theme.Border
import com.example.targym.ui.theme.FirstText
import com.example.targym.ui.theme.InterFont
import com.example.targym.ui.theme.Second
import com.example.targym.ui.theme.SecondText

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize().background(Background)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val list = listOf(MenuItems.BREAST, MenuItems.BACK, MenuItems.LEGS)
            val exercise = Exercise(
                id = 1,
                workoutDayId = 1,
                muscleGroup = MuscleGroup.CHEST,
                name = "Приседание со штангой",
                note = "Глубже",
                repetitions = listOf(
                    Repetition(1, 1, 100.0, 8, isDone = true),
                    Repetition(1, 1, 100.0, 8, isDone = true),
                    Repetition(1, 1, 105.0, 6, isDone = false)
                )
            )

            Spacer(modifier = Modifier.height(40.dp))
            Menu(list)
            Spacer(modifier = Modifier.height(40.dp))
            ExerciseTitle("Грудь")
            Spacer(modifier = Modifier.height(12.dp))
            ExerciseItem(exercise)
        }
    }
}

enum class MenuItems(@StringRes val title: Int) {
    BREAST(R.string.breast),
    BACK(R.string.back),
    LEGS(R.string.legs)
}

@Composable
fun Menu(
    items: List<MenuItems>,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Second)
            .padding(6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        items.forEach { item ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(12.dp))
                    .clickable { /* обработка клика */ }
                    .background(Accent)
                    .border(
                        BorderStroke(1.dp, FirstText.copy(alpha = 0.1f)),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(item.title),
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Black,
                        fontFamily = InterFont,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 0.5.sp
                    ),
                )
            }
        }
    }
}

@Composable
fun ExerciseTitle(
    title: String
) {
    Text(
        text = title,
        style = TextStyle(
            fontSize = 24.sp,
            color = Accent,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 1.sp,
            fontFamily = InterFont
        ),
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Composable
fun ExerciseItem(
    exercise: Exercise,
    modifier:Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(
                BorderStroke(1.dp, Border),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = exercise.name,
                style = TextStyle(
                    fontSize = 20.sp,
                    color = FirstText,
                    fontFamily = InterFont,
                    fontWeight = FontWeight.Medium
                )
            )
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Second)
                    .padding(vertical = 8.dp, horizontal = 24.dp),
            ) {
                Icon(
                    imageVector = Icons.Outlined.Videocam,
                    contentDescription = null,
                    tint = FirstText,
                    modifier = Modifier
                        .size(20.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp))
                .background(Accent.copy(alpha = 0.1f))
                .drawBehind {
                    val strokeWidth = 2.dp.toPx()
                    drawLine(
                        color = Accent,
                        start = androidx.compose.ui.geometry.Offset(x = strokeWidth / 2, y = 0f),
                        end = androidx.compose.ui.geometry.Offset(x = strokeWidth / 2, y = size.height),
                        strokeWidth = strokeWidth
                    )
                }
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Default.FlashOn,
                contentDescription = null,
                tint = Accent,
                modifier = Modifier.size(18.dp)
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = "включайся, должно быть тяжело",
                style = TextStyle(
                    fontSize = 14.sp,
                    color = FirstText,
                    fontFamily = InterFont,
                    fontWeight = FontWeight.Light,
                    fontStyle = FontStyle.Italic
                )
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        exercise.repetitions.forEach { repetition ->
            SingleRepetition(
                1, repetition
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .align(Alignment.End)
                .clip(RoundedCornerShape(8.dp))
                .background(Second)
                .padding(vertical = 8.dp, horizontal = 44.dp),
        ) {
            Icon(
                imageVector = Icons.Outlined.Edit,
                contentDescription = null,
                tint = FirstText,
                modifier = Modifier
                    .size(20.dp)
            )
        }
    }
}

@Composable
fun SingleRepetition(
    index: Int,
    repetition: Repetition,
    modifier: Modifier = Modifier
) {
    val weightText = if (repetition.weight % 1 == 0.0) {
        repetition.weight.toInt().toString()
    } else {
        repetition.weight.toString()
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    color = if (!repetition.isDone && index == 3) Accent.copy(alpha = 0.4f) else Transparent
                ),
                shape = RoundedCornerShape(12.dp)
            )
            .background(Black)
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$index.",
            style = TextStyle(
                fontSize = 16.sp,
                color = if (repetition.isDone) SecondText else Accent,
                fontFamily = InterFont,
                fontWeight = FontWeight.Medium
            ),
            modifier = Modifier.width(28.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = weightText,
            style = TextStyle(
                fontSize = 20.sp,
                color = FirstText,
                fontFamily = InterFont,
                fontWeight = FontWeight.Bold
            )
        )

        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = null,
            tint = SecondText,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .size(16.dp)
        )

        Text(
            text = repetition.quantity.toString(),
            style = TextStyle(
                fontSize = 20.sp,
                color = FirstText,
                fontFamily = InterFont,
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(modifier = Modifier.weight(1f))

        if (repetition.isDone) {
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .background(color = Accent, shape = androidx.compose.foundation.shape.CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = "Выполнено",
                    tint = Black,
                    modifier = Modifier.size(12.dp)
                )
            }
        } else {
            Icon(
                imageVector = Icons.Default.RadioButtonUnchecked,
                contentDescription = "Не выполнено",
                tint = SecondText,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MenuPreview() {
    MainScreen()
}

