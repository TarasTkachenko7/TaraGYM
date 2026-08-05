package com.example.targym.presentation.gif.views

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.targym.R
import com.example.targym.presentation.gif.components.VideoPlayer
import com.example.targym.ui.theme.Accent
import com.example.targym.ui.theme.Background
import com.example.targym.ui.theme.Black
import com.example.targym.ui.theme.Border
import com.example.targym.ui.theme.ButtonsTextStyle
import com.example.targym.ui.theme.FirstText
import com.example.targym.ui.theme.InterFont
import com.example.targym.ui.theme.Second
import com.example.targym.ui.theme.SecondText
import androidx.core.net.toUri

@Composable
fun GifSuccess(
    exerciseName: String,
    mediaUri: String?,
    onMediaSelected: (Uri?) -> Unit,
    onGotItClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    var isClosing by remember { mutableStateOf(false) }

    val handleDismiss = {
        if (!isClosing) {
            isClosing = true
            onGotItClick()
        }
    }

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
            onMediaSelected(uri)
        }
    }

    val isVideo = remember(mediaUri) {
        if (mediaUri == null) false
        else {
            val uri = mediaUri.toUri()
            val mimeType = context.contentResolver.getType(uri)
            mimeType?.startsWith("video") == true || mediaUri.endsWith(".mp4", ignoreCase = true)
        }
    }

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
                text = exerciseName,
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
                    .height(400.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Background)
                    .border(1.dp, Border, RoundedCornerShape(8.dp))
                    .clickable {
                        photoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo)
                        )
                    },
                contentAlignment = Alignment.Center
            ) {
                if (mediaUri != null && !isClosing) {
                    val parsedUri = remember(mediaUri) { mediaUri.toUri() }
                    if (isVideo) {
                        VideoPlayer(
                            uri = parsedUri,
                            modifier = Modifier.fillMaxSize()
                        )
                    } else {
                        AsyncImage(
                            model = mediaUri,
                            contentDescription = exerciseName,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(8.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(Second.copy(alpha = 0.8f))
                            .padding(6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = stringResource(R.string.edit),
                            tint = FirstText,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                } else if (!isClosing) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.AddAPhoto,
                            contentDescription = stringResource(R.string.add_photo),
                            tint = SecondText,
                            modifier = Modifier.size(40.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = stringResource(R.string.add_media),
                            style = TextStyle(
                                fontFamily = InterFont,
                                fontSize = 14.sp,
                                color = SecondText,
                                letterSpacing = 0.5.sp,
                                fontWeight = FontWeight.Normal
                            )
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = handleDismiss,
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