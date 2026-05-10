package com.example.weathersnap.presentation.screen.CameraScreen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(): ViewModel() {
    // No state needed in ViewModel; camera is handled in Composable
}
