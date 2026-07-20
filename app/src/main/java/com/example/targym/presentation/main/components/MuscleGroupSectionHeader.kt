package com.example.targym.presentation.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import com.example.targym.ui.theme.Accent
import com.example.targym.ui.theme.Background
import com.example.targym.ui.theme.Border
import com.example.targym.ui.theme.FirstText
import com.example.targym.ui.theme.Garbage
import com.example.targym.ui.theme.InterFont
import com.example.targym.ui.theme.SecondText

@Composable
fun MuscleGroupSectionHeader(
    title: String,
    isMenuExpanded: Boolean,
    onMenuToggle: (Boolean) -> Unit,
    onAddExerciseClick: () -> Unit,
    onDeleteGroupClick: () -> Unit,
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
            imageVector = Icons.Default.Add,
            contentDescription = stringResource(R.string.add_exercise),
            tint = SecondText,
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple(true),
                    role = Role.Button,
                    onClick = { onAddExerciseClick() }
                )
                .padding(6.dp)
        )

        Spacer(Modifier.width(4.dp))

        Box {
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
                        onClick = { onMenuToggle(true) }
                    )
                    .padding(6.dp)
            )

            DropdownMenu(
                expanded = isMenuExpanded,
                onDismissRequest = { onMenuToggle(false) },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .background(Background)
                    .border(1.dp, Border, RoundedCornerShape(12.dp))
            ) {
                DropdownMenuItem(
                    text = {
                        Text(
                            text = stringResource(R.string.delete_muscle),
                            style = TextStyle(
                                fontFamily = InterFont,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Medium,
                                color = Garbage.copy(alpha = 0.8F)
                            )
                        )
                    },
                    onClick = {
                        onMenuToggle(false)
                        onDeleteGroupClick()
                    },
                    contentPadding = PaddingValues(horizontal = 14.dp, vertical = 12.dp)
                )
            }
        }
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