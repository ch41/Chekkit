package com.example.ui.model

import com.example.presentation.BaseEffect

sealed class ScannerEffect : BaseEffect {
    data class NavigateToReceiptForm(val text: String) : ScannerEffect()
    data class ShowToast(val message: String) : ScannerEffect()
}