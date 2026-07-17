package com.example.targym.presentation.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.targym.R
import com.example.targym.ui.theme.Accent
import com.example.targym.ui.theme.FirstText
import com.example.targym.ui.theme.InterFont
import com.example.targym.ui.theme.SecondText

@Composable
fun EmptyStateContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.image_for_emptyscreen),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = modifier
                .height(240.dp)
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = buildAnnotatedString {
                append(stringResource(R.string.you_don_have))
                withStyle(style = SpanStyle(
                    color = Accent,
                    fontWeight = FontWeight.ExtraBold
                )) {
                    append(stringResource(R.string.training_days))
                }
            },
            style = TextStyle(
                fontSize = 24.sp,
                color = FirstText,
                fontFamily = InterFont,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 30.sp
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .widthIn(max = 300.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.create_first_training),
            style = TextStyle(
                fontSize = 14.sp,
                color = SecondText,
                fontFamily = InterFont,
                fontWeight = FontWeight.Normal,
                lineHeight = 20.sp
            ),
            modifier = Modifier.widthIn(max = 320.dp),
            textAlign = TextAlign.Center
        )
    }
}