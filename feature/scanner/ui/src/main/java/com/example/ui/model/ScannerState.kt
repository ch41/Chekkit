package com.example.ui.model

import com.example.presentation.BaseState

sealed class ScannerState : BaseState {
    data object Idle : ScannerState()
    data object Camera : ScannerState()
    data object Processing : ScannerState()
    data class Recognized(val text: String) : ScannerState()
    data class Error(val message: String) : ScannerState()
}