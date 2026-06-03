package com.example.ui.viewModel

import com.example.presentation.BaseViewModel
import com.example.ui.model.ScannerEffect
import com.example.ui.model.ScannerIntent
import com.example.ui.model.ScannerState
import kotlinx.coroutines.delay

class ScannerViewModel : BaseViewModel<ScannerState, ScannerIntent, ScannerEffect>(){

    override fun createInitialState(): ScannerState = ScannerState.Idle
    override suspend fun handleEvent(event: ScannerIntent) {
        when (event) {
            ScannerIntent.StartCamera -> {
                setState { ScannerState.Camera }
            }
            is ScannerIntent.TextRecognized -> {
                setState { ScannerState.Processing }
                delay(500)
                setState { ScannerState.Recognized(event.text) }
                sendEffect(ScannerEffect.NavigateToReceiptForm(event.text))
            }
            ScannerIntent.Reset -> {
                setState { ScannerState.Camera }
            }
            ScannerIntent.SaveReceipt -> {
                sendEffect(ScannerEffect.ShowToast("Чек сохранён"))
                setState { ScannerState.Camera }
            }
        }
    }


}