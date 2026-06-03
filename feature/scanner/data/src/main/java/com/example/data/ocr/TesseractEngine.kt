package com.example.data.ocr

import android.content.Context
import android.graphics.Bitmap
/*
import com.googlecode.tesseract.android.TessBaseAPI

class TesseractEngine(
    private val context: Context
) {

    private val tessBaseApi by lazy {
        TessBaseAPI().apply {
            init(
                getTessDataPath(),
                LANGUAGE
            )
        }
    }

    fun extractText(bitmap: Bitmap): String {
        return try {
            tessBaseApi.setImage(bitmap)

            tessBaseApi.utF8Text
                ?.trim()
                .orEmpty()
        } finally {
            tessBaseApi.clear()
        }
    }

    fun release() {
//        tessBaseApi.end()
    }

    private fun getTessDataPath(): String {
        return context.filesDir.absolutePath
    }

    companion object {
        private const val LANGUAGE = "ru"
    }
}*/
