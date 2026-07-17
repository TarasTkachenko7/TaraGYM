package com.example.targym.presentation.main.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.targym.R
import com.example.targym.domain.model.MuscleGroup
import com.example.targym.ui.theme.Accent
import com.example.targym.ui.theme.InterFont
import com.example.targym.ui.theme.SecondText

@Composable
fun MuscleGroupSectionHeader(
    title: String,
    onMuscleMenu: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        MuscleGroupTitle(
            title = title,
            modifier = Modifier.weight(1f)
        )

        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = stringResource(R.string.muscle_info),
            tint = SecondText,
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple(true),
                    role = Role.Button,
                    onClick = onMuscleMenu
                )
                .padding(6.dp)
        )
    }
}

@Composable
private fun MuscleGroupTitle(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        style = TextStyle(
            fontSize = 24.sp,
            color = Accent,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 0.5.sp,
            fontFamily = InterFont
        ),
        modifier = modifier
    )
}