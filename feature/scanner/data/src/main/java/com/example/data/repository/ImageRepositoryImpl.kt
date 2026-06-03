package com.example.data.repository

import com.example.data.ocr.TesseractEngine
import com.example.domain.repository.ImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ImageRepositoryImpl(
    private val tesseractEngine: TesseractEngine
) : ImageRepository {

    override suspend fun extractTextFromImage(imageData: ByteArray): String =
        withContext(Dispatchers.IO) {
            tesseractEngine.extractText(imageData)
        }

}
