package com.example.targym.presentation.main.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.targym.R
import com.example.targym.presentation.main.RepetitionUiModel
import com.example.targym.ui.theme.Accent
import com.example.targym.ui.theme.Black
import com.example.targym.ui.theme.FirstText
import com.example.targym.ui.theme.InterFont
import com.example.targym.ui.theme.SecondText

@Composable
fun SingleRepetition(
    repetition: RepetitionUiModel,
    onCheckClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val containerAlpha = if (repetition.isDone) 0.6f else 1.0f

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    color = if (repetition.isDone) Accent.copy(alpha = 0.3f) else Transparent
                ),
                shape = RoundedCornerShape(12.dp)
            )
            .background(Black)
            .graphicsLayer { alpha = containerAlpha }
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = repetition.indexText,
            style = TextStyle(
                fontSize = 16.sp,
                color = if (repetition.isDone) Accent else SecondText,
                fontFamily = InterFont,
                fontWeight = FontWeight.Medium
            ),
            modifier = Modifier.width(28.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = repetition.weightText,
            style = TextStyle(
                fontSize = 20.sp,
                color = if (repetition.isDone) SecondText else FirstText,
                fontFamily = InterFont,
                fontWeight = FontWeight.Bold
            )
        )

        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = stringResource(R.string.x_info),
            tint = SecondText.copy(alpha= 0.3F),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .size(16.dp)
        )

        Text(
            text = repetition.quantityText,
            style = TextStyle(
                fontSize = 20.sp,
                color = if (repetition.isDone) SecondText else FirstText,
                fontFamily = InterFont,
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(modifier = Modifier.weight(1f))

        RepetitionCheckbox(
            isDone = repetition.isDone,
            onClick = onCheckClick
        )
    }
}

@Composable
private fun RepetitionCheckbox(
    isDone: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .clickable(
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(bounded = false, radius = 20.dp),
                role = Role.Button
            )
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        if (isDone) {
            Box(
                modifier = Modifier
                    .size(18.dp)
                    .background(color = Accent, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = stringResource(R.string.completed),
                    tint = Black,
                    modifier = Modifier.size(12.dp)
                )
            }
        } else {
            Icon(
                imageVector = Icons.Default.RadioButtonUnchecked,
                contentDescription = stringResource(R.string.not_completed),
                tint = SecondText,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}
