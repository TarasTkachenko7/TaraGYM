package com.example.targym.presentation.edit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
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
import com.example.targym.ui.theme.Background
import com.example.targym.ui.theme.Border
import com.example.targym.ui.theme.FirstText
import com.example.targym.ui.theme.Garbage
import com.example.targym.ui.theme.InterFont
import com.example.targym.ui.theme.Second
import com.example.targym.ui.theme.SecondText

@Composable
fun EditScreenHeader(
    title: String,
    isMenuExpanded: Boolean,
    onNavigationClick: () -> Unit,
    onMoreClick: () -> Unit,
    onDismissMenu: () -> Unit,
    onRenameClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .height(40.dp)
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBackIosNew,
            contentDescription = stringResource(R.string.backstack),
            tint = SecondText,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .size(36.dp)
                .clip(CircleShape)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple(true),
                    role = Role.Button,
                    onClick = onNavigationClick
                )
                .padding(6.dp)
        )

        Text(
            text = title,
            style = TextStyle(
                fontSize = 18.sp,
                color = FirstText,
                fontFamily = InterFont,
                fontWeight = FontWeight.SemiBold
            ),
            modifier = Modifier.align(Alignment.Center)
        )

        Box(modifier = Modifier.align(Alignment.CenterEnd)) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = null,
                tint = SecondText,
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = ripple(true),
                        role = Role.Button,
                        onClick = onMoreClick
                    )
                    .padding(6.dp)
            )

            DropdownMenu(
                expanded = isMenuExpanded,
                onDismissRequest = onDismissMenu,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .background(Second)
                    .border(1.dp, Border, RoundedCornerShape(12.dp))
            ) {
                DropdownMenuItem(
                    text = {
                        Text(
                            text = stringResource(R.string.change_name),
                            style = TextStyle(
                                fontFamily = InterFont,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Medium,
                                color = FirstText
                            )
                        )
                    },
                    onClick = {
                        onDismissMenu()
                        onRenameClick()
                    },
                    contentPadding = PaddingValues(horizontal = 14.dp, vertical = 12.dp)
                )

                DropdownMenuItem(
                    text = {
                        Text(
                            text = stringResource(R.string.delete),
                            style = TextStyle(
                                fontFamily = InterFont,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Medium,
                                color = Garbage.copy(alpha = 0.8F)
                            )
                        )
                    },
                    onClick = {
                        onDismissMenu()
                        onDeleteClick()
                    },
                    contentPadding = PaddingValues(horizontal = 14.dp, vertical = 12.dp)
                )
            }
        }
    }
}