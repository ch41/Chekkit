package com.example.ui.model

import com.example.presentation.BaseIntent

sealed class ScannerIntent : BaseIntent {
    data object StartCamera : ScannerIntent()
    data class TextRecognized(val text: String) : ScannerIntent()
    data object Reset : ScannerIntent()
    data object SaveReceipt : ScannerIntent()
}