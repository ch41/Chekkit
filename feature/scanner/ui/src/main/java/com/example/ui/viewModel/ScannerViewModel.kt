package com.example.ui.viewModel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.domain.use_case.TextExtractionUseCase
import com.example.presentation.BaseViewModel
import com.example.ui.model.ScannerEffect
import com.example.ui.model.ScannerEffect.*
import com.example.ui.model.ScannerIntent
import com.example.ui.model.ScannerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ScannerViewModel(
    private val textExtractionUseCase: TextExtractionUseCase,
) : BaseViewModel<ScannerState, ScannerIntent, ScannerEffect>(){

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
                sendEffect(NavigateToReceiptForm(event.text))
            }
            ScannerIntent.Reset -> {
                setState { ScannerState.Camera }
            }
            ScannerIntent.SaveReceipt -> {
                sendEffect(ShowToast("Чек сохранён"))
                setState { ScannerState.Camera }
            }

            is ScannerIntent.ProcessImage -> {
                processImage(event.imageData)
            }
        }
    }
    private fun processImage(imageData: ByteArray) {
        viewModelScope.launch {
            try {
                setState { ScannerState.Processing }

                val recognizedText = textExtractionUseCase(imageData)

                Log.d("ScannerViewModel", "result: '$recognizedText'")
                setState { ScannerState.Recognized(recognizedText) }
                sendEffect(NavigateToReceiptForm(recognizedText))
            } catch (e: Exception) {
                setState { ScannerState.Error(e.message ?: "Ошибка распознавания") }
                sendEffect(ShowToast("Ошибка: ${e.message}"))
            }
        }
    }

}