package com.example.domain.use_case

import com.example.domain.repository.ImageRepository

class TextExtractionUseCase(private val imageRepository: ImageRepository) {
    suspend operator fun invoke(imageData: ByteArray): String {
        return imageRepository.extractTextFromImage(imageData)
    }
}