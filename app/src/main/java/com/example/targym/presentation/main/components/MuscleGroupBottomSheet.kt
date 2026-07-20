package com.example.targym.presentation.main.components

import android.R.attr.fontFamily
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.targym.R
import com.example.targym.domain.model.MuscleGroup
import com.example.targym.ui.theme.Accent
import com.example.targym.ui.theme.Background
import com.example.targym.ui.theme.Black
import com.example.targym.ui.theme.FirstText
import com.example.targym.ui.theme.InterFont
import com.example.targym.ui.theme.Second
import com.example.targym.ui.theme.SecondText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MuscleGroupBottomSheet(
    availableGroups: List<MuscleGroup>,
    onDismissRequest: () -> Unit,
    onMuscleGroupClick: (MuscleGroup) -> Unit,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        containerColor = Second,
        dragHandle = null,
        modifier = modifier
    ) {
        val bottomPadding = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 28.dp, bottom = bottomPadding + 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.select_muscle),
                style = TextStyle(
                    fontFamily = InterFont,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Accent
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            if (availableGroups.isEmpty()) {
                Text(
                    text = stringResource(R.string.all_muscle_groups_already_on_screen),
                    style = TextStyle(
                        fontFamily = InterFont,
                        fontSize = 14.sp,
                        color = SecondText,
                        textAlign = TextAlign.Center,
                        lineHeight = 22.sp
                    ),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = onDismissRequest,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Accent),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = stringResource(R.string.understand),
                        style = TextStyle(
                            fontFamily = InterFont,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Black,
                            letterSpacing = 0.5.sp
                        )
                    )
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(availableGroups) { muscleGroup ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Background)
                                .clickable { onMuscleGroupClick(muscleGroup) },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(muscleGroup.titleRes),
                                style = TextStyle(
                                    fontFamily = InterFont,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = FirstText
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}