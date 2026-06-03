package com.example.domain.repository


interface ImageRepository {

    suspend fun extractTextFromImage(imageData: ByteArray): String

    // файлы
    // suspend fun extractTextFromFile(imageFile: File): String
}