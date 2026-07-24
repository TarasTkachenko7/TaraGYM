package com.example.targym.presentation.main.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Videocam
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.targym.R
import com.example.targym.ui.theme.FirstText
import com.example.targym.ui.theme.Second

@Composable
fun VideoButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Second)
            .padding(horizontal = 8.dp)
    ) {
        Icon(
            imageVector = Icons.Outlined.Videocam,
            contentDescription = stringResource(R.string.watch_technique),
            tint = FirstText,
            modifier = Modifier.size(20.dp)
        )
    }
}