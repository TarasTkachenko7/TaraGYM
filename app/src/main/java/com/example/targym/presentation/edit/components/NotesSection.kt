package com.example.targym.presentation.edit.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.targym.ui.theme.Accent
import com.example.targym.ui.theme.FirstText
import com.example.targym.ui.theme.InterFont
import com.example.targym.ui.theme.Second
import com.example.targym.ui.theme.SecondText
import com.example.targym.R
import com.example.targym.ui.theme.Border

@Composable
fun NotesSection(
    noteText: String,
    onNoteChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.notes),
            style = TextStyle(
                fontSize = 20.sp,
                color = Accent,
                fontWeight = FontWeight.Medium,
                fontFamily = InterFont
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = noteText,
            onValueChange = onNoteChange,
            placeholder = {
                Text(stringResource(R.string.add_details), color = SecondText, fontSize = 16.sp)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            shape = RoundedCornerShape(12.dp),
            textStyle = TextStyle(color = FirstText, fontSize = 16.sp, fontFamily = InterFont),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Second,
                unfocusedContainerColor = Second,
                focusedBorderColor = Border,
                unfocusedBorderColor = Second,
                cursorColor = Accent
            )
        )
    }
}