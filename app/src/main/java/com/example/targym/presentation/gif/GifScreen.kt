package com.example.targym.presentation.gif

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.targym.presentation.gif.views.GifError
import com.example.targym.presentation.gif.views.GifLoading
import com.example.targym.presentation.gif.views.GifSuccess

@Composable
fun GifScreen(
    viewModel: GifViewModel,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    val screenState by viewModel.uiState.collectAsStateWithLifecycle()

    when (val state = screenState) {
        is GifScreenState.Loading -> {
            GifLoading(modifier = modifier)
        }
        is GifScreenState.Success -> {
            GifSuccess(
                exerciseName = state.exerciseName,
                onGotItClick = onDismiss,
                modifier = modifier
            )
        }
        is GifScreenState.Error -> {
            GifError(
                messageRes = state.messageRes,
                onGotItClick = onDismiss,
                modifier = modifier
            )
        }
    }
}